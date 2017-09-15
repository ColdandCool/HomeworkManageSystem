<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<%
	response.setStatus(HttpServletResponse.SC_OK);
	String path = request.getContextPath();
%>
<html>
<head>
	<title>500</title>
	<%@ include file="../include/head.jsp"%>
	<style>
		.tpl-content-wrapper{margin-left:0}
	</style>
</head>
<body>
<script src="${ctxStatic}/assets/js/theme.js"></script>
<div class="am-g tpl-g">
	<!-- Content area -->
	<div class="tpl-content-wrapper">
		<div class="row-content am-cf">
			<div class="widget am-cf">
				<div class="widget-body">
					<div class="tpl-page-state">
						<div class="tpl-page-state-title am-text-center tpl-error-title">500</div>
						<div class="tpl-error-title-info">Internal Server Error</div>
						<div class="tpl-page-state-content tpl-error-content">
							<p>Internal server error, please contact the administrator</p>
							<button type="button" class="am-btn am-btn-secondary am-radius tpl-error-btn" onclick="top.location='${ctx}'">Return Home</button></div>
						<div class="am-g" style="padding: 0px 30px;">
							<div>The system execution error occurred. The information is described below：</div>
							<div>The error status code is：${pageContext.errorData.statusCode}</div>
							<div>The error occurred on the page：${pageContext.errorData.requestURI}</div>
							<div>Error message：${pageContext.exception}</div>
							<div>
								Error stack information：<br />
								<c:forEach var="trace"
										   items="${pageContext.exception.stackTrace}">
									<div>${trace}</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="../include/bottom.jsp"%>
</body>
</html>