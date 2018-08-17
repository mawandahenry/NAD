<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%@ page import="java.sql.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>doc</title>
</head>
<body>


<c:set var="plastname" scope="request" value="G" />
<c:if test="${!empty param.username}">
< c:set var="username" value="${param.username}" />
</c:if>
<c:set var="password" scope="request" value="tx" />
<c:if test="${!empty param.password}">
< c:set var="password" value="${param.password}" />
</c:if>
<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/mawanda" 
user="root" password="sesnaco" />
<sql:query dataSource="${db}" var="quex">SELECT * FROM admin</sql:query>
<c:forEach items="${quex.rows}" var="pp">
<c:choose>
<c:when test="${param.username == pp.username && param.password == pp.password }">
<c:set scope="session" var="loginUser" value="${param.username }"></c:set>
<c:redirect url="admin.jsp"></c:redirect>
</c:when>
<c:otherwise>
<c:redirect url="index.jsp"></c:redirect>

</c:otherwise>

</c:choose>

</c:forEach>

</body>
</html>