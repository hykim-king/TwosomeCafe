/**
 * 파일명: MemberView.java <br>
 * 작성자: Wholesome-Gee  <br>
 * 생성일: 2026-04-28 <br>
 */
package com.twosome.member.view;

import java.util.Scanner;
import com.twosome.member.domain.MemberVO;

public class MemberView {

	public void menuIntro () {
		
	System.out.println(" ######  ######## ##     ## ########  ##    ## ");
	System.out.println("##    ##    ##    ##     ## ##     ##  ##  ##  ");
	System.out.println("##          ##    ##     ## ##     ##   ####   ");
	System.out.println(" ######     ##    ##     ## ##     ##    ##    ");
	System.out.println("      ##    ##    ##     ## ##     ##    ##    ");
	System.out.println("##    ##    ##    ##     ## ##     ##    ##    ");
	System.out.println(" ######     ##     #######  ########     ##    ");
	System.out.println();
	System.out.println("  ######     ###    ######## ######## ");
	System.out.println(" ##    ##   ## ##   ##       ##       ");
	System.out.println(" ##        ##   ##  ##       ##       ");
	System.out.println(" ##       ##     ## ######   ######   ");
	System.out.println(" ##       ######### ##       ##       ");
	System.out.println(" ##    ## ##     ## ##       ##       ");
	System.out.println("  ######  ##     ## ##       ######## ");
	
	System.out.println("╔═══════════════════════════════════════════════╗");
	System.out.println("║              Twosome Study Cafe               ║");
	System.out.println("╚═══════════════════════════════════════════════╝");
	
	
	}		
    /**
     * 초기 시작 메뉴 출력 및 입력
     */
    public int loginMenu(Scanner scanner) {
        System.out.println("\n═════════════════════════════════════════════════");
        System.out.println("로그인을해주세요. >");
        System.out.println("0.종료, 1.로그인, 2.회원가입");
        System.out.println("═════════════════════════════════════════════════");
        System.out.print("선택 > ");
        
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // 숫자가 아닌 입력 시 예외 처리
        }
    }

    /**
     * 회원가입 정보를 입력받는 화면
     */
    public MemberVO signUpView(Scanner scanner) {
        System.out.println("\n회원가입페이지입니다.");
        System.out.print("id를 입력하세요. > ");
        String id = scanner.nextLine();
        
        System.out.print("비밀번호를 입력하세요. > ");
        String password = scanner.nextLine();
        
        System.out.print("이름을 입력하세요. > ");
        String name = scanner.nextLine();
        
        return new MemberVO(id, password, name);
    }

    /**
     * 로그인 정보를 입력받는 화면
     */
    public MemberVO loginInputView(Scanner scanner) {
        System.out.println("\n로그인페이지입니다.");
        System.out.print("id를 입력하세요. > ");
        String id = scanner.nextLine();
        
        System.out.print("비밀번호를 입력하세요. > ");
        String password = scanner.nextLine();
        
        return new MemberVO(id, password, ""); // 로그인은 ID와 비번만 필요
    }

    /**
     * 메시지 전용 출력 메서드
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}