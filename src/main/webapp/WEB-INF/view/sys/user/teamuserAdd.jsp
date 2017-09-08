<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>TeamUserAdd</title>
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
                                        <th>Operation</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${userLsit}" var="user" varStatus="status">
                                        <tr>
                                            <td>${status.index+1}</td>
                                            <td>${user.username}</td>
                                            <td>${user.name}</td>
                                            <td>${user.email}</td>
                                            <td>${user.mobile}</td>
                                            <td>
                                                <shiro:hasPermission name="sys:user:delete">
                                                    <a href="${ctx}/user/teamuserAdd?id=${user.id}"
                                                       onclick="return confirm('Are you sure you want to add this user to your team?', this.href)"
                                                       title="ADD"><span class="am-icon-plus"></span></a>
                                                </shiro:hasPermission>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
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