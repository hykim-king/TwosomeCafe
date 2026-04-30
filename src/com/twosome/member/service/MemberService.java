/**
 * 파일명: MemberService.java <br>
 * 작성자: Wholesome-Gee  <br>
 * 생성일: 2026-04-28 <br>
 */
package com.twosome.member.service;

import com.twosome.member.domain.MemberVO;

public interface MemberService {
    /** 회원가입 */
    int signUp(MemberVO vo);
    
    /** 로그인 체크 (0: ID없음, 1: 성공, 2: 비번틀림) */
    int loginCheck(String id, String password);
    
    /** 로그인 성공 시 회원 정보 가져오기 */
    MemberVO getMember(String id);
}