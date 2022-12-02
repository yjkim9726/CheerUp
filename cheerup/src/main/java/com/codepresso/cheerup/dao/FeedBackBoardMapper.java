package com.codepresso.cheerup.dao;

import com.codepresso.cheerup.vo.FeedBackBoard;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FeedBackBoardMapper {
    List<FeedBackBoard> getAllFeedBackBoardList();
    FeedBackBoard getFeedBackBoardDetail(int boardNo);

    int insertFeedBackBoard(FeedBackBoard feedBackBoard);
    int updateFeedBackBoard(FeedBackBoard feedBackBoard);
    int deleteFeedBackBoard(int boardNo);

}
