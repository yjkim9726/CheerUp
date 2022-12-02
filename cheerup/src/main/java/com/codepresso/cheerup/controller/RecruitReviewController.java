package com.codepresso.cheerup.controller;

import com.codepresso.cheerup.service.RecruitReviewService;
import com.codepresso.cheerup.vo.Pagination;
import com.codepresso.cheerup.vo.RecruitReview;
import com.codepresso.cheerup.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(path="recruitReview")
public class RecruitReviewController {
    private final RecruitReviewService recruitReviewService;

    @GetMapping("/mainRecruitReview")
    public String mainRecruitReview(Model model,RecruitReview review, @RequestParam(defaultValue = "1") int page, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String loginUser = ((User) userDetails).getId();

        int totalListCnt;
        if(review.getSearchKeyword()== null){
             totalListCnt = recruitReviewService.findAllCnt();
        }else{
            totalListCnt = recruitReviewService.findSearchCnt(review);
        }

        // 생성인자로  총 게시물 수, 현재 페이지를 전달
        Pagination pagination = new Pagination(totalListCnt, page);

        // DB select start index
        int startIndex = pagination.getStartIndex();
        // 페이지 당 보여지는 게시글의 최대 개수
        int pageSize = pagination.getPageSize();

        List<RecruitReview> list = new ArrayList<>();
        if(review.getSearchKeyword()== null){
            list = recruitReviewService.getAllRecruitReview(startIndex, pageSize);
        }else{
            review.setStartIndex(startIndex);
            review.setPageSize(pageSize);
            list = recruitReviewService.getSearchRecruitReview(review);
        }
        model.addAttribute("reviewList", list);
        model.addAttribute("pagination", pagination);
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("searchCategory",review.getCategory());
        model.addAttribute("searchKeyword",review.getSearchKeyword());
        return "recruitReview/mainRecruitReview";
    }

    @GetMapping("/writeReview")
    public String writeReview(Model model, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String loginUser = ((User) userDetails).getId();

        model.addAttribute("loginUser", loginUser);
        return "recruitReview/writeReview";
    }

    @PostMapping("/enrollReview")
    public String enrollReview(Model model,RecruitReview review){
        int enrollReview = recruitReviewService.enrollReview(review);
        return "redirect:mainRecruitReview";
    }

    @GetMapping("/viewReview")
    public String viewReview(Model model, @RequestParam int boardNo, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String loginUser = ((User) userDetails).getId();
        RecruitReview review = recruitReviewService.getOneRecruitReview(boardNo);
        model.addAttribute("review", review);
        model.addAttribute("loginUser", loginUser);
        return "recruitReview/viewReview";
    }

    @PostMapping("/modifyReview")
    public String modifyReview(Model model, HttpServletRequest request){
        RecruitReview review = new RecruitReview();
        review.setBoardNo(Integer.parseInt(request.getParameter("md-boardNo")));
        review.setTitle(request.getParameter("md-title"));
        review.setCategory(request.getParameter("md-category"));
        review.setCompany(request.getParameter("md-company"));
        review.setContent(request.getParameter("md-content"));
        review.setCodingDiff(request.getParameter("md-codingDiff"));
        int updateReview = recruitReviewService.updateReview(review);
        return "redirect:viewReview?boardNo="+review.getBoardNo();
    }

    @GetMapping("/deleteReview")
    public String deleteReview(Model model, @RequestParam int boardNo){
        int result = recruitReviewService.deleteReview(boardNo);
        if(result == 1){
            return "redirect:mainRecruitReview";
        }else{
            return "에러페이지";
        }
    }

}
