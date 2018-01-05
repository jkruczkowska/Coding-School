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
	<!-- <form action="/home" method="get"></form>
	  -->
	  <%@ include file="/WEB-INF/header.jsp" %>
	  <h2>Ostatnio dodane rozwiązania:</h2>
	<table border="1">
		<thead>
			<tr>
				<td><b>Data dodania</b></td>
				<td><b>Updated</b></td>
				<td><b>Opis</b></td>
				<td><b>Exercise_id</b></td>
				<td><b>User_id</b></td>
				<td><b>Szczegóły</b></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${solutions}" var="solution">
				<tr>
					<td>${solution.created}</td>
					<td>${solution.updated}</td>
					<td>${solution.description}</td>
					<td>${solution.exercise_id}</td>
					<td>${solution.users_id}</td>
					<td><a href="showSolution?name=${solution.id}">Szczegóły</a></td>
					<!-- <td><a href="<c:url value='/DelSmurf?id=${smurf.id}'/>">Usun</a>
					<td><a href="<c:url value='/UpdateSmurf?id=${smurf.id}'/>">Edycja</a> </td>-->
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
	
	
	
	
	
<%@ include file="/WEB-INF/footer.jsp" %>	
	
</body>
</html>