package com.codepresso.cheerup.dao;

import com.codepresso.cheerup.vo.FreeBoard;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //Mapper로 등록시킨다
@Repository
public interface FreeBoardMapper {

    List<FreeBoard> getAllFreeBoardList();
    FreeBoard getFreeBoardDetail(int boardNo);

    int insertFreeBoard(FreeBoard freeBoard);
    int updateFreeBoard(FreeBoard freeBoard);
    int deleteFreeBoard(int boardNo);


}
