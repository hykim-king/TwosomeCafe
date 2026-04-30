/**
 * 파일명: MemberDao.java <br>
 * 작성자: Wholesome-Gee  <br>
 * 생성일: 2026-04-28 <br>
 * 설　명: member.csv에 접근해서 CRUD처리하는 클래스
 */
package com.twosome.member.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.twosome.cmn.DTO;
import com.twosome.cmn.PLogger;
import com.twosome.cmn.WorkDiv;
import com.twosome.member.domain.MemberVO;
public class MemberDao implements WorkDiv<MemberVO>, PLogger {

    // PATH = member.csv 파일경로
    private final String PATH = "D:\\CJFS_20260324\\01_JAVA1\\WORKSPACE\\StudyCafe\\data\\member.csv";
    // member List
    public static List<MemberVO> members = new ArrayList<>();

    public MemberDao() {
        // MemberDao 호출 시 member.csv를 읽옴
        readFile(PATH);
    }

    /**
     * csv 파일을 읽고, members List에 담기
     */
    private void readFile(String path) {
        if (path == null || path.trim().isEmpty()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            
//            members.clear(); // 기존 리스트 초기화
            while ((line = br.readLine()) != null) { //line = 데이터 한줄
                String[] data = line.split(",");	 // data = ["pcwk01","1234","이상무01"];
                if (data.length == 3) {
                    members.add(new MemberVO(data[0], data[1], data[2])); // members에 member추가 
                }
//                log.debug("{}",members);
            }
        } catch (IOException e) {
            log.error("파일 읽기 오류: {}",e.getMessage());
        } catch (Exception e) {
        	log.error("오류가 발생했습니다.: {}",e.getMessage());
        }
    }

    /**
     * members List에 member 데이터를 csv에 담기
     */
    public void writeFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH))) {
            for (MemberVO member : members) {
                bw.write(member.toCsv()); // toCsv로 데이터 포맷화("pcwk01","1234","이상무01")해서 csv에 추가 
                bw.newLine();
            }
            
        } catch (IOException e) {
        	log.error("파일 쓰기 오류: {}",e.getMessage());
        } catch (Exception e) {
        	log.error("오류가 발생했습니다.: {}",e.getMessage());
        }
    }
    
    /**
     * ID 중복 체크 및 ID 존재 여부 확인
     */
    public boolean isExistsId(String id) {
        for (MemberVO member : members) {
            if (member.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    
    /**
     * 회원가입 정보 저장 (members에 newMember 추가)
     */
    @Override // C
    public int doSave(MemberVO newMember) {
    	int flag = 0;
        if (isExistsId(newMember.getId())) { // 중복확인
        	flag = 2; // 2: 이미 존재하는 ID
            return flag; 
        }
        
        flag = members.add(newMember) == true ? 1: 0;  
        if(flag==1) writeFile();  // members에 newMember추가 후, csv에저장
        
        return flag; // 성공
    }
    
    
    /**
     * 단건 조회 (로그인 시 비밀번호 확인용으로도 사용) 
     */
    @Override // R
    public MemberVO doSelectOne(MemberVO targetMember) {
        for (MemberVO member : members) {
            if (member.getId().equals(targetMember.getId())) {
                return member;
            }
        }
        return null;
    }
    
    // WorkDiv 인터페이스의 메서드를 오버라이딩 했지만, 사용은 안함
    @Override
	public List<MemberVO> doRetrieve(DTO param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override // U
	public int doUpdate(MemberVO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override // D
	public int doDelete(MemberVO param) {
		// TODO Auto-generated method stub
		return 0;
	}
}