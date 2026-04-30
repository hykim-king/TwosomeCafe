/**
 * 파일명: ReservationDao.java <br>
 * 작성자: Wholesome-Gee  <br>
 * 생성일: 2026-04-28 <br>
 * 설　명: reservation.csv에 접근해서 CRUD처리하는 클래스
 */
package com.twosome.reservation.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.twosome.cmn.DTO;
import com.twosome.cmn.PLogger;
import com.twosome.cmn.WorkDiv;
import com.twosome.reservation.domain.ReservationVO;

public class ReservationDao implements WorkDiv<ReservationVO>, PLogger {
	
	// PATH = reservation.csv 파일경로
    private final String PATH = "D:\\CJFS_20260324\\01_JAVA1\\WORKSPACE\\StudyCafe\\data\\reservation.csv";
    // reservation List
    public static List<ReservationVO> reservationList = new ArrayList<>();

    public ReservationDao() {
    	// ReservationDao 호출 시 reservation.csv를 읽옴
        readFile(PATH);
    }
    
    /**
     * csv 파일을 읽고, reservationList에 담기
     */
    private void readFile(String path) {
    	if (path == null || path.trim().isEmpty()) return;
    	
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            
        	String line;
//            reservationList.clear();
            while ((line = br.readLine()) != null) {  //line = 데이터 한줄
                String[] data = line.split(",");	  // data = ["pcwk01", "월요일", "1"];
                if (data.length == 3) {
                    reservationList.add(new ReservationVO(data[0], data[1], Integer.parseInt(data[2])));
                }
            }
        } catch (IOException e) {
            log.error("파일 읽기 오류: {}",e.getMessage());
        } catch (Exception e) {
        	log.error("오류가 발생했습니다.: {}",e.getMessage());
        }
    }
    
    /**
     * csv파일 쓰기 
     */
    private void writeFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH))) {
            for (ReservationVO vo : reservationList) {
                bw.write(vo.toCsv());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("예약 파일 쓰기 실패");
        }
    }
    
    
    /**
     * 예약 저장
     */
    @Override // C
    public int doSave(ReservationVO reservation) {
        int flag = reservationList.add(reservation)==true ? 1: 0;
        writeFile();
        return flag;
    }

    /**
     * 기능: 요일을 입력했을때, 이 멤버가 해당 요일에 이미 예약을 했는지 확인
     * 설명: id, day를 파라미터로 받고 
     * 		예약정보 리스트를 순회하고 
     * 		각 예약정보의 id와 day가 파라미터의 id 와 day랑 일치한지 equals로 검사   
     * 		일치 시 해당 예약정보(ResevationVO)를 반환
     * 		일치 하는게 없을 시 null 리턴
     * @return ReservationVO
     */
    public ReservationVO getMyReservationByDay(String id, String day) {  // id, day를 파라미터로 받고 
        for (ReservationVO reservation: reservationList) {
            if (reservation.getId().equals(id) && reservation.getDay().equals(day)) { // 유효성검사 
                return reservation; // 일치하는 reservationVO 반환
            }
        }
        return null;
    }

    /**
     * 기능: 입력받은 요일을 이용해 요일 별 예약된 좌석 번호 List 가져오기
     * 설명: day를 파라미터로 받고
     * 		예약정보 리스트를 순회하고
     * 		각 예약정보의 day와 파라미터의 day가 일치 하면
     * 		해당 예약정보(ReservationVO)의 seat(int)를 seats List에 추가 후 seats List 반환
     * @return List<Integer> seats
     */
    public List<Integer> getReservedSeatsByDay(String day) {
        List<Integer> seats = new ArrayList<>();
        for (ReservationVO reservation : reservationList) {
            if (reservation.getDay().equals(day)) {
                seats.add(reservation.getSeat());
            }
        }
        return seats;
    }

    /**
     * 기능: 특정 멤버의 예약 리스트 조회 
     * 설명: userId를 파라미터로 받고
     * 		예약정보 리스트를 순회하고
     * 		각 예약정보의 id와 userId가 일치하면
     * 		해당 예약정보(ReservationVO)를 newList List에 추가 후 newList 반환
     * @return List<ReservationVO> newList
     */
    public List<ReservationVO> getMyReservations(String userId) {
        List<ReservationVO> newList = new ArrayList<>();
        for (ReservationVO reservation : reservationList) {
            if (reservation.getId().equals(userId)) {
            	newList.add(reservation);
            }
        }
        return newList;
    }

    /**
     * 기능: 특정 멤버의 예약 리스트 조회 
     * 설명: reservation(예약정보)을 파라미터로 받고
     * 		예약정보 리스트에서 reservation을 삭제했을때, 
     * 		삭제성공(true)이 뜨면, 파일을 다시 쓰고, flag를 1로 반환
     * 		삭제실패(false)일 시 flag를 0으로 반환
     * @return List<ReservationVO> newList
     */
    @Override
    public int doDelete(ReservationVO reservation) {
    	int flag = 0;
        if (reservationList.remove(reservation)) {
            writeFile();
            flag = 1;
            return flag;
        }
        return flag;
    }
    

	@Override
	public int doUpdate(ReservationVO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ReservationVO doSelectOne(ReservationVO param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReservationVO> doRetrieve(DTO param) {
		// TODO Auto-generated method stub
		return null;
	}
}