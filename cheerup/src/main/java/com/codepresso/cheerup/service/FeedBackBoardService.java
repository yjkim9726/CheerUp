package com.codepresso.cheerup.service;

import com.codepresso.cheerup.dao.FeedBackBoardMapper;
import com.codepresso.cheerup.vo.FeedBackBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedBackBoardService {
    private final FeedBackBoardMapper feedBackBoardMapper;

    //전체 조회
    public List<FeedBackBoard> getAllFeedBackBoardList() {
        return feedBackBoardMapper.getAllFeedBackBoardList();
    }

    //글 하나 조회
    public FeedBackBoard getFeedBackBoardDetail(int boardNo) { return feedBackBoardMapper.getFeedBackBoardDetail(boardNo); }

    //글 생성
    public int insertFeedBackBoard(FeedBackBoard feedBackBoard) { return feedBackBoardMapper.insertFeedBackBoard(feedBackBoard); }

    //글 수정
    public int updateFeedBackBoard(FeedBackBoard feedBackBoard) { return feedBackBoardMapper.updateFeedBackBoard(feedBackBoard); }

    //글 삭제
    public int deleteFeedBackBoard(int boardNo) { return feedBackBoardMapper.deleteFeedBackBoard(boardNo); }
}
