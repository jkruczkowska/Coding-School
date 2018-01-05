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
	<c:if test="${idGroup != null}">
		<form action="AdminGroups" method="post">
		<h2>Edycja grupy:</h2>
			<label>Nazwa grupy:
			<input type="text" name="addName" value="${name}">
			</label><br>
			<input type="hidden" name="idGroup" value="${idGroup}">
				<input type="submit">
		</form>
	</c:if>
	<c:if test="${idGroup == null}">
		<form action="AdminGroups" method="post">
		<h2>Nowa grupa:</h2>
			<label>Nazwa grupy:
			<input type="text" name="addName">
			</label><br>
			<input type="submit">
		</form>
	</c:if>
</body>
</html>