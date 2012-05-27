<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>GreenMail Web</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet"></link>
<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
<link rel="stylesheet" href="static/css/bootstrap-responsive.min.css"></link>
<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="./">GreenMail WebApp</a>
				<div class="nav-collapse">
					<ul class="nav">
						<li class="active"><a href="./">Home</a></li>
						<li><a href="send">send</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<!-- /header -->
	<div id="container">
		<h1>Mail Detail</h1>
		<h2>Headers</h2>
		<c:forEach items="${headers}" var="h">
			<c:out value="${h.name}" />=<c:out value="${h.value}" />
			<br />
		</c:forEach>
		<h2>Contents</h2>
		<c:out value="${subject}" />
		<br />
		<c:out value="${from}" />
		<br />
		<c:out value="${plainBody}" />
		<br />
	</div>
</body>
</html>
