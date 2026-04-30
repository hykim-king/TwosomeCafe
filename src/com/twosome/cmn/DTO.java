/**
 * 파일명: DTO.java <br>
 * 작성자: Wholesome-Gee  <br>
 * 생성일: 2026-04-28 <br>
 */
package com.twosome.cmn;

import java.io.Serializable;

/**
 * 모든 Value Object(VO)의 부모 클래스입니다.
 * 공통 필드(검색 구분, 검색어 등)를 관리합니다.
 */
public class DTO implements Serializable {

    private String searchDiv;  // 검색구분 (예: 10: ID, 20: 이름)
    private String searchWord; // 검색어
    private int no;            // 순번이나 페이지 번호 등으로 활용

    public DTO() {
    }

    public DTO(String searchDiv, String searchWord, int no) {
        this.searchDiv = searchDiv;
        this.searchWord = searchWord;
        this.no = no;
    }

    public String getSearchDiv() {
        return searchDiv;
    }

    public void setSearchDiv(String searchDiv) {
        this.searchDiv = searchDiv;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "DTO [searchDiv=" + searchDiv + ", searchWord=" + searchWord + ", no=" + no + "]";
    }
}