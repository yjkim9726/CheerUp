package com.codepresso.cheerup.service;

import com.codepresso.cheerup.dao.FreeBoardReplyMapper;
import com.codepresso.cheerup.vo.FreeBoardReply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FreeBoardReplyService {
    private final FreeBoardReplyMapper freeBoardReplyMapper;

    public int insertReply(FreeBoardReply freeBoardReply) { return freeBoardReplyMapper.insertReply(freeBoardReply); }

    public List<FreeBoardReply> getAllReplyList(int boardNo) { return freeBoardReplyMapper.getAllReplyList(boardNo); }

    public int deleteReply(int replyNo) { return freeBoardReplyMapper.deleteReply(replyNo); }
}
