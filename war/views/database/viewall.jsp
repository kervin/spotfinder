<%@ page import="com.kervinramen.spotfinder.facebookapp.model.*"%>
<%@ page import="java.util.*"%>

<html>
<head>
<title>Facebook Users</title>
</head>
<body>
	Find below the list of users:

	<%
	FacebookUsers fbUsers = (FacebookUsers) request.getAttribute("users");
	ArrayList<FacebookUser> users = fbUsers.getUsers();

	for (FacebookUser user : users) {
%>
	<table border="1">
		<tr>
			<td>Username:</td>
			<td><%=user.getAccessToken()%></td>
		</tr>
		<tr>
			<td>AccessToken:</td>
			<td><%=user.getUsername()%></td>
		</tr>
		<tr>
			<td>CreatedOn:</td>
			<td><%=user.getCreatedOn()%></td>
		</tr>
		<tr>
			<td>Info Graph:</td>
			<td><%=user.getInfoGraphString()%></td>
		</tr>
		<tr>
			<td>Feed Graph:</td>
			<td><%=user.getFeedGraphString()%></td>
		</tr>
		<tr>
			<td>Home Feed Graph:</td>
			<td><%=user.getHomeFeedGraphString()%></td>
		</tr>

	</table>
	<%
		}
	%>

</body>
</html>