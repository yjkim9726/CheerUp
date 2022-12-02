package com.codepresso.cheerup.service;

import com.codepresso.cheerup.dao.FreeBoardMapper;
import com.codepresso.cheerup.vo.FreeBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FreeBoardService {
    private final FreeBoardMapper freeBoardMapper;

    //전체 조회
    public List<FreeBoard> getAllFreeBoardList() {
        return freeBoardMapper.getAllFreeBoardList();
    }

    //글 하나 조회
    public FreeBoard getFreeBoardDetail(int boardNo) { return freeBoardMapper.getFreeBoardDetail(boardNo); }

    //글 생성
    public int insertFreeBoard(FreeBoard freeBoard) { return freeBoardMapper.insertFreeBoard(freeBoard); }

    //글 수정
    public int updateFreeBoard(FreeBoard freeBoard) { return freeBoardMapper.updateFreeBoard(freeBoard); }

    //글 삭제
    public int deleteFreeBoard(int boardNo) { return freeBoardMapper.deleteFreeBoard(boardNo); }


}
