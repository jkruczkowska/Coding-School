<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
body {
	font-family: verdana
}
thead {
	background-color : powderblue
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/header.jsp" %>
<h2>Lista grup:</h2>
	<table border="1">
		<thead>
			<tr>
				<td><b>Numer grupy</b></td>
				<td><b>Nazwa grupy</b></td>
				<td><b>Szczegóły</b></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${groups}" var="group">
				<tr>
					<td>${group.id}</td>
					<td>${group.name}</td>
					<td><a href="ShowGroupUsers?groupId=${group.id}">Lista użytkowników</a>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>