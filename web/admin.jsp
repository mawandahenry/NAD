<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Mobile Money</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
	<script src="jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	
</head>
<body>

<script type="text/javascript">
$(document).ready(function(){
$("#reg").click(function(){
$("#food").fadeToggle("slow",function(){
$("#food3").hide("slow",function(){
$("#food2").hide("slow",function(){
$("#food4").hide("slow",function(){
$("#food5").hide("slow",function(){
$("#food6").hide("slow",function(){
$("#food7").hide();
                        });
		        });
			});	
			});
			});
		});
	});
	
	
	$("#comm").click(function(){
	$("#food2").fadeToggle("slow", function(){
	$("#food").hide("slow",function(){
        $("#food3").hide("slow",function(){
	$("#food4").hide("slow",function(){
	$("#food5").hide("slow",function(){
	$("#food6").hide("slow",function(){
        
        $("#food7").hide();
        });
	});
	});	
	});
	});
			
	});
		
	});
	
	$("#comm1").click(function(){
	$("#food3").fadeToggle("slow", function(){
	$("#food").hide("slow",function(){
	$("#food2").hide("slow",function(){
	$("#food4").hide("slow",function(){
	$("#food5").hide("slow",function(){
	$("#food6").hide("slow");
				});
			});
		});	
		});
		
	});	
		
	});
	$("#comm2").click(function(){
	$("#food4").fadeToggle("slow", function(){
	$("#food").hide("slow",function(){
	$("#food2").hide("slow",function(){
	$("#food3").hide("slow",function(){
	$("#food5").hide("slow",function(){
	$("#food6").hide("slow",function(){
        $("#food7").hide();
        });
					});
				});
			});	
			});
			
		});	
			
		});
	
	$("#two").click(function(){
	$("#food5").fadeToggle("slow", function(){
	$("#food").hide("slow",function(){
	$("#food2").hide("slow",function(){
	$("#food3").hide("slow",function(){
	$("#food4").hide("slow",function(){
	$("#food6").hide("slow",function(){
        $("#food7").hide();
        });
					});
						
				});
			});	
			});
			
		});	
			
		});
	$("#comm3").click(function(){
	$("#food6").fadeToggle("slow", function(){
	$("#food").hide("slow",function(){
	$("#food2").hide("slow",function(){
	$("#food3").hide("slow",function(){
	$("#food4").hide("slow",function(){
	$("#food5").hide("slow",function(){
                                                    
         $("#food7").hide();
         });
         });
						
	});
	});	
	});
			
	});	
			
	});
        $("#comm4").click(function(){
        $("#food7").fadeToggle("slow", function(){
	$("#food").hide("slow",function(){
	$("#food2").hide("slow",function(){
	$("#food3").hide("slow",function(){
	$("#food4").hide("slow",function(){
	$("#food5").hide("slow",function(){
         $("#food6").hide();
          });
          });
						
	});
	});	
	});
			
	});	
			
	});
});
</script>
<c:if test="${empty sessionScope['loginUser'] }">
<c:redirect url="index.jsp"></c:redirect>
</c:if>
<div style="float:right">
<span style="color:green; background-color:dodgerblue;"></span><c:out value="${sessionScope['loginUser']}" /></span>|
<a href="logout.jsp">Logout </a>
</div>

<div id="tagx"> 
	<h1> MOBILE MONEY UGANDA</h1>
</div>
<div class="container">
<div class="nav_left">

<table class="table" style="margin-top: 120px;"><tr><td>Admin panel</td></tr><tr><td>
<a href="#" id= "reg"  style="font-size: 20px;"><i class="fa fa-users" style="font-size: 35px;">
</i>Register</a></td></tr>

<tr><td>
<a href="#" id="two" style="font-size: 20px;"><i class="fa fa-bars" style="font-size: 35px;"></i>Reports & records</a>
</td></tr>
<tr><td>
<a href="#" style="font-size: 20px;" id = "comm"><i class="fa fa-file" style="font-size: 35px;"></i>Commission by kiosk</a></td></tr>
<tr><td>
<a href="#" style="font-size: 20px;" id = "comm1"><i class="fa fa-list" style="font-size: 35px;"></i>Kiosk & MTN</a></td></tr>
<tr><td>
<a href="#" style="font-size: 20px;" id = "comm2"><i class="fa fa-list" style="font-size: 35px;"></i>Kiosk & Warid</a></td></tr>
<tr><td>
<a href="#" style="font-size: 20px;" id="comm3"><i class="fa fa-list" style="font-size: 35px;"></i>commission by all</a></td></tr>
<tr><td>
<a href="#" style="font-size: 20px;" id="comm4"><i class="fa fa-list" style="font-size: 35px;"></i>transfers</a></td></tr>
</table>
</div>
<div class="context">
	
	
<div id="food">
<h3 style="margin-left:-90px; " >Registration details</h3>

<form action="insert.jsp" method="POST">
	<table>
	
	<tr><td>Username</td><td><input type="text" name="username"></td></tr>
    <tr><td>MTNno.</td><td><input type="text" name="MTNLine"></td></tr>
    <tr><td>AirtelNo.</td><td><input type="text" name="AirtelLine"></td></tr>
    <tr><td>MTNFloat</td><td><input type="text" name="MtnFloat"></td></tr>
    <tr><td>AirtelFloat</td><td><input type="text" name="AirtelFloat"></td></tr>
    <tr><td>InitialCash</td><td><input type="text" name="initialCash"></td></tr>
    <tr><td>Password</td><td><input type="password" name="password"></td></tr>
    <tr><td>Commission</td><td><input type="text" name="commission"></td></tr>
    <tr><td>CommissionMTN</td><td><input type="text" name="commissionMTN"></td></tr>
    <tr><td>CommissionAirtel</td><td><input type="text" name="commissionAirtel"></td></tr>
    <tr><td></td><td><input type="submit" value="Register" class="btn btn-success btn-lg"></td></tr>
    
	</table>
	
</form>
</div>
<div id="food2">
<h3 style="margin-left:30px; " >Kiosks & Commissions</h3>
<table class="table"><tr><th>KioskNo.</th><th>Attendant</th><th>Commission</th></tr><tr>

<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/mawanda" 
user="root" password="sesnaco" />
<sql:query dataSource="${db}" var="quex">SELECT * FROM attedantsregister</sql:query>
<c:forEach items="${quex.rows}" var="pp">
<td><c:out value="${pp.KioskNo }"></c:out></td><td><c:out value="${pp.username }"></c:out></td><td><c:out value="${pp.commission}"></c:out></td></tr>

</c:forEach>

</table>


</div>
<div id="food3">
<h3 style="margin-left:30px; " >Kiosks & MTN</h3>
<table class="table"><tr><th>KioskNo.</th><th>Attendant</th><th>Commission_mtn</th></tr><tr>

<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/mawanda" 
user="root" password="sesnaco" />
<sql:query dataSource="${db}" var="quex">SELECT * FROM attedantsregister</sql:query>
<c:forEach items="${quex.rows}" var="pp">
<td><c:out value="${pp.KioskNo }"></c:out></td><td><c:out value="${pp.username }"></c:out></td><td><c:out value="${pp.commission_mtn}"></c:out></td></tr>

</c:forEach>

</table>


</div>
<div id="food4">
<h3 style="margin-left:30px; " >Kiosks & Airtel</h3>
<table class="table"><tr><th>KioskNo.</th><th>Attendant</th><th>Commission_airtel</th></tr><tr>

<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/mawanda" 
user="root" password="sesnaco" />
<sql:query dataSource="${db}" var="quex">SELECT * FROM attedantsregister</sql:query>
<c:forEach items="${quex.rows}" var="pp">
<td><c:out value="${pp.KioskNo }"></c:out></td><td><c:out value="${pp.username }"></c:out></td><td><c:out value="${pp.commission_airtel}"></c:out></td></tr>

</c:forEach>

</table>


</div>
<div id="food5">
<h3 style="margin-left:30px; " >All Made Transactions</h3>
<table class="table"><tr><th>KioskNo.</th><th>Amount</th><th>Line</th><th>username</th><th>transaction</th><th>date</th><th>CustomerNo</th></tr><tr>

<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/mawanda" 
user="root" password="sesnaco" />
<sql:query dataSource="${db}" var="quex">SELECT * FROM tranfers</sql:query>
<c:forEach items="${quex.rows}" var="pp">


<td><c:out value="${pp.KioskNo }"></c:out></td><td><c:out value="${pp.Amount }"></c:out></td><td><c:out value="${pp.line}"></c:out></td><td><c:out value="${pp.username }"></c:out></td>
<td><c:out value="${pp.transaction }"></c:out></td><td><c:out value="${pp.date }"></c:out></td><td><c:out value="${pp.customerNo }"></c:out></td></tr>

</c:forEach>

</table>


</div>
<div id="food6">
<h3 style="margin-left:20px; " >overall commission</h3>
<table class="table"><tr><th>Total-Amount</th>
<c:set var="total" value="${0}"/>
<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/mawanda" 
user="root" password="sesnaco" />
<sql:query dataSource="${db}" var="quex">SELECT * FROM attedantsregister</sql:query>
<c:forEach items="${quex.rows}" var="pp">
<c:set var="total" value="${total + pp.commission}" />

</c:forEach>
<td><c:out value="${total}"></c:out></td></tr>

</table>


</div>
<div id="food7">
<h3 style="margin-left:30px; " >transfered transactions</h3>
<table class="table"><tr><th>transaction</th><th>Amount</th><th>customerLine</th><th>provider</th><th>from</th><th>to</th><th>status</th></tr><tr>

<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/mawanda" 
user="root" password="sesnaco" />
<sql:query dataSource="${db}" var="quex">SELECT * FROM service</sql:query>
<c:forEach items="${quex.rows}" var="pp">


<td><c:out value="${pp.transaction }"></c:out></td><td><c:out value="${pp.amount }"></c:out></td><td><c:out value="${pp.customerNo}"></c:out></td><td><c:out value="${pp.provider }"></c:out></td>
<td><c:out value="${pp.from_agent }"></c:out></td><td><c:out value="${pp.to_agent }"></c:out></td><td><c:out value="${pp.status }"></c:out></td></tr>

</c:forEach>

</table>


</div>

</div>
</div>
<script type="text/javascript" src="bootstrap.min.js"></script>
</body>
</html>