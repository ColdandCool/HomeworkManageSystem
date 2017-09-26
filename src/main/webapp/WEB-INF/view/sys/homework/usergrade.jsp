<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>UserGrade</title>
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
    <!-- Content area -->
    <div class="tpl-content-wrapper">
        <div class="row-content am-cf">
            <div class="row">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                    <div class="widget am-cf">
                        <div class="widget-body am-fr">
                            <div class="am-u-sm-12">
                                <table id="contentTable"
                                       class="am-table am-table-compact am-table-striped tpl-table-black">
                                    <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>UserName</th>
                                        <th>Grade</th>
                                        <th>FileName</th>
                                        <th>SubmitDate</th>
                                        <th>Operation</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${data}" var="item" varStatus="status">
                                        <tr>
                                            <td>${status.index+1}</td>
                                            <td>${item.username}</td>
                                            <td>${item.grade}</td>
                                            <td>${item.fileName}</td>
                                            <td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></td>
                                            <td>
                                                <a href="#" onclick="promptData('${ctx}/homework/setGrade/${item.id}')"
                                                   title="Grade"><span class="am-icon-check"></span></a>
                                                <a href="${ctx}/homework/download/${item.id}" title="download"><span
                                                        class="am-icon-download"></span></a>
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