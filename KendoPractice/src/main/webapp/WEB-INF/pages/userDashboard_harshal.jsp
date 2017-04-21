<!-- saved from url=(0022)http://internet.e-mail -->
<!DOCTYPE html>
<html>
<head>
    <title>_HarShaL_ Application</title>
    <link href="https://bootswatch.com/cerulean/bootstrap.min.css" rel="stylesheet" />
    <link href="https://bootswatch.com/assets/css/custom.min.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
    
    <script src="${pageContext.request.contextPath}/resources/app/angular.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/ui-bootstrap-tpls-0.13.4.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-sanitize.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-chart.js"></script>
	<script src="${pageContext.request.contextPath}/resources/scripts/angular-route.js"></script>
	<script src="https://angular-file-upload.appspot.com/js/ng-file-upload-shim.js"></script>
	<script src="https://angular-file-upload.appspot.com/js/ng-file-upload.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/app/Demo/HarshalAPP.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Services/FileUploadService_harshal.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Services/UserService_harshal.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Directive/FileUploadDirective_harshal.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/BaseController_harshal.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/UserController_harshal.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/ModalInstanceController_harshal.js"></script>
	
	<script>localStorage._url = "${pageContext.request.contextPath}";</script>
</head>
<body ng-app="HarshalDemo">
    <div class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a href="${pageContext.request.contextPath}/user_harshal/userDashboard_harshal" class="navbar-brand">_HarShaL_ Dashboard</a>
                <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${pageContext.request.contextPath}/user_harshal/usersList_harshal">Manage Users</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container" ng-controller="userController_harshal as ctrl" ng-init="ctrl.userGraphData()">
        <div class="row">
        	<div class="col-lg-6">
        	<br/>
			    <canvas id="pieChart_harshal" class="chart chart-pie"
				  chart-data="pieChartData_harshal" chart-labels="pieChartLabel_harshal" chart-options="pieChartOption_harshal" height="240">
				 </canvas>
			</div>
            <div class="col-lg-6">
            <br/>
            	<canvas id="barChart_harshal" class="chart chart-bar"
				  chart-data="barChartData_harshal" chart-labels="barChartLabel_harshal" chart-series="barChartSeries_harshal" chart-options="barChartOption_harshal" height="240">
				</canvas>
            </div>
        </div>
    </div>
</body>
</html> 