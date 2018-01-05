<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
body, pre {
	font-family: verdana
}
thead {
	background-color : powderblue
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/header.jsp" %>
	<h2>Zarządzanie użytkownikami</h2>
	<a href="AdminUsers?action=add&idUser=${user.id}">Dodaj użytkownika</a>
	<br>
	<hr>
	<br>
	<table border="1" style="text-align: center">
		<thead>
		
			<tr>
			
				<td><b>Imię i nazwisko</b></td>
				<td><b>Email</b></td>
				<td><b>Nr grupy</b></td>
				<td><b>Akcje</b></td>
				
			</tr>
			
		</thead>
		<tbody>
			<c:forEach items="${adminUsers}" var="user">
				<tr>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td>${user.person_group_id}</td>
					<td><pre>  <a href="AdminUsers?action=edit&idUser=${user.id}">Edytuj</a> <a href="AdminUsers?action=del&idUser=${user.id}">Usuń</a>	</pre></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>