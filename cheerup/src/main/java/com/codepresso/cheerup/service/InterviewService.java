package com.codepresso.cheerup.service;

import com.codepresso.cheerup.dao.InterviewMapper;
import com.codepresso.cheerup.vo.Interview;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor //final 객체 생성자 생성
public class InterviewService {

    private final InterviewMapper interviewMapper;

    //READ : 사용자가 선택한 카테고리 질문 가져오기(단일 선택)
    public List<Interview> getSingleList(String category_origin) {
        return interviewMapper.getSingleList(category_origin);
    }

    //READ : 사용자가 선택한 카테고리 질문 가져오기(복합 선택)
    public List<Interview> getMultiList(String[] multiArray) {

        //각각 카테고리별로 가져오기 limitCount 개씩 가져오기
        if(Arrays.asList(multiArray).contains("os")){
            System.out.println("여기서비스인데요 os입니다.");
        }

        return interviewMapper.getMultiList(multiArray);
    }

    //선생님이하는대로 해보기
    public List<Interview> multiListTest(@Param("multiArray") String[] multiArray, @Param("limitCount")int limitCount) {

        //각각 카테고리별로 가져오기 limitCount 개씩 가져오기
        List<List<Interview>> all_question = new ArrayList<>();

        String category;

        if(Arrays.asList(multiArray).contains("programming")){
            List<Interview> programming = new ArrayList<>();
            category = "programming";

            programming = interviewMapper.getProgrammingList(category, limitCount);
            all_question.add(programming);

        }
        if (Arrays.asList(multiArray).contains("data_structure")){
            List<Interview> data_structure = new ArrayList<>();
            category = "data_structure";

            data_structure = interviewMapper.getData_structureList(category, limitCount);
            all_question.add(data_structure);

        }
        if (Arrays.asList(multiArray).contains("algorithm")){
            List<Interview> algorithm = new ArrayList<>();
            category = "algorithm";

            algorithm = interviewMapper.getAlgorithmList(category, limitCount);
            all_question.add(algorithm);

        }
        if (Arrays.asList(multiArray).contains("database")){
            List<Interview> database = new ArrayList<>();
            category = "database";

            database = interviewMapper.getDatabaseList(category, limitCount);
            all_question.add(database);

        }
        if (Arrays.asList(multiArray).contains("network")){
            List<Interview> network = new ArrayList<>();
            category = "network";

            network = interviewMapper.getNetworkList(category, limitCount);
            all_question.add(network);
        }
        if (Arrays.asList(multiArray).contains("os")){
            List<Interview> os = new ArrayList<>();
            category = "os";

            os = interviewMapper.getOsList(category, limitCount);
            all_question.add(os);

        }
        if (Arrays.asList(multiArray).contains("personality")){
            List<Interview> personality = new ArrayList<>();
            category = "personality";

            personality = interviewMapper.getPersonalityList(category, limitCount);
            all_question.add(personality);

        }
        if (Arrays.asList(multiArray).contains("surprise")){
            List<Interview> surprise = new ArrayList<>();
            category = "surprise";

            surprise = interviewMapper.getSurpriseList(category, limitCount);
            all_question.add(surprise);
        }

        List<Interview> finalList = new ArrayList<>();

        for(List<Interview> list : all_question){
            finalList.addAll(list);
        }

        return finalList;
    }

    public int insertUserResponse(String id, String[] id_array, String[] answer_array) {
        int result = interviewMapper.insertUserResponse(id,id_array,answer_array);

        return result;
    }

    public int insertUserResponse2(@Param("id") String id, @Param("interview_id")String interview_id, @Param("interview_answer")String interview_answer) {
        int result2 = interviewMapper.insertUserResponse2(id,interview_id,interview_answer);

        return result2;
    }

    //total 갯수 +1 증가시키기
    public void updateTotalCount(String id_array) {
        interviewMapper.updateTotalCount(id_array);
    }

    public List<Interview> getResponseList(String[] id_array) {
        return interviewMapper.getResponseList(id_array);
    }

//    public int getRowCount() {
//        return interviewMapper.getRowCount();
//    }

    public void insertFailCount(@Param("failId") String[] failId ) {
        interviewMapper.insertFailCount(failId);
    }

    public void updateFailCounting(@Param("id_list") List<Object> id_list) {
        interviewMapper.updateFailCounting(id_list);

    }

    public void updateFailCounting2(String failId) {
        interviewMapper.updateFailCounting2(failId);

    }

    public int getPgCount(String id) {
        int result = interviewMapper.getPgCount(id);
        return result;
    }

    public int getDsCount(String id) {
        int result = interviewMapper.getDsCount(id);
        return result;
    }

    public int getAgCount(String id) {
        int result = interviewMapper.getAgCount(id);
        return result;
    }

    public int getDbCount(String id) {
        int result = interviewMapper.getDbCount(id);
        return result;
    }

    public int getNwCount(String id) {
        int result = interviewMapper.getNwCount(id);
        return result;
    }

    public int getOsCount(String id) {
        int result = interviewMapper.getOsCount(id);
        return result;
    }

    public int getPsCount(String id) {
        int result = interviewMapper.getPsCount(id);
        return result;
    }

    public int getSpCount(String id) {
        int result = interviewMapper.getSpCount(id);
        return result;
    }

    public List<Interview> getFailList(String id) {
        return interviewMapper.getFailList(id);
    }
}
