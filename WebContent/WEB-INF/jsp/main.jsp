<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css"/>">
<title><c:out value="Welcome to organizer."/></title>
</head>
<body onload='document.noteForm.note.focus();'>

<h2><c:out value="Your note:"/></h2>
<form:form name='noteForm' method="POST" action="/Organizer/addNote">
   <table>
    <tr>
        <td><form:label path="note">Note</form:label></td>
        <td><form:textarea name='note' path="note" rows="10" cols="60" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>

<form name='logoutForm' action="<c:url value='logout' />" method='POST'>
<input name="logout" type="submit" value="logout" />
		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
</form>
</body>
</html>