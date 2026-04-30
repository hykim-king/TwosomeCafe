/**
 * 파일명: MemberServiceImpl.java <br>
 * 작성자: Wholesome-Gee  <br>
 * 생성일: 2026-04-28 <br>
 */
package com.twosome.member.service;

import com.twosome.member.dao.MemberDao;
import com.twosome.member.domain.MemberVO;

public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;

    public MemberServiceImpl() {
        // 창고지기(DAO)를 고용합니다.
        memberDao = new MemberDao();
    }

    @Override
    public int signUp(MemberVO vo) {
        // DAO에게 저장을 시키고 결과를 그대로 리턴합니다.
        // (이미 DAO의 doSave에서 중복 체크를 하도록 설계했습니다.)
        return memberDao.doSave(vo);
    }

    @Override
    public int loginCheck(String id, String password) {
        MemberVO inVO = new MemberVO();
        inVO.setId(id);
        
        // 1. 존재하는 ID인지 확인
        MemberVO user = memberDao.doSelectOne(inVO);
        if (user == null) {
            return 0; // 존재하지 않는 ID
        }
        
        // 2. 비밀번호가 맞는지 확인
        if (user.getPassword().equals(password)) {
            return 1; // 로그인 성공
        } else {
            return 2; // 비밀번호 틀림
        }
    }

    @Override
    public MemberVO getMember(String id) {
        MemberVO inVO = new MemberVO();
        inVO.setId(id);
        return memberDao.doSelectOne(inVO);
    }
}