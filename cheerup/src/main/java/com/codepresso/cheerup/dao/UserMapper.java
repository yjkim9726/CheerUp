package com.codepresso.cheerup.dao;

import com.codepresso.cheerup.vo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    //SpringSecurity : 회원 조회하기
    Optional<User> findById(String id);

    //중복 id체크
    int idChk(String id);

    //회원가입
    int insertMember(User user);

    //로그인
    String loginCheck(String id, String password);


    String chkUserId(User user);

    String chkUserPw(User user);

    int modifyPw(User user);
}
