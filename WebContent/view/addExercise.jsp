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
	
	<c:if test="${idExercise != null}">
		<form action="AdminExercises" method="post">
		<h2>Edycja zadania:</h2>
			<label>Nazwa zadania:
			<input type="text" name="addTitle" value="${title}">
			</label><br>
			<label>Opis zadania:
			<input type="text" name="addDescription" value="${description}">
			</label><br>
			<input type="hidden" name="idExercise" value="${idExercise}">
				<input type="submit">
		</form>
	</c:if>
	<c:if test="${idExercise == null}">
		<form action="AdminExercises" method="post">
		<h2>Dodaj zadanie:</h2>
			<label>Nazwa zadania:
			<input type="text" name="addTitle">
			</label><br>
			<label>Opis zadania:
			<input type="text" name="addDescription">
			</label><br>
			<input type="submit">
		</form>
	</c:if>
</body>
</html>