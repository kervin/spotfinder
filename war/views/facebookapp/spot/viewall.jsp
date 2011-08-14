<%@ page import="com.kervinramen.spotfinder.base.model.*"%>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View all spots</title>
</head>
<body>

    <table border="1">
        <tr>
            <td>Spot Name</td>
            <td>Description</td>
            <td>Location</td>
            <td>Image</td>
            <td>Action</td>
        </tr>

        <%
            ArrayList<Spot> spots = (ArrayList<Spot>) request.getAttribute("spots");

            for (Spot spot : spots) {
        %>

        <tr>
            <td><%=spot.getName()%></td>
            <td><%=spot.getDescription()%></td>
            <td><%=spot.getLocation()%></td>
            <td><%=spot.getImage()%></td>
            <td><input type="button" value="Edit" onClick="window.location='/spot/?spotid=<%=spot.getSpotId()%>' " /></td>
        </tr>


        <%
            }
        %>
    </table>
    <input type="button" value="Add new spot" onClick="window.location='/spot/add/' " />
    
</body>
</html>