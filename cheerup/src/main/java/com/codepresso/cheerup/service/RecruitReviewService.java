package com.codepresso.cheerup.service;

import com.codepresso.cheerup.dao.RecruitReviewMapper;
import com.codepresso.cheerup.vo.Pagination;
import com.codepresso.cheerup.vo.RecruitReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitReviewService {
// 예전에는 속도 implements로 나눴지만 나눴지만 지금은 상관이 없어서 한파일로 해도됨

    private final RecruitReviewMapper recruitReviewMapper;

    public List<RecruitReview> getAllRecruitReview(int startIndex, int pageSize) {
        return recruitReviewMapper.getAllRecruitReview(startIndex, pageSize);
    }

    public RecruitReview getOneRecruitReview(int boardNo) {return recruitReviewMapper.getOneRecruitReview(boardNo);}

    public int enrollReview(RecruitReview review) {return recruitReviewMapper.enrollReview(review);}

    public int findAllCnt() {return recruitReviewMapper.findAllCnt();}

    public int findSearchCnt(RecruitReview review) {return recruitReviewMapper.findSearchCnt(review);}

    public List<RecruitReview> getSearchRecruitReview(RecruitReview review) {
        return recruitReviewMapper.getSearchRecruitReview(review);
    }
    public int updateReview(RecruitReview review){return recruitReviewMapper.updateReview(review);}
    public int deleteReview(int boardNo){return recruitReviewMapper.deleteReview(boardNo);}
}
