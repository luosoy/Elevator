<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="stc" value="${pageContext.request.contextPath}/assets" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=no" />
        <meta http-equiv="Cache-Control" content="no-store" />
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Expires" content="0" />
        <title><sitemesh:write property='title' /></title>
        <link href="${stc}/lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="${stc}/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
        <link href="${stc}/lib/Ionicons/css/ionicons.min.css" rel="stylesheet"/>
        <link href="${stc}/lib/AdminLTE/css/AdminLTE.min.css" rel="stylesheet"/>
        <link href="${stc}/lib/AdminLTE/css/skins/skin-blue.min.css" rel="stylesheet"/>
        <link href="${stc}/common/css/common.css" rel="stylesheet"/>
        <script type="text/javascript">
            var SYS = {
                ctx: '${ctx}',
                path: '${ctx}/web/',
                stc: '${stc}'
            };
        </script>
        <!--[if lt IE 8]><script src="${stc}/lib/json/json3.min.js" type="text/javascript"></script><![endif]-->
        <script src="${stc}/lib/jquery/dist/jquery.min.js"></script>
        <script src="${stc}/lib/bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="${stc}/lib/AdminLTE/js/adminlte.min.js"></script>
        <script src="${stc}/common/js/common.js"></script>
        <sitemesh:write property='head'/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
    <sitemesh:write property='body'/>
</body>
</html>