<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Welcome to organizer</title>
</head>
<body>

<h2>Your note:</h2>
<form:form method="POST" action="/Organizer/addNote">
   <table>
    <tr>
        <td><form:label path="note">Note</form:label></td>
        <td><form:textarea path="note" heigth="200" width="500" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>