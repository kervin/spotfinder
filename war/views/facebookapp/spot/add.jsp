<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add new spot</title>
<link rel="StyleSheet" href="/css/common.css" type="text/css">

</head>
<body>

    <h1>Add new spot</h1>
    <form method="post" " action="/spot/add/new/">
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" id="name"  size="100" />
                </td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><textarea name="description" id="description" rows="5" cols="77">
                </textarea>
            </tr>
            <tr>
                <td>Location:</td>
                <td><input type="text" name="location" id="location" size="100"/>
                </td>
            </tr>
            <tr>
                <td>Image:</td>
                <td><input type="text" name="image" id="image"  size="100" />
                </td>
            </tr>
        </table>

        <input type="submit" value="Save Spot"  width="100px" /><input type="button" value="Cancel" onClick="window.location='/spot/' " />
    </form>
</body>
</html>