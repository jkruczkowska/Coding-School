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
	<h2>Zarządzanie grupami użytkowników</h2>
	<a href="AdminGroups?action=add">Dodaj grupę</a>
	<br>
	<hr>
	<br>
	<table border="1" style=text-align:center>
		<thead>
			<tr>
				<td><b>Nr grupy</b></td>
				<td><b>Nazwa grupy</b></td>
				<td><b>Akcje</b></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${adminGroups}" var="group">
				<tr>
					<td>${group.id}</td>
					<td>${group.name}</td>
					<td><pre>  <a href="AdminGroups?action=edit&idGroup=${group.id}">Edytuj</a> <a href="AdminGroups?action=del&idGroup=${group.id}">Usuń</a>	</pre></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>