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
<title>Insert title here</title>
</head>
<body>
 <c:if test="${ empty param.username or empty param.password or empty param.MTNLine or empty param.AirtelLine or empty param.MtnFloat or empty param.AirtelFloat}">
            <c:redirect url="admin.jsp" >
                <c:param name="errMsg" value="Please fill in all the fields" />
            </c:redirect>
 
        </c:if>
        <sql:setDataSource var="dbsource" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost/mawanda"
                           user="root"  password="sesnaco"/>
 
 
        <sql:update dataSource="${dbsource}" var="result">
            INSERT INTO attedantsregister(KioskNo,username,MTNLine,AirtelLine,MtnFloat,AirtelFloat,initialCash,password,commission,commission_mtn,commission_airtel) VALUES (null,?,?,
            ?,?,?,?,?,?,?,?);
            <sql:param value="${param.username}" />
            <sql:param value="${param.MTNLine}" />
            <sql:param value="${param.AirtelLine}" />
            <sql:param value="${param.MtnFloat}" />
            <sql:param value="${param.AirtelFloat}" />
            <sql:param value="${param.initialCash}" />
            <sql:param value="${param.password}" />
            <sql:param value="${param.commission}" />
            <sql:param value="${param.commissionMTN}" />
            <sql:param value="${param.commissionAirtel}" />
           
        </sql:update>
        <c:if test="${result>=1}">
            
 
            <c:redirect url="admin.jsp" >
              <font size="5" color='green'> Congratulations ! Data inserted
            successfully.</font>  
            </c:redirect>
        </c:if> 
 

</body>
</html>