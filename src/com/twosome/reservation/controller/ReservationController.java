/**
 * 파일명: ReservationController.java <br>
 * 작성자: Wholesome-Gee  <br>
 * 생성일: 2026-04-28 <br>
 * 설　명: 예약메뉴(예약하기, 예약조회, 예약취소) 실행 로직
 */
package com.twosome.reservation.controller;

import java.util.List;
import java.util.Scanner;
import com.twosome.member.domain.MemberVO;
import com.twosome.reservation.dao.ReservationDao;
import com.twosome.reservation.domain.ReservationVO;
import com.twosome.reservation.view.ReservationView;

public class ReservationController {

    private final ReservationDao resDao; 	
    private final ReservationView resView;
    private final Scanner scanner;
    private final String[] days = {"", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"};

    public ReservationController(Scanner scanner) {
        this.resDao = new ReservationDao();
        this.resView = new ReservationView();
        this.scanner = scanner;
    }

    /**
     * 기능: 예약하기 메인 로직
     * @param <MemberVO> member
     * 설명: selectDayView로부터 받은 사용자의 요일 입력값(dayIdx, 1)을 days의 인덱스로 활용<br>
     * 		요일을 변수에 담음. selectedDay=days[dayIdx] <br>
     * 		입력값을 인덱스로 활용하여 selectedDay에 days[dayIdx]를 담음 (ex. selectedDay = 월요일)<br>
     * 		member.getId()와 selectedDay를 사용하여 member가 selectedDay에 예약을 했는지 중복예약체크(중복아니면 null)<br>
     * 		중복이 아닐시, reservedSeats List에 해당 요일에 예약된 좌석 번호(int)를 담은 List를 받고,<br>
     * 		reservedSeats를 resView의 displaySeats에 보낸다.(좌석배치도 출력)<br>
     * 		이후, resView.selectSeatView(scanner)로 좌석 선택 view를 출력<br>
     * 		resView.selectSeatView(scanner)로 받은 좌석번호를 seatNum 변수에 담고,<br>
     * 		reservedSeats에 seatNum이 포함되어있는지 검사한다. 포함되어있다면 이미 예약된좌석이라는 메시지를 출력<br>
     * 		이후, member.getId(), selectedDay, seatNum를 활용해 Resevation 인스턴스를 만들고 파일에 저장
     */
    public void doReservation(MemberVO member) {
        // 1. 요일 선택
        int dayIdx = resView.selectDayView(scanner);
        String selectedDay = days[dayIdx];

        // 2. 중복 예약 확인 (해당 유저가 해당 요일에 이미 예약했는지)
        ReservationVO existReservation = resDao.getMyReservationByDay(member.getId(), selectedDay);
        if (existReservation != null) {
            System.out.printf("해당 요일은 이미 예약을 하셨습니다. 예약정보 : %s, %d번좌석\n", 
                               existReservation.getDay(), existReservation.getSeat());
            return;
        }

        // 3. 좌석 배치도 출력
        System.out.println("선택하신 요일은 \"" + selectedDay + "\"입니다.");
        List<Integer> reservedSeats = resDao.getReservedSeatsByDay(selectedDay);
        resView.displaySeats(reservedSeats);

        // 4. 좌석 선택 및 중복 체크
        int seatNum;
        while (true) {
            seatNum = resView.selectSeatView(scanner);
            if (reservedSeats.contains(seatNum)) {
                System.out.println("이미 예약된 좌석입니다.");
            } else {
                break;
            }
        }

        // 5. 예약 확정 및 저장
        ReservationVO newRes = new ReservationVO(member.getId(), selectedDay, seatNum);
        resDao.doSave(newRes);
        System.out.printf("예약이 완료되었습니다.\n예약정보 : %s, %d번좌석\n", selectedDay, seatNum);
    }
    
    /**
     * 기능: 예약조회 로직
     * @param <MemberVO> member
     * 한줄 설명: doInquiry에 MemberVO를 파라미터로 전달하면, 해당멤버의 예약정보를 출력
     * 로직 설명:  member의 id를 resDao.getMyReservations에 전달하여 member의 예약정보List를 myResList에 담는다.<br>
     * 			만약 myResList가 비었을 시, 예약내역이 없다고 출력<br>
     * 			예약내역이 있다면, myResList를 순회하여 모든 예약정보 출력 	  
     */
    public void doInquiry(MemberVO member) {
        System.out.println(member.getName() + "님의 예약정보는 아래와 같습니다.");
        List<ReservationVO> myResList = resDao.getMyReservations(member.getId());

        if (myResList.isEmpty()) {
            System.out.println("예약 내역이 없습니다.");
            return;
        }

        for (ReservationVO reservation : myResList) {
            System.out.println(reservation.toPrint());
        }
    }

    /**
     * 기능: 예약취소 로직
     * @param <MemberVO> member
     * 한줄 설명:  doCancel에 MemberVO를 파라미터로 전달하면, 해당멤버의 예약정보를 출력
     * 로직 설명:  member의 id를 resDao.getMyReservations에 전달하여 member의 예약정보List를 myResList에 담는다.<br>
     * 			만약 myResList가 비었을 시, 취소할 내역이 없다고 출력<br>
     * 			취소할 내역이 있다면, for 문으로 member의 예약 내역 출력
     * 			예약내역이 있다면, myResList를 순회하여 모든 예약정보 출력 	  
     */
    public void doCancel(MemberVO member) {
        List<ReservationVO> myResList = resDao.getMyReservations(member.getId());
        if (myResList.isEmpty()) {
            System.out.println("취소할 예약 내역이 없습니다.");
            return;
        }

        // 예약 내역을 출력해주기
        for (int i = 0; i < myResList.size(); i++) { 
            System.out.println((i + 1) + ". " + myResList.get(i).toPrint()); // 1.월요일 - 1번좌석   2.화요일 - 1번좌석
        }

        System.out.print("몇번을 취소하겠습니까? > ");
        try {
            int select = Integer.parseInt(scanner.nextLine());
            if (select > 0 && select <= myResList.size()) { // 입력값이 0보다 크고 예약내역수 이하일떄
                resDao.doDelete(myResList.get(select - 1));
                System.out.println("예약이 취소되었습니다.");
            } else {
                System.out.println("번호를 잘못 입력하셨습니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력 가능합니다.");
        }
    }

    /**
     * 예약 메뉴 실행 (반복)
     */
    public void run(MemberVO member) {
        while (true) {
            int menu = resView.reservationMenu(scanner, member.getName());
            if (menu == 1) doReservation(member);
            else if (menu == 2) doInquiry(member);
            else if (menu == 3) doCancel(member);
            else if (menu == 0) break; // 서비스 선택 종료 (로그아웃 개념)
            else System.out.println("잘못된 메뉴 선택입니다.");
        }
    }
}