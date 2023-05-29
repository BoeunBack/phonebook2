package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PersonVo;

public class PersonDao {
	//필드
	//생성자
	//메소드-g,s
	//메소드-일반
	//personDao.personInsert(personVo);personVo주소가 담겨있는 것을 받는다.
	//personDao.getpersonList();
	
	//주소록 저장 메서드
	public void personInsert(PersonVo personVo) { //(자료형 주소)
		System.out.println("dao"+personVo.toString());
		// 0. import시키기 java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			 
			
			conn = DriverManager.getConnection(url, "c##webdb","webdb");
		// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "insert into person "; //여기서 붙으면서 띄어쓰이 오류가 많다.
			query += "values(seq_person_id.nextval, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);//문자열을 query로 만든다.
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());

			int count = pstmt.executeUpdate();//결과값 받아오기
		// 4.결과처리
			System.out.println(count+"건 등록성공");
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {

		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		}
	}
	
	//주소록 전체 데이터(LIST) 가져오는 메서드. personDao.getpersonList();
	public List<PersonVo> getPersonList() {
		//System.out.println("전체데이터가져오기");
		//DB에서 가져오는 코드
		// 0. import java.sql.*;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//1) 리스트 만들기
		List<PersonVo> pList = new ArrayList<PersonVo>(); //vo객체의 주소가 담길 리스트를 만듬.

		try {
			
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "c##webdb","webdb");
		// 3. SQL문 준비 / 바인딩 / 실행	
			String query = "";
			query += " select person_id, ";
			query += " name, ";
			query += " hp, ";
			query += " company ";
			query += " from person ";
			
			//System.out.println(query); //테스트
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery(); //꺼낸 결과값을 list에 담아서 
			
		// 4.결과처리:  rs 데이터를 list형태로 정리
			
			while(rs.next()) {
			
			int personID = rs.getInt("person_id");
			String name = rs.getString("name");
			String hp = rs.getString("hp");
			String company = rs.getString("company");
			//System.out.println(personID); //테스트
			//꺼낸 객체를 Vo에 담음.
			PersonVo personVo = new PersonVo(personID, name, hp, company);
			//System.out.println(personVo);//테스트
			pList.add(personVo);//add()메소드를 사용해서 리스트에 Vo를 담는다.
			
			} 			
			System.out.println(pList.toString());
			rs.next();//이러면 커서가 하나 내려감.
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {	
			System.out.println("error:" + e);
		} finally {

		// 5. 자원정리

			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			System.out.println("error:" + e);
			}
			
		}return pList;
	
	}
}
