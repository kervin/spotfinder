<%@ page import="com.kervinramen.spotfinder.facebookapp.model.*" %>
<%@ page import="java.util.*" %>

<% FacebookUsers users = (FacebookUsers)request.getAttribute("users"); %> 

<html>
<head><title>Facebook Users</title></head>
<body>
    <%= ((String)request.getAttribute("someValue")) %>
    
   
   <c:forEach items="${users.users}" var="current">
        ${current.username}
       ${current.userId}
      </c:forEach>
      
      
   
      xxx
</body>
</html>