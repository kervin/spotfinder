<html>
<head>
<title>Add New Facebook User</title>
</head>
<body>
    <h2>Add New User</h2>
    <form method="post" action="/database/user/add/new/">
        <table>
            <tr>
                <td>UserId:</td>
                <td><input type="text" name="userid" id="userid" />
                </td>
            </tr>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username" id="username" />
                </td>
            </tr>
            <tr>
                <td>Info Graph:</td>
                <td><input type="text" name="infograph" id="infograph" />
                </td>
            </tr>
            <tr>
                <td>Feed Graph:</td>
                <td><input type="text" name="feedgraph" id="feedgraph" />
                </td>
            </tr>
            <tr>
                <td>Home Feed Graph:</td>
                <td><input type="text" name="homefeedgraph" id="homefeedgraph" />
                </td>
            </tr>
        </table>
        <input type="submit" value="Add User" />
    </form>
</body>
</html>