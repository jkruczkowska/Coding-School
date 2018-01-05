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
	<table border="1">
		<thead>
			<tr>
				<td><b>Data dodania</b></td>
				<td><b>Updated</b></td>
				<td><b>Opis</b></td>
				<td><b>Exercise_id</b></td>
				<td><b>User_id</b></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${solution.created}</td>
				<td>${solution.updated}</td>
				<td>${solution.description}</td>
				<td>${solution.exercise_id}</td>
				<td>${solution.users_id}</td>
			</tr>
		</tbody>
	</table>
</body>
</html>