package com.codepresso.cheerup.dao;

import com.codepresso.cheerup.vo.FeedBackBoard;
import com.codepresso.cheerup.vo.FeedBackBoardReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FeedBackBoardReplyMapper {
    int insertReply(FeedBackBoardReply feedBackBoardReply);

    List<FeedBackBoardReply> getAllReplyList(int boardNo);

    int getReplyTotal(int boardNo);


    int deleteReply(int replyNo);
}
