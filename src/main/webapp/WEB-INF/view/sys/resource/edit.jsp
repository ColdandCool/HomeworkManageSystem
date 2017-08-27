<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
	<title>ResourceModify</title>
	<%@ include file="../../include/head.jsp"%>
	<style type="text/css">
		.tpl-content-wrapper{margin-left:0}
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
							<div class="widget-title am-fl">ResourceModify</div>
						</div>
						<div class="widget-body am-fr">
							<form class="am-form tpl-form-border-form" data-am-validator modelAttribute="resource" action="${ctx}/resource/update" method="post">
								<c:choose>
									<c:when test="${empty resource.id}">
										<input type="hidden" name="parentId" value="${resource.resource.id}" />
										<input type="hidden" name="parentIds" value="${resource.resource.parentIds}${resource.resource.id}/" />
									</c:when>
									<c:otherwise>
										<input type="hidden" name="id" value="${resource.id}" />
									</c:otherwise>
								</c:choose>

								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label"><span class="error">*</span>Superior：</label>
									<div class="am-u-sm-9">
										<input type="text" id="parentName" minlength="1" value="${resource.resource.name}" readonly/>
									</div>
								</div>
								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label"><span class="error">*</span>Name：</label>
									<div class="am-u-sm-9">
										<input type="text" name="name" minlength="1" value="${resource.name}" placeholder="name(required)" required />
									</div>
								</div>
								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label"><span class="error">*</span>sort：</label>
									<div class="am-u-sm-9">
										<input type="text" name="sort" value="${resource.sort}" minlength="1" placeholder="sort(required)" required />
									</div>
								</div>
								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label"><span class="error">*</span>type：</label>
									<div class="am-u-sm-9">
										<select name="type" data="${resource.type}">
											<c:forEach items="${types}" var="m">
												<option value="${m}">${m.info}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="am-form-group" style="display:none">
									<label class="am-u-sm-3 am-form-label">logo：</label>
									<div class="am-u-sm-9">
										<div class="am-input-group">
											<input type="text" id="icon" name="icon" class="am-form-field" placeholder="logo"
												   value="${resource.icon}" />
							      <span class="am-input-group-btn">
							      	<input type="file" name="file" id="file" style="display: none;" />
							        <button class="am-btn am-btn-default" id="btnPicture" type="button">updateLogo</button>
							      </span>
										</div>
									</div>
								</div>
								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label">URL：</label>
									<div class="am-u-sm-9">
										<input type="text" name="url" value="${resource.url}" />
									</div>
								</div>
								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label">Permission：</label>
									<div class="am-u-sm-9">
										<input type="text" name="permission" value="${resource.permission}" />
									</div>
								</div>
								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label">Available：</label>
									<div class="am-u-sm-9">
										<select name="available" data="${resource.available}">
											<option value="true">Yes</option>
											<option value="false">No</option>
										</select>
									</div>
								</div>
								<div class="am-form-group">
									<div class="am-u-sm-9 am-u-sm-push-3">
										<shiro:hasPermission name="sys:resource:edit"><button type="submit" class="am-btn am-btn-primary">Save</button></shiro:hasPermission>
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
<%@ include file="../../include/bottom.jsp"%>
<script type="text/javascript" src="${ctxStatic}/custom/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	$(function () {
		//消息提醒
		var msg = '${msg}';
		if(msg!=''){
			showMsg(msg);
			closeModel(true);//关闭窗口
		}
		initSelectValue(true);//初始化下拉框的值
	});
	$(document).ready(function() {
		//触发选择文件
		$("#btnPicture").click(function(){
			$("#file").click();
		});
		//选择文件后
		$("#file").change(function(){
			$.ajaxFileUpload({
				url: '${ctx}/upload/local',
				type: 'post',
				secureuri: false,
				fileElementId: 'file',
				dataType: 'text',
				success: function (data, status)
				{
					data = JSON.parse(delHtmlTag(data));
					if(data.ret==1){
						$("#icon").val(data.data);
					}else{
						alert(data.msg);
					}
				},
				error: function (data, status, e)
				{
					alert(e);
				}
			});
		});
	});
</script>
</body>
</html>