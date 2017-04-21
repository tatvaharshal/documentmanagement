<!-- saved from url=(0022)http://internet.e-mail -->
<!DOCTYPE html>
<html>
<head>
    <title>User_ Application</title>
   <script src="${pageContext.request.contextPath}/resources/app/angular.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/angular-route.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-chart.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/angular-sanitize.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/ui-bootstrap-tpls-0.13.4.js"></script>	
	<link href="https://bootswatch.com/cerulean/bootstrap.min.css" rel="stylesheet" />
    <link href="https://bootswatch.com/assets/css/custom.min.css" rel="stylesheet" />
    <link href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.css" rel="stylesheet" />
    <script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/DemoAPP.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Services/UserServiceChirag.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Directive/FileUploadChirag.js"></script>     
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/FileUploadServiceChirag.js"></script>     
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/IFileUploadServiceChirag.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/BaseController.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/UserControllerChirag.js"></script>
	
	<script>localStorage._url = "${pageContext.request.contextPath}";</script>
</head>
<body ng-app="Demo">
    <div class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a href="${pageContext.request.contextPath}/userChirag/dashboardChirag" class="navbar-brand">User Dashboard</a>
                <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${pageContext.request.contextPath}/userChirag/userList">Manage Users</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container" ng-controller="userControllerChirag as ctrl" ng-init="ctrl.userGraphData()">
        <div class="row">
        	<div class="col-lg-6">
        	<br/>
			    <canvas id="pieChartChirag" class="chart chart-pie"
				  chart-data="pieChartDataChirag" chart-labels="pieChartLabelChirag" chart-options="pieChartOptionChirag" height="240">
				 </canvas>
			</div>
            <div class="col-lg-6">
            <br/>
            	<canvas id="barChartChirag" class="chart chart-bar"
				  chart-data="barChartDataChirag" chart-labels="barChartLabelChirag" chart-series="barChartSeriesChirag" chart-options="barChartOptionChirag" height="240">
				</canvas>
            </div>
        </div>
    </div>
</body>
</html> 