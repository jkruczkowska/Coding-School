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
	<h2>Zarządzanie zadaniami</h2>
	<a href="AdminExercises?action=add">Dodaj zadanie</a>
	<br>
	<hr>
	<br>
	<table border="1" style="text-align: center">
		<thead>
			<tr>
				<td><b>Nr</b></td>
				<td><b>Tytuł</b></td>
				<td><b>Opis</b></td>
				<td><b>Akcje</b></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${adminExercises}" var="exercise">
				<tr>
					<td>${exercise.id}</td>
					<td>${exercise.title}</td>
					<td>${exercise.description}</td>
					<td><pre>  <a href="AdminExercises?action=edit&idExercise=${exercise.id}">Edytuj</a> <a href="AdminExercises?action=del&idExercise=${exercise.id}">Usuń</a>	</pre></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>