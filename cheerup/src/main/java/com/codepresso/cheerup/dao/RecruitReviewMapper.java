package com.codepresso.cheerup.dao;

import com.codepresso.cheerup.vo.RecruitReview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecruitReviewMapper {
    List<RecruitReview> getAllRecruitReview(int startIndex, int pageSize);
    RecruitReview getOneRecruitReview(int boardNo);

    int enrollReview(RecruitReview review);

    int findAllCnt();

    int findSearchCnt(RecruitReview review);

    List<RecruitReview> getSearchRecruitReview(RecruitReview review);

    int updateReview(RecruitReview review);
    int deleteReview(int boardNo);
}
