<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css"/>">
<title><c:out value="Your note was stored."/></title>
</head>
<body>
   <h2><c:out value="Your note was stored."/></h2>
    <a href="/Organizer/showLastNote">View last note.</a>
</body>
</html>
