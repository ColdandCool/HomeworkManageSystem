<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title><c:set var="iutilsName" value='${fnc:getConfig("david.name")}' />${iutilsName} - index</title>
	<%@ include file="include/head.jsp"%>
	<style>
		.tpl-content-wrapper{margin-left:0}
		.widget-body{padding: 13px 20px;}
	</style>
</head>
<body>
<script src="${ctxStatic}/assets/js/theme.js"></script>
<!-- Content area -->
<div class="tpl-content-wrapper">
	<div class="row-content am-cf">

	</div>
</div>
<%@ include file="include/bottom.jsp"%>
</body>
</html>