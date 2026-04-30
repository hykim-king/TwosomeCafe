/**
 * 파일명: WorkDiv.java <br>
 * 작성자: Wholesome-Gee  <br>
 * 생성일: 2026-04-28 <br>
 */
package com.twosome.cmn;

import java.util.List;

/**
 * 모든 DAO의 표준 CRUD 메서드를 정의하는 인터페이스입니다.
 * @param <T> 작업할 데이터의 타입 (예: MemberVO, ReservationVO)
 */
public interface WorkDiv<T> {
    
    /**
     * 데이터를 저장(등록)합니다.
     * @param param 저장할 객체
     * @return 1: 성공, 0: 실패, 2: 이미 존재함
     */
    int doSave(T param);
    
    /**
     * 데이터를 수정합니다.
     * @param param 수정할 정보가 담긴 객체
     * @return 1: 성공, 0: 실패
     */
    int doUpdate(T param);
    
    /**
     * 데이터를 삭제합니다.
     * @param param 삭제할 대상 정보
     * @return 1: 성공, 0: 실패
     */
    int doDelete(T param);
    
    /**
     * 단건(하나)의 정보를 조회합니다.
     * @param param 조회할 조건(ID 등)이 담긴 객체
     * @return 조회된 객체
     */
    T doSelectOne(T param);
    
    /**
     * 목록을 조회합니다.
     * @param param 검색 조건
     * @return 조회된 결과 리스트
     */
    List<T> doRetrieve(DTO param);
}