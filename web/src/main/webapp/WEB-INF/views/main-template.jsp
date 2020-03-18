<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request" />
<c:set var="pagesBook" value="${contextPath}/book" scope="request" />
<c:set var="pagesUser" value="${contextPath}/users" scope="request" />
<c:set var="pagesEngine" value="${contextPath}/engine" scope="request" />
<c:set var="pagesCar" value="${contextPath}/car" scope="request" />


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><tiles:insertAttribute name="title" /></title>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link rel="stylesheet" href="${contextPath}/resources/css/custom.css">

</head>
<body>
	<tiles:insertAttribute name="header" />
	<main>
		<div class="container">
			<tiles:insertAttribute name="body" />
		</div>
	</main>
	<tiles:insertAttribute name="footer" />
</body>
</html>
