/**
 * 파일명: MemberMain.java <br>
 * 작성자: Wholesome-Gee  <br>
 * 생성일: 2026-04-28 <br>
 */
package com.twosome.member.main;

import com.twosome.member.controller.MemberController;

/**
 * Twosome Cafe 스터디카페 예약 시스템의 시작점입니다.
 */
public class MemberMain {

	public static void main(String[] args) {
		// 1. 모든 흐름을 관리하는 컨트롤러 객체 생성
		MemberController controller = new MemberController();
		
		// 2. 프로그램 실행 (무한 루프 진입)
		controller.run();
	}

}