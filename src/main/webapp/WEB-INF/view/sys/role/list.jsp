<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
	<title>RoleSetting</title>
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
							<div class="widget-title am-fl">RoleSetting</div>
						</div>
						<div class="widget-body am-fr">
							<div class="am-u-sm-12 am-u-md-3 am-u-lg-3">
								<div class="am-btn-toolbar">
									<div class="am-btn-group am-btn-group-xs">
										<shiro:hasPermission name="sys:role:edit">
										<button type="button" class="am-btn am-btn-default am-btn-success"
												onclick="openModel(false,'${ctx}/role/update')"><span class="am-icon-plus"></span> ADD
										</button></shiro:hasPermission>
									</div>
								</div>
							</div>

							<div class="am-u-sm-12 am-u-md-9 am-u-lg-9">
								<form id="searchForm" action="${ctx}/role" method="post" style="display: none;">
									<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
									<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
									<div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">

									</div>
								</form>
							</div>

							<div class="am-u-sm-12">
								<table id="contentTable"
									   class="am-table am-table-compact am-table-striped tpl-table-black">
									<thead>
									<tr>
										<th>No</th>
										<th>Name</th>
										<th>Label</th>
										<th>Available</th>
										<th>Remark</th>
										<th>Operation</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${page.list}" var="role" varStatus="status">
										<tr>
											<td>${status.index+1}</td>
											<td>${role.name}</td>
											<td>${role.role}</td>
											<td>${role.available?'<span class="am-badge am-badge-success am-radius">Yes</span>':'<span class="am-badge am-badge-danger am-radius">No</span>'}</td>
											<td>${role.remarks}</td>
											<td>
												<shiro:hasPermission name="sys:role:view"><a href="javascript:;" onclick="openModel(false,'${ctx}/role/update?id=${role.id}')" title="Modify"><span class="am-icon-pencil"></span></a></shiro:hasPermission>
												<shiro:hasPermission name="sys:role:edit"><c:if test="${role.id>1}">
													<a href="${ctx}/role/delete?id=${role.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}" onclick="return confirm('Are you sure you want to delete the data?', this.href)" title="Delete"><span class="am-text-danger am-icon-trash-o"></span></a>
												</c:if></shiro:hasPermission>
											</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="am-u-lg-12 am-cf">
								<%@ include file="../../utils/pagination.jsp" %>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="../../include/bottom.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		var msg = '${msg}';
		if(msg!=''){
			showMsg(msg);
		}
	});
</script>
</body>
</html>