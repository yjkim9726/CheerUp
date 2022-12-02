package com.codepresso.cheerup.dao;

import com.codepresso.cheerup.vo.FreeBoardReply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FreeBoardReplyMapper {
    int insertReply(FreeBoardReply freeBoardReply);

    List<FreeBoardReply> getAllReplyList(int boardNo);

    int deleteReply(int replyNo);
}
