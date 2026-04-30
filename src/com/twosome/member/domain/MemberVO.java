/**
 * 파일명: MemberVO.java <br>
 * 작성자: Wholesome-Gee  <br>
 * 생성일: 2026-04-28 <br>
 * 설　명: 회원 정보에 필요한 변수, 생성자, getter, setter, toString, equals, hashCode 등
 */
package com.twosome.member.domain;

import java.util.Objects;
import com.twosome.cmn.DTO;

/**
 * 회원 정보에 필요한 변수, 생성자, getter, setter, toString, equals, hashCode 등
 */
public class MemberVO extends DTO {
	private String id;       
    private String password; 
    private String name;     

    public MemberVO() {
        super();
    }

    public MemberVO(String id, String password, String name) {
        super();
        this.id = id;
        this.password = password;
        this.name = name;
    }
    
    

    /**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * csv파일에 전달할 데이터 포맷 (ex. pcwk01,1234,이상무01)<br>
     * member.toCsv();
     */
    public String toCsv() {
        return String.format("%s,%s,%s", id, password, name);
    }


    @Override
	public int hashCode() {
		return Objects.hash(id);
	}
    
    /**
     * id 값이 같으면 같은객체로 인식 <br>
     * member.equals(member2.id);
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		return Objects.equals(id, other.id);
	}

	/**
	 * 디버깅용 toString
	 */
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", toString()=" + super.toString()
				+ "]";
	}

}