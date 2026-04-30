/**
 * 파일명: ReservationView.java <br>
 * 작성자: Wholesome-Gee  <br>
 * 생성일: 2026-04-28 <br>
 */
package com.twosome.reservation.view;

import java.util.List;
import java.util.Scanner;

public class ReservationView {

    /**
     * 메인 서비스 선택 메뉴 (로그인 성공 후)
     */
    public int reservationMenu(Scanner scanner, String name) {
        System.out.println("\n====================================");
        System.out.println(name + "님 반갑습니다. 서비스를 선택하세요. > ");
        System.out.println("0.로그아웃 1.예약하기 2.예약조회 3.예약취소");
        System.out.println("====================================");
        System.out.print("선택 > ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * 요일 선택 메뉴
     */
    public int selectDayView(Scanner scanner) {
        while (true) {
            System.out.println("\n예약하기 페이지입니다. 예약하실 요일을 선택해주세요. > ");
            System.out.println("1.월요일, 2.화요일, 3.수요일, 4.목요일, 5.금요일, 6.토요일, 7.일요일");
            System.out.print("입력 > ");
            try {
                int day = Integer.parseInt(scanner.nextLine());
                if (day >= 1 && day <= 7) return day;
                System.out.println("1번부터 7번중에 입력해주세요.");
            } catch (NumberFormatException e) {
                System.out.println("숫자로 입력해주세요.");
            }
        }
    }

    /**
     * 6행 6열 좌석 배치도 출력
     * @param reservedSeats 이미 예약된 좌석 번호 목록
     */
    public void displaySeats(List<Integer> reservedSeats) {
        System.out.println("\n      [ 좌석 배치도 ]");
        int seatNum = 1;
        String[] rowLabels = {"　　(1번→6번)　", "　(7번→12번)　", "(13번→18번)　", "(19번→24번)　", "(25번→30번)　", "(31번→36번)　"};

        for (int i = 0; i < 6; i++) {
            System.out.print(rowLabels[i] + " ");
            for (int j = 0; j < 6; j++) {
                // 현재 번호가 예약 리스트에 있으면 ■, 없으면 □
                if (reservedSeats.contains(seatNum)) {
                    System.out.print("■ ");
                } else {
                    System.out.print("□ ");
                }
                seatNum++;
            }
            System.out.println();
        }
    }

    /**
     * 좌석 번호 입력받기
     */
    public int selectSeatView(Scanner scanner) {
        while (true) {
            System.out.print("\n예약하실 좌석을 선택해주세요. (1~36번좌석) > ");
            try {
                int seat = Integer.parseInt(scanner.nextLine());
                if (seat >= 1 && seat <= 36) return seat;
                System.out.println("1번부터 36번중에 입력해주세요.");
            } catch (NumberFormatException e) {
                System.out.println("숫자로 입력해주세요.");
            }
        }
    }
}