/**
 * 파일명: MemberController.java <br>
 * 작성자: Wholesome-Gee  <br>
 * 생성일: 2026-04-28 <br>
 */
package com.twosome.member.controller;

import java.util.Scanner;
import com.twosome.member.domain.MemberVO;
import com.twosome.member.service.MemberService;
import com.twosome.member.service.MemberServiceImpl;
import com.twosome.member.view.MemberView;
// 예약 컨트롤러를 불러옵니다.
import com.twosome.reservation.controller.ReservationController;

public class MemberController {

    private final MemberService memberService;
    private final MemberView memberView;
    private final Scanner scanner;
    private final ReservationController resController; // 추가
    
    private MemberVO loggedInUser = null;

    public MemberController() {
        memberService = new MemberServiceImpl();
        memberView = new MemberView();
        scanner = new Scanner(System.in);
        // 예약 컨트롤러 객체를 생성합니다.
        resController = new ReservationController(scanner); 
    }
    /**
     * 회원가입 로직 (중복 시 반복)
     */
    private void doSignUp() {
        while (true) {
            MemberVO info = memberView.signUpView(scanner);
            int result = memberService.signUp(info);
            
            if (result == 2) { // 중복된 ID
                memberView.printMessage("중복된 id입니다.");
            } else if (result == 1) { // 성공
                memberView.printMessage("회원가입이 완료되었습니다. 로그인해주세요.");
                break; // 가입 완료 시 반복문 탈출
            } else {
                memberView.printMessage("회원가입 실패. 다시 시도해주세요.");
            }
        }
    }

    /**
     * 로그인 로직
     */
    private boolean doLogin() {
        while (true) {
            MemberVO loginInfo = memberView.loginInputView(scanner);
            int check = memberService.loginCheck(loginInfo.getId(), loginInfo.getPassword());
            
            if (check == 0) {
                memberView.printMessage("존재하지 않는 id입니다.");
                return false; // 초기 화면으로 돌아감
            } else if (check == 2) {
                memberView.printMessage("비밀번호가 틀렸습니다.");
                // 비밀번호가 틀리면 다시 로그인 페이지 첫 화면(ID입력부터) 반복
                return false; 
            } else if (check == 1) {
                this.loggedInUser = memberService.getMember(loginInfo.getId());
                memberView.printMessage("로그인 되었습니다.");
                memberView.printMessage("\n" + loggedInUser.getName() + "님 반갑습니다.");
                return true; // 로그인 성공
            }
        }
    }
    /**
     * 전체 프로그램 실행 흐름
     */
    public void run() {
        boolean isRunning = true;
        
        memberView.menuIntro();
        
        while (isRunning) {
            int menu = memberView.loginMenu(scanner);
            
            switch (menu) {
                case 1: // 로그인
                    if (doLogin()) {
                        // 로그인 성공 시 예약 시스템(ReservationController)의 run을 호출!
                        // 이때 로그인한 유저 정보(loggedInUser)를 넘겨줍니다.
                        resController.run(this.loggedInUser);
                    }
                    break;
                case 2: // 회원가입
                    doSignUp();
                    break;
                case 0: // 종료
                    memberView.printMessage("감사합니다. 다음에 또 이용해주세요.");
                    isRunning = false;
                    break;
                default:
                    memberView.printMessage("잘못된 입력입니다.");
                    break;
            }
        }
    }
    
    // doLogin() 내부에서 로그인 성공 시 return true를 반환하므로 
    // 자연스럽게 예약 시스템으로 넘어갑니다.
}