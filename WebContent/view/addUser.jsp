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
	background-color: powderblue
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/header.jsp"%>
	<c:if test="${idUser != null}">
		<form action="AdminUsers" method="post">
			<h2>Edycja danych użytkownika:</h2>
			<label>Imię i nazwisko: <input type="text" name="addName"
				value="${name}">
			</label><br> <label>Email: <input type="text" name="addEmail"
				value="${email}">
			</label><br> <label>Hasło: <input type="text" name="addPassword"
				value="${password}">
			</label><br> <label>Id grupy: <input type="text"
				name="addIdGroup" value="${idGroup}">
			</label><br> <input type="hidden" name="idUser" value="${idUser}">
			<input type="submit">
		</form>
	</c:if>
	<c:if test="${idUser == null}">
		<form action="AdminUsers" method="post">
			<h2>Nowy użytkownik:</h2>
			<label>Imię i nazwisko: <input type="text" name="addName">
			</label><br> <label>Email: <input type="text" name="addEmail">
			</label><br> <label>Hasło: <input type="text" name="addPassword">
			</label><br> <label>Id grupy:
			<select>
			<c:forEach items="${groupList}" var="group">
				<option value="${group.id}">${group.id}</option>
				</c:forEach>
			</select>
				</label><br>
			<input type="submit">
		</form>
	</c:if>
</body>
</html>