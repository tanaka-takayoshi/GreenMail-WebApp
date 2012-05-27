<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
						<li><a href="send">Send</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<!-- /header -->
	<div id="container">
		<h1>Mail List</h1>
		<div id="content" class="no-side-nav">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>From</th>
						<th>Subject</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${mails}" var="mail">
						<tr>
							<td><c:out value="${mail.from}" /></td>
							<td><c:out value="${mail.subject}" /></td>
							<td><spring:url value="mail?id={mailId}" var="mailUrl">
									<spring:param name="mailId" value="${mail.id}" />
								</spring:url> <a href="${fn:escapeXml(mailUrl)}">Detail</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
