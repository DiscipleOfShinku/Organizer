<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css"/>">
<title><c:out value="Your note was stored."/></title>
</head>
<body>
   <h2><c:out value="Your note was stored."/></h2>
    <a href="/Organizer/showLastNote">View last note.</a>
    
<form name='logoutForm' action="<c:url value='logout' />" method='POST'>
<input name="logout" type="submit" value="logout" />
		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
</form>
</body>
</html>
