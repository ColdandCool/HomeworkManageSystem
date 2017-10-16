<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><c:set var="iutilsName" value='${fnc:getConfig("david.name")}'/>${iutilsName}</title>
    <%@ include file="include/head.jsp" %>
    <link rel="stylesheet" href="${ctxStatic}/custom/css/amazeui.select.css">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">You are using <strong>outdated </strong>browsers, the platform is not supported. Please upgrade
    your browser for a better experience.You are using outdated browsers, the platform is not supported. Please <a
            href="http://browsehappy.com/" target="_blank">upgrade your browser </a>for a better experience！</p>
<![endif]-->
<script src="${ctxStatic}/assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- head  -->
    <header>
        <!-- logo -->
        <div class="am-fl tpl-header-logo">
            <a href="${ctx}"><b>HomeworkManageSystem</b></a>
        </div>
        <div class="tpl-header-fluid">
            <div class="am-fl tpl-header-switch-button am-icon-list">
                    <span>
                </span>
            </div>
            <div class="am-fr tpl-header-navbar">
                <ul>
                    <!-- weather -->
                    <li class="am-text-sm">
                       <a>Weather in Sydney, AU,<span>${empty weather?weather.temp:weather.temp}</span>℃</a>
                    </li>
                    <!-- welcome -->
                    <li class="am-text-sm tpl-header-navbar-welcome">
                        <a href="#${ctx}/user/userInfo" onclick="link('${ctx}/user/userInfo')">Hello,
                            <span>${empty loginUser.name?loginUser.username:loginUser.name}</span> </a>
                    </li>
                    <!-- logout -->
                    <li class="am-text-sm">
                        <a href="${ctx}/logout" onclick="return confirm('Are you sure you want to exit?', this.href)">
                            <span class="am-icon-sign-out"></span> Layou Out
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </header>

    <!-- sidebar -->
    <div class="left-sidebar">
        <!-- user info  -->
        <div class="tpl-sidebar-user-panel">
            <div class="tpl-user-panel-slide-toggleable">
                <div class="tpl-user-panel-profile-picture">
                    <img src="${pageContext.request.contextPath}/${loginUser.photo}<c:if test="${empty loginUser.photo}">static/assets/img/user06.png</c:if>"
                         alt="profile picture">
                </div>
                <span class="user-panel-logged-in-text">
              <i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>
              ${empty loginUser.name?loginUser.username:loginUser.name}
          </span>
                <a href="#${ctx}/user/${empty loginUser.id?"":loginUser.id}/changePassword" onclick="openModel(false,'${ctx}/user/${empty loginUser.id?"":loginUser.id}/changePassword')"
                   class="tpl-user-panel-action-link"> <span class="am-icon-pencil"></span> User Setting</a>
            </div>
        </div>

        <!-- menu -->
        <ul class="sidebar-nav">
            <li class="sidebar-nav-heading">FunctionMenu</li>
            <c:forEach items="${menus}" var="menu1">
                <c:if test="${menu1.parentId==1}">
                    <li class="sidebar-nav-link">
                        <c:set var="isSub" value="0"></c:set>
                        <c:forEach items="${menus}" var="menu2">
                            <c:if test="${menu1.id==menu2.parentId}"><c:set var="isSub" value="1"></c:set></c:if>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${isSub==1}">
                                <a href="javascript:;" class="sidebar-nav-sub-title menu-link-clear">
                                    <img src="${ctxStatic}${m.icon}<c:if test="${empty m.icon}">/assets/i/menu.png</c:if>"
                                         style="width:16px;height:16px;margin-right:8px;"/> ${menu1.name}
                                    <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
                                </a>
                                <ul class="sidebar-nav sidebar-nav-sub">
                                    <c:forEach items="${menus}" var="menu2">
                                        <c:if test="${menu1.id==menu2.parentId}">
                                            <li class="sidebar-nav-link">
                                                <a href="#${ctx}/${menu2.url}" class="menu-link menu-link-clear"
                                                   level="2">
                                                    <span class="am-icon-angle-right sidebar-nav-link-logo"></span> ${menu2.name}
                                                </a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </c:when>
                            <c:otherwise>
                                <a href="#${ctx}/${menu1.url}" class="menu-link menu-link-clear" level="1">
                                    <img src="${ctxStatic}${m.icon}<c:if test="${empty m.icon}">/assets/i/menu.png</c:if>"
                                         style="width:16px;height:16px;margin-right:8px;"/> ${menu1.name}
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:if>
            </c:forEach>

        </ul>
    </div>

    <!-- Content area -->
    <div class="tpl-content-wrapper">
        <iframe name="main-content" id="main-content" src="${ctx}/welcome" style="width:100%;" scrolling="no"
                frameborder="no"></iframe>
    </div>
</div>
</div>
<%@ include file="include/bottom.jsp" %>
<script src="${ctxStatic}/3rd-lib/tpl/tpl.js"></script>
<!-- Define the template -->
<script type="text/template" id="mailTpl" desc="邮件列表">
    <# for(var i=0; i
    <list.length; i++) {var item=list[i];#>
    <li class="tpl-dropdown-menu-messages">
        <a href="javascript:;"
           onclick="document.getElementById('main-content').contentWindow.openModel(false,'${ctx}/sys/msgReceive/update?id=<#=item.id #>');$('#mail').dropdown('close');"
           c class="tpl-dropdown-menu-messages-item am-cf">
            <div class="menu-messages-ico">
                <img src="${pageContext.request.contextPath}/<#=item.createUser.photo #><# if(item.createUser.photo==null || item.createUser.photo==''){#>static/assets/img/user06.png<#}#>">
            </div>
            <div class="menu-messages-content">
                <div class="menu-messages-content-title">
                    <i class="am-icon-circle-o am-text-success"></i>
                    <span><#=item.createUser.name #></span>
                </div>
                <div class="am-text-truncate">
                    <#=item.msgSend.title #>
                </div>
                <div class="menu-messages-content-time">
                    <#=new Date(item.updateDate).format('yyyy-MM-dd h:m:s') #>
                </div>
            </div>
        </a>
    </li>
    <#}#>
        <li class="tpl-dropdown-menu-messages">
            <a href="#${ctx}/sys/msgReceive?msgSend.type=mail&status=-1"
               onclick="link('${ctx}/sys/msgReceive?msgSend.type=mail&status=-1');$('#mail').dropdown('close');"
               class="tpl-dropdown-menu-messages-item am-cf">
                <i class="am-icon-circle-o"></i> into list…
            </a>
        </li>
</script>
<script type="text/template" id="noticeTpl" desc="通知列表">
    <# for(var i=0; i
    <list.length; i++) {var item=list[i];#>
    <li class="tpl-dropdown-menu-notifications">
        <a href="javascript:;"
           onclick="document.getElementById('main-content').contentWindow.openModel(false,'${ctx}/sys/msgReceive/update?id=<#=item.id #>');$('#notice').dropdown('close');"
           class="tpl-dropdown-menu-notifications-item am-cf">
            <div class="tpl-dropdown-menu-notifications-title">
                <i class="am-icon-circle-o am-text-warning"></i>
                <span><#=item.msgSend.title #></span>
            </div>
            <div class="tpl-dropdown-menu-notifications-time">
                <#=new Date(item.updateDate).format('h:m') #>
            </div>
        </a>
    </li>
    <#}#>
        <li class="tpl-dropdown-menu-notifications">
            <a href="#${ctx}/sys/msgReceive?msgSend.type=notice&status=-1"
               onclick="link('${ctx}/sys/msgReceive?msgSend.type=notice&status=-1');$('#notice').dropdown('close');"
               class="tpl-dropdown-menu-notifications-item am-cf">
                <i class="am-icon-bell"></i> into list…
            </a>
        </li>
</script>
<script>
    $(document).ready(function () {
        //Initialize the address
        var initPage = function () {
            var link = location.hash;
            if (link) {
                var $menu = $("a[href='" + link + "']");
                var level = $menu.attr("level");
                if (level == '1') {
                    $menu.addClass("active");
                } else if (level == '2') {
                    $menu.addClass("sub-active");
                    $menu.parent().parent().prev().addClass("active");
                }
                $("#main-content").attr("src", link.substr(1));
            }
        }
        initPage();
        //Click on the left menu
        $(".sidebar-nav").on('click', '.menu-link', function () {
            //Clear the menu selection status
            $(".menu-link-clear").removeClass("active").removeClass("sub-active");
            var $menu = $(this);
            var level = $menu.attr("level");
            if (level == '1') {
                $menu.addClass("active");
            } else if (level == '2') {
                $menu.addClass("sub-active");
                $menu.parent().parent().prev().addClass("active");
            }
            $("#main-content").attr("src", $menu.attr("href").substr(1));
        });

        //All function menus
        var menus = [
            <c:forEach items="${menus}" var="menu" varStatus="status">
                <c:if test="${not empty menu.url}">{
                name: "${menu.name}",
                url: "${ctx}/${menu.url}"
            }<c:if test="${!status.last}">, </c:if></c:if>
            </c:forEach>
        ];

        //Function search event binding
        $(document).delegate(".am-select-ui-input", "blur focus keyup", function (event) {
            var eType = event.type;
            switch (eType) {
                case 'focusin':
                    var currentProposals = [];
                    var proposalList = $(this).parent().find(".am-select-ui");
                    var word = $(this).val();
                    proposalList.empty();
                    var items = menus;
                    for (var test in items) {
                        if (items[test].name.match(word)) {
                            currentProposals.push(items[test].name);
                            var element = $('<li></li>')
                                .html('<a href="#' + items[test].url + '" onclick="link(\'' + items[test].url + '\')">' + items[test].name + '</a>');
                            proposalList.append(element);
                        }
                    }
                    $(this).parent().find(".am-select-ui").show();
                    break;
                case 'focusout'://失去焦点
                    var $this = $(this);
                    setTimeout(function () {
                        $this.parent().find(".am-select-ui").hide(100);
                    }, 100);
                    break;
                case 'keyup':
                    var currentProposals = [];
                    var v = event.which;
                    if (v == 38 || v == 40 || v == 13) {
                        return;
                    }
                    var word = $(this).val();
                    var items = menus;
                    var proposalList = $(this).parent().find(".am-select-ui");
                    proposalList.empty();
                    for (var test in items) {
                        if (items[test].name.match(word)) {
                            currentProposals.push(items[test].name);
                            var element = $('<li></li>')
                                .html('<a href="#' + items[test].url + '" onclick="link(\'' + items[test].url + '\')">' + items[test].name + '</a>');
                            proposalList.append(element);
                        }
                    }
                    break;
            }
        });
    });

    //
    function link(url) {
        $(".menu-link-clear").removeClass("active").removeClass("sub-active");
        $("#main-content").attr("src", url);
    }
</script>
</body>
</html>