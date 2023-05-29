package com.javaex.vo;

public class PersonVo {
	//필드: 접근못하게 보호함. 그래서 거의 private로 만듬.
	private int personid;
	private String name;
	private String hp;
	private String company;
	
	
	public PersonVo() {//생성자
		
	}

	public PersonVo(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	public PersonVo(int personid, String name, String hp, String company) {
		super();
		this.personid = personid;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	//메소드-겟, 셋
	public int getPersonid() {
		return personid;
	}

	public void setPersonid(int personid) {
		this.personid = personid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
//메소드 -일반: 테스트용으로 찍어봐야하니까 tostring이 필요.
	@Override
	public String toString() {
		return "PersonVo [personid=" + personid + ", name=" + name + ", hp=" + hp + ", company=" + company + "]";
	}
}

	