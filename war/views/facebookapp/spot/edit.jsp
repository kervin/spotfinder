<%@ page import="com.kervinramen.spotfinder.base.model.*"%>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit spot</title>
<link rel="StyleSheet" href="/css/common.css" type="text/css">

</head>
<body>
    <%
        Spot spot = (Spot) request.getAttribute("spot");

    %>

    <h1>Edit spot</h1>
    <form method="post" " action="/spot/update/">
        <input type="hidden" name="spotid" value="<%=spot.getSpotId()%>" />
        
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" id="name" value="<%= spot.getName() %>"/>
                </td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><input type="text" name="description" id="description" value="<%= spot.getDescription() %>"/>
                </td>
            </tr>
            <tr>
                <td>Location:</td>
                <td><input type="text" name="location" id="location" value="<%= spot.getLocation() %>"/>
                </td>
            </tr>
            <tr>
                <td>Image:</td>
                <td><input type="text" name="image" id="image" value="<%= spot.getImage() %>"/>
                </td>
            </tr>
        </table>
        <input type="submit" value="Save spot" />

    </form>
</body>
</html>