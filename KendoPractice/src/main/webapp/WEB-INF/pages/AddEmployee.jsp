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
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/EmployeeService.js"></script>    
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/IEmployeeService.js"></script> 
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Directive/fileModel.js"></script>     
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/FileUploadService.js"></script>     
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/IFileUploadService.js"></script>     
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
    <div class="container" ng-controller="employeeController" ng-init="vm.EditEmployeeInit(${employeeid})">
        <div class="row">
            <div class="col-lg-10">
                <div class="well bs-component">
                    <form class="form-horizontal" name="addEmployeeForm">
                        <fieldset>
                            <legend ng-if="employeeMaster.employeeId == 0">Add Employee Details</legend>
							<legend ng-if="employeeMaster.employeeId > 0">Edit Employee Details</legend>

							<input type="hidden" ng-model="employeeMaster.employeeId">
                            <div class="form-group">
                                <label for="txtUsername" class="col-lg-2 control-label">Username :</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="txtUsername" placeholder="Username" ng-model="employeeMaster.userName" required="required">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="txtPassword" class="col-lg-2 control-label">Password :</label>
                                <div class="col-lg-10">
                                    <input type="password" class="form-control" id="txtPassword" placeholder="Password" ng-model="employeeMaster.password" required="required">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="txtEmail" class="col-lg-2 control-label">Email :</label>
                                <div class="col-lg-10">
                                    <input type="email" class="form-control" id="txtEmail" placeholder="Email Address" ng-model="employeeMaster.email" required="required">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="textAddress" class="col-lg-2 control-label">Address :</label>
                                <div class="col-lg-10">
                                    <textarea class="form-control" rows="3" id="textAddress" ng-model="employeeMaster.address"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="ddlCountry" class="col-lg-2 control-label">Country :</label>
                                <div class="col-lg-10">
                                    <select class="form-control" id="ddlCountry" ng-model="employeeMaster.country">
										<option value="">--Select--</option>
										<option value="India">India</option>
									</select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-2 control-label">Gender :</label>
                                <div class="col-lg-10">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="gender" id="genderMale" value="male" checked="" ng-model="employeeMaster.gender">
                                            Male
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="gender" id="genderFemale" value="female" ng-model="employeeMaster.gender">
                                            Female
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="technologies" class="col-lg-2 control-label">Technologies :</label>
                                <div class="col-lg-10">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="1" ng-checked="technologies.indexOf('Java') > -1" ng-click="vm.SelectTechnology('Java')"> Java
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="2" ng-checked="technologies.indexOf('.Net') > -1"  ng-click="vm.SelectTechnology('.Net')"> .Net
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="3" ng-checked="technologies.indexOf('PHP') > -1"  ng-click="vm.SelectTechnology('PHP')"> PHP
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="4" ng-checked="technologies.indexOf('Android') > -1"  ng-click="vm.SelectTechnology('Android')"> Android
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="5" ng-checked="technologies.indexOf('IPhone') > -1"  ng-click="vm.SelectTechnology('IPhone')"> IPhone
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="6" ng-checked="technologies.indexOf('Web Designing') > -1"  ng-click="vm.SelectTechnology('Web Designing')"> Web Designing
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="txtDob" class="col-lg-2 control-label">Date Of Birth :</label>
                                <div class="col-lg-10">
                                    <input type="text" id="txtDob" class="form-control" placeholder="MM/DD/YYYY" ng-model="mydateOfBirth"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="txtPhoneNumber" class="col-lg-2 control-label">Phone number :</label>
                                <div class="col-lg-10">
                                    <input type="text" id="txtPhoneNumber" class="form-control" placeholder="Phone number" ng-model="employeeMaster.phoneNumber"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="fileImage" class="col-lg-2 control-label">Image :</label>
                                <div class="col-lg-10">
                                    <input type="file" name="file" id="fileImage" class="form-control" file-model = "myFile">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-lg-10 col-lg-offset-2">
                                    <button type="button" class="btn btn-primary" ng-disabled="addEmployeeForm.$invalid" ng-click="vm.AddEmployee()">Submit</button>
                                    <a href="EmployeesList.html" class="btn btn-default">Cancel</a>
                                </div>
                            </div>

                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script>
        $('#txtDob').datepicker({
            format: 'mm/dd/yyyy',
            autoclose: true
        });
    </script>
</body>
</html>