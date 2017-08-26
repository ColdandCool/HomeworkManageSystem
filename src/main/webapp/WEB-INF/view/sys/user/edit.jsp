<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
	<title>User Modify</title>
	<%@ include file="../../include/head.jsp"%>
    <link rel="stylesheet" href="${ctxStatic}/3rd-lib/jquery-ztree/css/zTreeStyle.css">
    <style>
		ul.ztree {
			margin-top: 10px;
			border: 1px solid #ddd;
			background: #fff;
			width: 198px;
			height: 200px;
			overflow-y: auto;
			overflow-x: auto;
		}
		.tpl-content-wrapper{margin-left:0}
		.am-selected{width: 100%;}
	</style>
</head>
<body>
<script src="${ctxStatic}/assets/js/theme.js"></script>
<div class="am-g tpl-g">
	<!-- 内容区域 -->
	<div class="tpl-content-wrapper">
		<div class="row-content am-cf">
			<div class="row">
				<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
					<div class="widget am-cf">
						<div class="widget-head am-cf">
							<div class="widget-title am-fl">User Info</div>
						</div>
						<div class="widget-body am-fr">
							<form class="am-form tpl-form-border-form" data-am-validator modelAttribute="user" action="${ctx}/user/<c:choose><c:when test="${empty user.id}">create</c:when><c:otherwise>update</c:otherwise></c:choose>" method="post">
								<input type="hidden" name="id" value="${user.id}" />
								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label"><span class="error">*</span>Account：</label>
									<div class="am-u-sm-9">
										<input type="text" name="username" minlength="3" placeholder="Account at least 3 character"
											   value="${user.username}" ${empty user.id?'':'readonly'} required />
									</div>
								</div>
								<c:if test="${empty user.id}">
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label"><span class="error">*</span>Password：</label>
										<div class="am-u-sm-9">
											<input type="password" name="password" minlength="6" placeholder="password at least 6 character"
												   value="${user.password}" required />
										</div>
									</div>
								</c:if>
								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label"><span class="error">*</span>IsLeader：</label>
									<div class="am-u-sm-5">
										<select name="isDept" data="${user.isDept}">
											<option value="false">Yes</option>
											<option value="true">No</option>
										</select>
									</div>
									<label class="am-u-sm-4"></label>
								</div>
								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label">Roles：</label>
									<div class="am-u-sm-9">
										<select name="roleIds" data="<c:forEach items="${user.roleIds}" var="item">${item},</c:forEach>" multiple>
											<c:forEach items="${roleList}" var="m">
												<option value="${m.id}">${m.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label">Name：</label>
									<div class="am-u-sm-9">
										<input type="text" name="name" placeholder="Name" value="${user.name}" />
									</div>
								</div>
								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label">Email：</label>
									<div class="am-u-sm-9">
										<input type="text" name="email" placeholder="Email" value="${user.email}" />
									</div>
								</div>
								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label">Mobile：</label>
									<div class="am-u-sm-9">
										<input type="text" name="mobile" placeholder="Tel" value="${user.mobile}" />
									</div>
								</div>
								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label">Phone：</label>
									<div class="am-u-sm-9">
										<input type="text" name="phone" placeholder="Phone" value="${user.phone}" />
									</div>
								</div>
								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label">IsLocked：</label>
									<div class="am-u-sm-5">
										<select name="locked" data="${user.locked}">
											<option value="false">No</option>
											<option value="true">Yes</option>
										</select>
									</div>
									<label class="am-u-sm-4"></label>
								</div>
								<div class="am-form-group">
									<div class="am-u-sm-9 am-u-sm-push-3">
										<button type="submit" class="am-btn am-btn-primary">Save</button>
										<button type="button" class="am-btn am-btn-danger" onclick="closeModel(false)">Close</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index: 10000;">
	<ul id="tree" class="ztree" style="margin-top:0;"></ul>
</div>
<%@ include file="../../include/bottom.jsp"%>
<script src="${ctxStatic}/3rd-lib/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${ctxStatic}/custom/js/ztree.select.js"></script>
<script>
	$(document).ready(function() {
		//消息提醒
		var msg = '${msg}';
		if(msg!=''){
			showMsg(msg);
			if(msg=="Success"){
				closeModel(true);//关闭窗口
			}
		}
		initSelectValue(true);//初始化下拉框的值
	});
</script>

</body>
</html>