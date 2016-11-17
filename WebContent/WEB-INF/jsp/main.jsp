<%@ page contentType="text/html; charset=UTF-8" %>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>

<html>
<head>
<title><c:out value="Welcome to organizer."/></title>
</head>
<body>

<h2><c:out value="Your note:"/></h2>
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