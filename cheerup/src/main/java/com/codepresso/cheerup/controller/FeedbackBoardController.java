package com.codepresso.cheerup.controller;

import com.codepresso.cheerup.service.FeedBackBoardReplyService;
import com.codepresso.cheerup.service.FeedBackBoardService;
import com.codepresso.cheerup.vo.FeedBackBoard;
import com.codepresso.cheerup.vo.FeedBackBoardReply;
import com.codepresso.cheerup.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(path="feedback")
public class FeedbackBoardController {
    private final FeedBackBoardService feedBackBoardService;
    private final FeedBackBoardReplyService feedBackBoardReplyService;

    @GetMapping("/main")
    public String feedbackboard(Model model, FeedBackBoard feedBackBoard, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String user = ((User) userDetails).getId();

        List<FeedBackBoard> list = new ArrayList<>();
        list = feedBackBoardService.getAllFeedBackBoardList();

        model.addAttribute("feedbackboardList", list);
        model.addAttribute("user", user);

        return "feedbackBoard/feedback";
    }

    @GetMapping("/view")
    public String feedbackboardView(Model model, @RequestParam int boardNo, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String user = ((User) userDetails).getId();

        FeedBackBoard feedBackBoardDetail = feedBackBoardService.getFeedBackBoardDetail(boardNo);

        List<FeedBackBoardReply> replyList = feedBackBoardReplyService.getAllReplyList(boardNo);

        model.addAttribute("feedbackboardDetail", feedBackBoardDetail);
        model.addAttribute("replyList", replyList);
        model.addAttribute("user", user);
        return "feedbackBoard/feedbackView";
    }

    @GetMapping("/write")
    public String feedbackboardWrite(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String user = ((User) userDetails).getId();

        model.addAttribute("user", user);
        return "feedbackBoard/feedbackWrite";
    }

    @PostMapping("/insert")
    public String insertFeedBackBoard(Model model, FeedBackBoard feedBackBoard) {
        int insertFeedBackBoard = feedBackBoardService.insertFeedBackBoard(feedBackBoard);
        return "redirect:main";
    }

    @PostMapping("/update")
    public String updateFeedBackBoard(Model model, HttpServletRequest httpServletRequest) {
        FeedBackBoard feedBackBoard = new FeedBackBoard();
        feedBackBoard.setBoardNo(Integer.parseInt(httpServletRequest.getParameter("md-boardNo")));
        feedBackBoard.setTitle(httpServletRequest.getParameter("md-title"));
        feedBackBoard.setContent(httpServletRequest.getParameter("md-content"));

        int updateFeedBackBoard = feedBackBoardService.updateFeedBackBoard(feedBackBoard);
        return "redirect:view?boardNo="+feedBackBoard.getBoardNo();
    }

    @GetMapping("/delete")
    public String deleteFeedBackBoard(Model model, @RequestParam int boardNo) {
        int result = feedBackBoardService.deleteFeedBackBoard(boardNo);
        if (result == 1) {
            return "redirect:main";
        } else {
            return "삭제가 되지 않았습니다.";
        }
    }

    //주소매핑
    @PostMapping("/view/insertReply")
    public String enrollReply(@RequestParam("re-boardNo") int boardNo,@RequestParam("re-writeId") String writeId, @RequestParam("re-content") String content, Model model,Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String user = ((User) userDetails).getId();
        List<FeedBackBoardReply> replyList;


        FeedBackBoardReply reply = new FeedBackBoardReply();
        reply.setBoardNo(boardNo);
        reply.setWriteId(writeId);
        reply.setReply(content);

        int result = feedBackBoardReplyService.insertReply(reply);
        if(result == 1){
            replyList = feedBackBoardReplyService.getAllReplyList(boardNo);
        } else{
            return "정상적으로 등록되지 않았습니다.";
        }

        FeedBackBoard feedBackBoardDetail = feedBackBoardService.getFeedBackBoardDetail(boardNo);

        model.addAttribute("feedbackboardDetail", feedBackBoardDetail);
        model.addAttribute("replyList", replyList);
        model.addAttribute("user", user);
        return "feedbackBoard/feedbackView";
    }

    //주소매핑
    @GetMapping("/view/deleteReply")
    public String deleteReply(Model model, @RequestParam int boardNo, @RequestParam int replyNo, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String user = ((User) userDetails).getId();
        List<FeedBackBoardReply> replyList;

        int result = feedBackBoardReplyService.deleteReply(replyNo);

        if (result == 1){
            replyList = feedBackBoardReplyService.getAllReplyList(boardNo);
        } else{
           return "정상적으로 삭제가 되지 않았습니다.";
        }

        FeedBackBoard feedBackBoardDetail = feedBackBoardService.getFeedBackBoardDetail(boardNo);

        model.addAttribute("feedbackboardDetail", feedBackBoardDetail);
        model.addAttribute("replyList", replyList);
        model.addAttribute("user", user);
        return "feedbackBoard/feedbackView";
    }
}
