<!DOCTYPE html>
<html>
<head>
    <title>Demo Application</title>
    <link href="https://bootswatch.com/cerulean/bootstrap.min.css" rel="stylesheet" />
    <link href="https://bootswatch.com/assets/css/custom.min.css" rel="stylesheet" />
    <link href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.css" rel="stylesheet" />
    <script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>
            
    <script src="${pageContext.request.contextPath}/resources/app/angular.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/ui-bootstrap-tpls-0.13.4.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-sanitize.js"></script>  
    <script src="${pageContext.request.contextPath}/resources/app/Demo/DemoAPP.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Interface/IEmployeeScope.js"></script> 
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Interface/ICOCOScope.js"></script> 
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Directive/fileModel.js"></script>     
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/FileUploadService.js"></script>     
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/IFileUploadService.js"></script>  
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/EmployeeService.js"></script>    
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/IEmployeeService.js"></script>    
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/BaseController.js"></script>    
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/EmployeeController.js"></script>
	 <style>
    .ng-touched.ng-invalid.ng-invalid-required{
    border: 1px solid red;
    }
    </style>
</head>
<body ng-app="Demo">
   <div class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a href="UsersList.html" class="navbar-brand">Demo Application</a>
                <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="UsersList.html">Manage Users</a>
                    </li>
                    <li>
                        <a href="/employeelist">Manage Employees</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="container" ng-controller="employeeController">
        <div class="row">
            <div class="col-lg-12">
                <h2>Manage Employees</h2>
                <hr />
                <a class="btn btn-info" href="/addemployee">Add new</a>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-lg-12">
                <table class="table table-striped table-hover table-responsive">
                    <thead>
                        <tr>
                            <th>Username</th>
                            <th>Email </th>
                            <th>Address</th>
                            <th>Country</th>
                            <th>Gender</th>
                            <th>Technologies </th>
                            <th>Date Of Birth</th>
                            <th>Phone number</th>
                            <th>Image</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="employee in vm.employees"> 
                            <td>{{employee.userName}}</td>
                            <td>{{employee.email}}</td>
                            <td>{{employee.address}}</td>
                            <td>{{employee.country}}</td>
                            <td>{{employee.gender}}</td>
                            <td>{{employee.technologies}}</td>
                            <td ng-bind="employee.birthDate | date:'MM/dd/yyyy'"></td>
                            <td>{{employee.phoneNumber}}</td>
                            <td><img ng-if="employee.imageFilePath!=null" src="/resources/images/{{employee.imageFilePath}}" alt="" class="img-thumbnail" style="width: 50px;height: 50px;" /></td>
                            <td><a class="btn btn-primary" href="/editemployee?employeeId={{employee.employeeId}}">Edit</a> &nbsp; <a class="btn btn-danger" ng-click="vm.DeleteEmployee(employee.employeeId)">Delete</a></td>
                        </tr>                        
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>