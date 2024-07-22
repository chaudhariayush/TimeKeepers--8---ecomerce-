<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>TimeKeepers</title>
   		<!-- This is a link to view favicon in browsers tab  -->
  		<link href="../resources/icons/favicon.png" rel="icon">		
		<link rel="stylesheet" type="text/css" href="#" />
	</head>
<body>
    <h1>User Profile</h1>
    <p>First Name: ${user.firstName}</p>
    <p>Last Name: ${user.lastName}</p>
    <p>Username: ${user.username}</p>
    <p>Date of Birth: ${user.dob}</p>
    <p>Gender: ${user.gender}</p>
    <p>Phone Number: ${user.phoneNumber}</p>
    <p>Email: ${user.email}</p>
    <p>Address: ${user.address}</p>
    <img src="<%=contextPath%>/resources/images/user/${user.userImage}" alt="User Image" width="200px" height="200px"> 
<%--    <%=request.getContextPath()%> --%>

	      <a href="pages/updateProfile.jsp">Update Profile</a>

</body>
</html>
