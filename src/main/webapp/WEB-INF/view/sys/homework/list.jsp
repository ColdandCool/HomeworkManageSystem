<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<html>
<head>
    <title>HomeworkCenter</title>
    <%@ include file="../../include/head.jsp" %>
    <link href="${ctxStatic}/custom/css/amazeui.select.css" type="text/css" rel="stylesheet" charset="UTF-8"/>
    <style>
        .tpl-content-wrapper {
            margin-left: 0
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
                            <div class="widget-title am-fl">HomeworkList</div>
                        </div>
                        <div class="widget-body am-fr">
                            <div class="am-u-sm-12 am-u-md-3 am-u-lg-3">
                                <div class="am-btn-toolbar">
                                    <div class="am-btn-group am-btn-group-xs">
                                        <button type="button" class="am-btn am-btn-default am-btn-success"
                                                onclick="openModel(false,'${ctx}/homework/create')"><span
                                                class="am-icon-plus"></span> ADD
                                        </button>
                                    </div>
                                </div>
                            </div>


                            <div class="am-u-sm-12">
                                <table id="contentTable"
                                       class="am-table am-table-compact am-table-striped tpl-table-black">
                                    <thead>
                                    <tr>
                                        <th>NO</th>
                                        <th>Title</th>
                                        <th>Deadline</th>
                                        <th>Remark</th>
                                        <th>HasUpload</th>
                                        <th>Operation</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.list}" var="item" varStatus="status">
                                        <tr>
                                            <td>${status.index+1}</td>
                                            <td>${item.title}</td>
                                            <td><fmt:formatDate value="${item.deadline}" pattern="yyyy-MM-dd"/></td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${fn:length(item.remarks) > 10}">
                                                        <c:out value="${fn:substring(item.remarks, 0, 10)}......"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${item.remarks}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${item.hasUpload}</td>
                                            <td>
                                                <a href="javascript:;"
                                                   onclick="openModel(false,'${ctx}/homework/update?id=${item.id}')"
                                                   title="Modify"><span class="am-icon-pencil"></span></a>
                                                <a href="${ctx}/homework/${item.id}/delete?pageNo=${page.pageNo}&pageSize=${page.pageSize}"
                                                   onclick="return confirm('Are you sure you want to delete the data?', this.href)"
                                                   title="Delete"><span
                                                        class="am-text-danger am-icon-trash-o"></span></a>
                                                <a href="javascript:;"
                                                   onclick="openModel(false,'${ctx}/homework/detail?id=${item.id}')"
                                                   title="Detail"><span class="am-icon-retweet"></span></a>
                                                <a href="${ctx}/homework/${item.id}/submitgrade" title="Grade"><span
                                                        class="am-icon-check"></span></a>
                                            </td>
                                            <td class="am-form-group am-form-file">
                                                <i class="am-icon-cloud-upload"></i> 选择要上传的作业
                                                <input type="file" id="uploadfile" accept=".doc,.docx" onchange="saveFile(${item.id})">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
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
<script type="text/javascript" src="${ctxStatic}/custom/js/amazeui.select.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var msg = '${msg}';
        if (msg != '') {
            showMsg(msg);
        }
    });

    //保存图像到后台
    function saveFile(homeworkid) {
        var formData = new FormData();
        var name = $('#uploadfile').val();
        formData.append('file', $('#uploadfile')[0].files[0]);
        formData.append("name",name);
        $.ajax({
            url: '${ctx}/homework/submithomework/'+homeworkid,
            type: 'POST',
            cache: false,
            data: formData,
            processData: false,
            contentType: false
        }).done(function (res) {
            showMsg(res)
        }).fail(function (res) {
        });
    }
</script>
</body>
</html>
