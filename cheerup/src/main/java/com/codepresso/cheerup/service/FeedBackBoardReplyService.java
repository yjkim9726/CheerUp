package com.codepresso.cheerup.service;

import com.codepresso.cheerup.dao.FeedBackBoardMapper;
import com.codepresso.cheerup.dao.FeedBackBoardReplyMapper;
import com.codepresso.cheerup.vo.FeedBackBoard;
import com.codepresso.cheerup.vo.FeedBackBoardReply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedBackBoardReplyService {
    private final FeedBackBoardReplyMapper feedBackBoardReplyMapper;

    public int insertReply(FeedBackBoardReply feedBackBoardReply) { return feedBackBoardReplyMapper.insertReply(feedBackBoardReply); }

    public List<FeedBackBoardReply> getAllReplyList(int boardNo) { return feedBackBoardReplyMapper.getAllReplyList(boardNo); }

    public int deleteReply(int replyNo) { return feedBackBoardReplyMapper.deleteReply(replyNo); }

    public int getReplyTotal(int boardNo) { return feedBackBoardReplyMapper.getReplyTotal(boardNo); }
}
