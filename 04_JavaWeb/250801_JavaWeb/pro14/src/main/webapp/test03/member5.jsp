<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%>  
<c:set  var="id"  value="hong"  scope="page" />
<c:set  var="pwd"  value="1234"  scope="page" />
<c:set  var="name"  value="${'홍길동'}"  scope="page" /> 
<c:set  var="age"  value="${22}"  scope="page" />
<c:set  var="height"  value="${177}"  scope="page" />
<html>
<head>
<meta charset=”UTF-8">
<title>회원 정보 출력 창</title>
</head>
<body>
<!--서버 재시작 꼭 할고 실행  -->
<table align="center" border="1"  >
   <tr align="center"  bgcolor="lightgreen">
      <td width="7%" ><b>아이디</b></td>
      <td width="7%" ><b>비밀번호</b></td>
      <td width="7%" ><b>이름</b></td>
      <td width="7%"><b>나이</b></td>
      <td width="7%" ><b>키</b></td>
   </tr>
<c:choose> 
   <%-- <c:when test="${name==null}"> --%>
   <c:when test="${empty name}">
      <tr align="center">
         <td colspan=5 >이름을 입력하세요!!</td> 
      </tr>
   </c:when>
   <c:otherwise >
      <tr align="center">
         <td>${id}</td>
         <td>${pwd}</td>
         <td>${name}</td>
         <td>${age}</td>
         <td>${height}</td>
      </tr>
   </c:otherwise>
</c:choose>   
</table>
</body>
</html>
