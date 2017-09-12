<%--
  Created by IntelliJ IDEA.
  User: Victor
  Date: 12.09.2017
  Time: 6:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crowflight</title>
</head>
<body>
<form action=Crowflight.do method="post">
    <TR><TD>
        <B>From city:</B>
    </TD>
        <TD>
            <P><INPUT TYPE=TEXT NAME="fromCity" VALUE="" SIZE=15>
        </TD></TR>
    <TR><TD>
        <B>To city:</B>
    </TD>
        <TD>
            <P><INPUT TYPE=TEXT NAME="toCity" VALUE="" SIZE=15>
        </TD></TR>
    <tr>
        <td>
            <input type="submit" value="Calculation">
        </td>
    </tr>
</form>
</body>
</html>
