package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PersonDao;
import com.javaex.vo.PersonVo;

@WebServlet("/PhoneController")
public class PhoneController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// action 파라미터 값 꺼내기(request에 메소드가 다 들어있으므로 request에서 꺼내쓰면 된다)
		String actionName = request.getParameter("action"); // 파라미터 값을 actionName에 담음. 값이 담겼는지 꼭 확인해라.
		System.out.println(actionName);

		if ("wform".equals(actionName)) {
			// action=wfomf일때 해야되는 작업.
			System.out.println("wform");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeform.jsp");// 이 jsp에 request, response를
																							// 넘긴다는것
			rd.forward(request, response); // 이렇게 쓰면 포워드가 되는구나 알면됨.

		} else if ("insert".equals(actionName)) {// acton=insert일때 해야되는 작업으로 구분

			System.out.println("insert");

			// 3개의 파라미터 값을 꺼냄
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");

			System.out.println(name);
			System.out.println(hp);
			System.out.println(company);

			// 데이터를 vo에 담는다(묶음)--> DAO에 1개로 넘기려고
			PersonVo personVo = new PersonVo(name, hp, company);// 이렇게하면 메모리에 올라가는 것.
			System.out.println(personVo.toString());// vo 테스트

			// DB저장
			PersonDao personDao = new PersonDao(); // 주소값을 담는
			personDao.personInsert(personVo);

			// 리스트 가져오기해야하는데 -->독립된 기능있다 action=list 리다이렉트로 한다.
			response.sendRedirect("/phonebook2/PhoneController?action=list");//url을 적어준다.몰래넘겨주는
			
		} else if ("list".equals(actionName)) {
			System.out.println("리스트");
			//DAO를 이용해서 DB에서 데이터 가져오기
			PersonDao personDao = new PersonDao();
			List<PersonVo> personList = personDao.getPersonList();
			//System.out.println(personList.toString());//테스트
			
			//request객체에 리스트를 추가한다 Attribute영역에
			request.setAttribute("personList", personList);//("이름", )은 attrubute영역에 pList가 들어가는것
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");//하면 꾸러미가 가겠지 갔으면 개가 응답해야함.
			rd.forward(request, response);
		} else {
			System.out.println("나머지");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}
}
