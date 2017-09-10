<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>UserManagement</title>
    <%@ include file="../../include/head.jsp" %>
    <link rel="stylesheet" href="${ctxStatic}/3rd-lib/jquery-ztree/css/zTreeStyle.css">
    <link href="${ctxStatic}/custom/css/amazeui.select.css" type="text/css" rel="stylesheet" charset="UTF-8"/>
    <style>
        .tpl-content-wrapper {
            margin-left: 0
        }

        .theme-black .widget .ztree li a {
            color: #ffffff
        }
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
                            <div class="widget-title am-fl">UserList</div>
                        </div>
                        <div class="widget-body am-fr">
                            <div class="am-u-sm-12 am-u-md-3 am-u-lg-3">
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <shiro:hasPermission name="sys:user:create">
                                            <button type="button" class="am-btn am-btn-default am-btn-success"
                                                    onclick="openModel(false,'${ctx}/user/create')">
                                                <span class="am-icon-plus"></span> ADD
                                            </button>
                                            <button type="button" class="am-btn am-btn-default am-btn-success"
                                                    onclick="openModel(false,'${ctx}/user/teamuserAddPage?id=${page.entity.id}')">
                                                <span class="am-icon-plus"></span> ADD
                                            </button>
                                        </shiro:hasPermission>
                                    </div>
                                </div>
                            </div>


                            <div class="am-u-sm-12">
                                <table id="contentTable"
                                       class="am-table am-table-compact am-table-striped tpl-table-black">
                                    <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>Account</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Tel</th>
                                        <th>IsLeader</th>
                                        <th>IsLocked</th>
                                        <th>Operation</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.list}" var="user" varStatus="status">
                                        <tr>
                                            <td>${status.index+1}</td>
                                            <td>${user.username}</td>
                                            <td>${user.name}</td>
                                            <td>${user.email}</td>
                                            <td>${user.mobile}</td>
                                            <td>${user.isDept?'<span class="am-badge am-badge-danger am-radius">Yes</span>':'<span class="am-badge am-badge-success am-radius">No</span>'}</td>
                                            <td>${user.locked?'<span class="am-badge am-badge-danger am-radius">Yes</span>':'<span class="am-badge am-badge-success am-radius">No</span>'}</td>
                                            <td>
                                                <shiro:hasPermission name="sys:user:update">
                                                    <a href="javascript:;" onclick="openModel(false,'${ctx}/user/update?id=${user.id}')"
                                                       title="Modify"><span class="am-icon-pencil"></span></a>
                                                </shiro:hasPermission>
                                                <shiro:hasPermission name="sys:user:delete">
                                                    <c:if test="${user.id!=1 && user.id!=fnc:getLoginUser().id}">
                                                        <a href="${ctx}/user/delete?id=${user.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}"
                                                           onclick="return confirm('Are you sure you want to delete this data?', this.href)"
                                                           title="Delete"><span class="am-text-danger am-icon-trash-o"></span></a>
                                                    </c:if>
                                                </shiro:hasPermission>
                                                <a href="javascript:;" onclick="openModel(false,'${ctx}/user/${user.id}/changePassword')"
                                                   title="ChangePwd"><span class="am-text-success am-icon-key"></span></a>
                                                <a href="${ctx}/user/teamUserRemove?id=${user.id}"
                                                   onclick="return confirm('Are you sure you want to remove this user from your team?', this.href)"
                                                   title="Delete"><span class="am-icon-times"></span></a>

                                                <a href="${ctx}/user/download?id=${user.id}&homeworkid=${1}"
                                                   onclick="return confirm('Are you sure you want to remove this user from your team?', this.href)"
                                                   title="Delete"><span class="am-icon-times"></span></a>
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
<%@ include file="../../include/bottom.jsp" %>
<script src="${ctxStatic}/3rd-lib/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/custom/js/amazeui.select.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var msg = '${msg}';
        if (msg != '') {
            showMsg(msg);
        }
    });
</script>
</body>
</html>