package com.codepresso.cheerup.controller;

import com.codepresso.cheerup.service.InterviewService;
import com.codepresso.cheerup.vo.Interview;
import com.codepresso.cheerup.vo.Interview_response;
import lombok.RequiredArgsConstructor;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

import static org.apache.logging.log4j.core.util.ArrayUtils.remove;

@Controller
@RequiredArgsConstructor
public class InterviewPageController {

    private final InterviewService interviewService;

    //화면 이동 : interview 홈
    @GetMapping("/interview-home")
    public String interview_home(Authentication authentication, Model model){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String id = userDetails.getUsername();
        System.out.println("controller ++++++++++++"+id);

        //사용자가 저장한 무응답 질문들이 있는지 가져오기
        int programming = interviewService.getPgCount(id);
        int data_structure = interviewService.getDsCount(id);
        int algorithm = interviewService.getAgCount(id);
        int database = interviewService.getDbCount(id);
        int network = interviewService.getNwCount(id);
        int os = interviewService.getOsCount(id);
        int personality = interviewService.getPsCount(id);
        int surprise = interviewService.getSpCount(id);

        System.out.println("controller ++++++++++++"+programming);

        model.addAttribute("programming",programming);
        model.addAttribute("data_structure",data_structure);
        model.addAttribute("algorithm",algorithm);
        model.addAttribute("database",database);
        model.addAttribute("network",network);
        model.addAttribute("os",os);
        model.addAttribute("personality",personality);
        model.addAttribute("surprise",surprise);


        return "user/interview/interview-home";


    }

    //interview 종료
    @PostMapping("/interview-result")
    public String result(String interview_id, String interview_answer, Authentication authentication , Model model){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String id = userDetails.getUsername();

        String[] id_array = interview_id.split(",");
        String[] answer_array = interview_answer.split(",");

        System.out.println(interview_id);
        System.out.println(interview_answer);


        System.out.println("여기는 컨트롤러"+interview_id);
        System.out.println("여기는 컨트롤러"+interview_answer);

        //사용자의 응답 데이터 저장
        for (int i = 0; i < id_array.length; i++) {
            int result2 = interviewService.insertUserResponse2(id, id_array[i],answer_array[i]);
            System.out.println(result2 + ",i:" + i);
        }

        //질문 total 갯수 1 증가시키기
        for (int i = 0; i < id_array.length; i++) {
            interviewService.updateTotalCount(id_array[i]);
        }
        

        //면접 결과 보여주기
        List<Interview> response_list = interviewService.getResponseList(id_array);
        List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
        for(int i =0; i<response_list.size(); i++){
            Map<String, Object> obj = new Hashtable<>();
            obj.put("answer",answer_array[i]);
            obj.put("response", response_list.get(i));
            map.add(obj);
        }
        model.addAttribute("id_array",id_array);
        model.addAttribute("map",map);

        //기존의 응답 테이블의 갯수 가져오기
       // int response_total = interviewService.getRowCount();

        //무응답 질문 전체 질문 테이블에 반영 > 이따 와서 하자
        /** 1. 대답 array 에서 "X" 값을 찾는다 -> X 값의 인덱스를 찾아서 삭제시킨다
         *  2. 질문 id array에서 위와 같은 위치의 인덱스를 삭제한다.
         */

        List<String> id_list = new ArrayList<>();
        List<String> answer_list = new ArrayList<>();


        for (int i = 0; i < answer_array.length; i++) {
            if(answer_array[i].equals("X")){
                //X라면 ?
                id_list.add(id_array[i]);
                answer_list.add(answer_array[i]);

                System.out.println(id_array[i]);
                System.out.println(answer_array[i]);
            }
        }


        String[] failId = id_list.toArray(new String[id_list.size()]);

        //fail  갯수 1 증가시키기
        for (int i = 0; i < failId.length; i++) {
            interviewService.updateFailCounting2(failId[i]);
        }

        System.out.println("answer_list"+answer_list);

        return "/user/interview/interview-result";
    }


    //interview 진행 중
    @GetMapping("/interview")
    public String interview( Interview interview, Model model) {

        String category_origin = interview.getCategory();
        System.out.println("여기는 컨트롤러고 카테고리를 받았어요" + category_origin);

        //(1)카테고리의 id 값을 넘겨서 사용자가 선택한 질문 카테고리만 가져오기
        if (!(category_origin.contains(","))) {
            //(1)-(1) : 단일 카테고리 작업
            System.out.println("여기는 컨트롤러고 조건문 단일 카테고리가 안에 있어요" + category_origin);

            List<Interview> singleList = interviewService.getSingleList(category_origin);

            model.addAttribute("list", singleList);

        } else {
            //(1)-(2) : 복합 카테고리 작업
            String[] multiArray = category_origin.split(",");
            System.out.println("여기는 컨트롤러고 조건문 복합 카테고리 안에 있어요" + multiArray);

            //쿼리 갯수 제한 카운트
            int limitCount = 0;

            if (multiArray.length == 2) {
                //카테고리 2개 -> 각 카테고리당 5개씩
                limitCount = 5;
            } else if (multiArray.length == 3) {
                //카테고리 3개 -> 각 카테고리당 3개씩
                limitCount = 4;
            } else if (multiArray.length == 4) {
                //카테고리 4개 -> 각 카테고리당 3개씩
                limitCount = 3;
            } else if (multiArray.length == 5) {
                //카테고리 5개 -> 각 카테고리당 3개씩
                limitCount = 3;
            } else if (multiArray.length == 6) {
                limitCount = 3;
            } else if (multiArray.length == 7) {
                limitCount = 2;
            } else if (multiArray.length == 8) {
                limitCount = 2;
            }

            //     List<Interview> multiList = interviewService.getMultiList(multiArray);
            //    model.addAttribute("list", multiList);

            List<Interview> multiListTest = interviewService.multiListTest(multiArray, limitCount);
            model.addAttribute("list", multiListTest);

            System.out.println(multiListTest.toString());
        }

        return "user/interview/interview";
    }

    @GetMapping("/interviewing_f")
    public String interviewing_f(Authentication authentication, Model model){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String id = userDetails.getUsername();

        List<Interview> failList = interviewService.getFailList(id);

        model.addAttribute("list",failList);

        return "user/interview/interview_fail";
    }

    @PostMapping("interview_result_f")
    public String result_fail_ver(String interview_id, String interview_answer, Authentication authentication , Model model){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String id = userDetails.getUsername();

        String[] id_array = interview_id.split(",");
        String[] answer_array = interview_answer.split(",");

        System.out.println(interview_id);
        System.out.println(interview_answer);


        //사용자의 응답 데이터 저장
        for (int i = 0; i < id_array.length; i++) {
            int result2 = interviewService.insertUserResponse2(id, id_array[i],answer_array[i]);
            System.out.println(result2 + ",i:" + i);
        }

        //질문 total 갯수 1 증가시키기
        for (int i = 0; i < id_array.length; i++) {
            interviewService.updateTotalCount(id_array[i]);
        }


        //면접 결과 보여주기
        List<Interview> response_list = interviewService.getResponseList(id_array);
        List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
        for(int i =0; i<response_list.size(); i++){
            Map<String, Object> obj = new Hashtable<>();
            obj.put("answer",answer_array[i]);
            obj.put("response", response_list.get(i));
            map.add(obj);
        }
        model.addAttribute("id_array",id_array);
        model.addAttribute("map",map);

        //기존의 응답 테이블의 갯수 가져오기
//        int response_total = interviewService.getRowCount();

        //무응답 질문 전체 질문 테이블에 반영 > 이따 와서 하자
        /** 1. 대답 array 에서 "X" 값을 찾는다 -> X 값의 인덱스를 찾아서 삭제시킨다
         *  2. 질문 id array에서 위와 같은 위치의 인덱스를 삭제한다.
         */

        List<String> id_list = new ArrayList<>();
        List<String> answer_list = new ArrayList<>();


        for (int i = 0; i < answer_array.length; i++) {
            if(answer_array[i].equals("X")){
                //X라면 ?
                id_list.add(id_array[i]);
                answer_list.add(answer_array[i]);

                System.out.println(id_array[i]);
                System.out.println(answer_array[i]);
            }
        }


        String[] failId = id_list.toArray(new String[id_list.size()]);

        //fail  갯수 1 증가시키기
        for (int i = 0; i < failId.length; i++) {
            interviewService.updateFailCounting2(failId[i]);
        }

        System.out.println("answer_list"+answer_list);

        return "/user/interview/interview-result";
    }

}
