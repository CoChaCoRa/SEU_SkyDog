<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% System.out.println("hello  jsp"); %>
      <h1>这是 一个 jsp</h1>
       <h1>欢迎  ${username}</h1>
        <h1>有一位同学 名字叫   ${s.name}  它的年龄是  ${s.age}</h1>
        他的银行卡有：
        <br>
        
        ${card[0]}<br>
        ${card[1]}<br>
        ${card[2]}<br>
        ${card[3]}
        <hr>
        ========================================================================================
        <table>
        <tr><th>姓名</th><th>年龄</th><th>性别</th></tr>
     <c:forEach  items="${students}"  var="student">
         <tr>
         <td>${student.name}</td>
         <td>${student.age}</td>
         <td>${student.gender}</td>
     </c:forEach>   
        </table>
        
        
        
        
</body>
</html>