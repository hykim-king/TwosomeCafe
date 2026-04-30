/**
 * 파일명: ReservationVO.java <br>
 * 작성자: Wholesome-Gee  <br>
 * 생성일: 2026-04-28 <br>
 * 설　명: 예약 정보에 필요한 변수, 생성자, getter, setter, toCsv, toString, equals, hashCode 등
 */
package com.twosome.reservation.domain;

import com.twosome.cmn.DTO;

public class ReservationVO extends DTO {
    private String id;   // 예약자 ID
    private String day;  // 요일
    private int seat;    // 좌석번호 (1~36)

    public ReservationVO() {
        super();
    }

    public ReservationVO(String id, String day, int seat) {
        super();
        this.id = id;
        this.day = day;
        this.seat = seat;
    }

    /**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the seat
	 */
	public int getSeat() {
		return seat;
	}

	/**
	 * @param seat the seat to set
	 */
	public void setSeat(int seat) {
		this.seat = seat;
	}
    

	/**
     * CSV 파일 저장 형식 (회원ID,요일,좌석번호)
     * "id,day,seat"
     */
    public String toCsv() {
        return String.format("%s,%s,%d", id, day, seat);
    }

    /**
     * 예약 조회 시 출력할 형식 (월요일 - 1번좌석)
     * "월요일 - 1번좌석"
     */
    public String toPrint() {
        return String.format("%s - %d번좌석", day, seat);
    }

    @Override
    public String toString() {
        return "ReservationVO [memberId=" + id + ", dayOfWeek=" + day + ", seatNum=" + seat + "]";
    }
}