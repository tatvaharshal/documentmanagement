
<!DOCTYPE html>
<html>
<head>
    <title>Document List</title>
    <link href="${pageContext.request.contextPath}/resources/content/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/content/custom.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/content/GridUI.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/kendo-ui-core/2014.1.416/styles/kendo.default.min.css">
    <link rel="stylesheet" href="//cdn.kendostatic.com/2014.2.716/styles/kendo.common.min.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="http://kendo.cdn.telerik.com/2017.2.504/styles/kendo.default.mobile.min.css">
    <link href="${pageContext.request.contextPath}/resources/content/style.css" rel="stylesheet" />
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-sanitize.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/ui-bootstrap-tpls-0.13.4.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-chart.js"></script>

    <script src="${pageContext.request.contextPath}/resources/app/Demo/Document/ng-file-upload.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Document/ng-file-upload-shim.js"></script>

    <script src="${pageContext.request.contextPath}/resources/app/Demo/DocumentListApp.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Services/DocumentListService.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Services/FileUploadService.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/BaseDocumentListController.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/DocumentListController.js"></script>
    <script src="https://kendo.cdn.telerik.com/2017.1.223/js/kendo.all.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body ng-app="DocumentList">

<header id="header1">


</header>

<div  ng-controller="DocumentListController as ctrl" >


    <%--<div class ="success_message" id="success_message" style="color:green;float:left; margin:0 0 0 1000px; " ></div>--%>
    <h2 style="color:#2d4265">Documents
       <a href="./DocumentAdd" class="btn">+ Document Upload</a>
    </h2>
    <br>
        <div class="alert alert-success"  ng-show="success_message == true">{{statusMessage}}</div>
    <kendo-grid id="kGrid" options="mainGridOptions"  k-on-change="ctrl.onRawClick({ selected : selected })">
    </kendo-grid>

</div>
</body>
</html>
