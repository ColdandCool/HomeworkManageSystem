<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
	<title>404</title>
	<%@ include file="../include/head.jsp"%>
	<style>
		.tpl-content-wrapper{margin-left:0}
	</style>
</head>
<body>
<script src="${ctxStatic}/assets/js/theme.js"></script>
 <div class="am-g tpl-g">
	<!-- 内容区域 -->
	<div class="tpl-content-wrapper">
		<div class="row-content am-cf">
			<div class="widget am-cf">
				<div class="widget-body">
					<div class="tpl-page-state">
						<div class="tpl-page-state-title am-text-center tpl-error-title">404</div>
						<div class="tpl-error-title-info">Page Not Found</div>
						<div class="tpl-page-state-content tpl-error-content">
							<p>Sorry, did not find the page you need, may be the URL is uncertain, or the page has been removed.</p>
							<button type="button" class="am-btn am-btn-secondary am-radius tpl-error-btn" onclick="top.location='${ctx}'">Return Home</button></div>
					</div>
				</div>
			</div>
		</div>
	</div>
 </div>
<%@ include file="../include/bottom.jsp"%>
</body>
</html>