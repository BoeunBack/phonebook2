<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>전화번호 리스트</h1>
   <p>입력한 정보 내역입니다.</p>

   <!-- 메일정보 리스트 -->
   <!-- 반복문 시작 -->
   <c:forEach items="${personList}" var="personVo">
      <table border="1">
         <colgroup>
            <col style="width: 120px;">
            <col style="width: 170px;">
         </colgroup>
         <tbody>
            <tr>
               <td>이름(name)</td>
               <td>${personVo.personID}</td>
            </tr>
            <tr>
               <td>핸드폰(hp)</td>
               <td>${personVo.hp}</td>
            </tr>
            <tr>
               <td>회사(company)</td>
               <td>${personVo.company}</td>
            </tr>
            <tr>
               <td><a href="/phonebook2/PhoneController?action=">수정</a></td>
               <td><a href="/phonebook2/PhoneController?action=delete">삭제</a></td>
            </tr>
         </tbody>
      </table>
      <br>
   </c:forEach>
   <!-- 반복문 끝 -->
   <p>
      <a href="/phonebook2/PhoneController?action=wform">추가번호 등록</a>
   </p>
   <br>
</body>
</html>