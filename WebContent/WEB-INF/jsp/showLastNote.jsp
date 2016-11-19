<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css"/>">
<title>Last note</title>
</head>
<body>
<h2>Last note</h2>
<table>
<tr>
<td><b>Note number</b></td>
<td><b>Note</b></td>
<td><b>Date</b></td>
</tr>
<tr>
<td><c:out value="${requestScope.lastNote.id}" /></td>
<td><c:out value="${requestScope.lastNote.note}" /></td>
<td><c:out value="${requestScope.lastNote.noteDate}" /></td>
</tr>
</table>
<a href="/Organizer/main">Create another note.</a>
</body>
</html>