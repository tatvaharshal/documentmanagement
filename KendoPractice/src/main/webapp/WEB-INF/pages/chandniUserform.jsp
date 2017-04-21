<!DOCTYPE html>
<html>
<head>
    <title>Demo Application</title>
    <link href="${pageContext.request.contextPath}/resources/content/bootstrap.min.css" rel="stylesheet" />
    <link href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/content/custom.min.css" rel="stylesheet" />
    
    <script src="${pageContext.request.contextPath}/resources/app/angular.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/angular-sanitize.js"></script>
    
    <script src="${pageContext.request.contextPath}/resources/app/ui-bootstrap-tpls-0.13.4.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/bootstrap.min.js"></script>  
	<script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script> 
	<script src="https://angular-file-upload.appspot.com/js/ng-file-upload-shim.js"></script>
	<script src="https://angular-file-upload.appspot.com/js/ng-file-upload.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-chart.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/app/Demo/DemoAppChandni.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/FileUploadChandniService.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Services/UserChandniService.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Directive/FileUploadChandniDirective.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/Demo/Directive/FormValidationChandni.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/BaseChandniController.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/UserAddEditChandniController.js"></script>

</head>
<body ng-app="DemoChandni" ng-cloak>
    <div class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a href="chandni-user-list" class="navbar-brand">Demo Application</a>
                <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="chandni-user-list">Manage Users</a>
                    </li>
                    <li>
                        <a href="chandni-charts">Statistics</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <br/>
    <div class="container">
         <div class="col-lg-10">
                <div class="well bs-component" ng-controller="userAddEditChandniController as ctrl" ng-init="ctrl.Init()">
                    <form class="form-horizontal" name="userForm" enctype="multipart/form-data"  novalidate="novalidate" form-submit-validation="">
                        <fieldset>
                            <legend><span ng-show="userChandni.userId == 0" >Add </span> <span ng-show="userChandni.userId != 0" >Edit </span> User Details</legend>

                            <div class="form-group" show-errors>
                                <label for="txtFullname" class="col-lg-2 control-label required-cls">Full name</label>
                                <div class="col-lg-10">
                                 	<input type="hidden" id="txtUserId" ng-model="userChandni.userId" data-ng-init="userChandni.userId ='${userChandni.userId}'">
                                    <input type="text" class="form-control" id="txtFullname" name="txtFullname" placeholder="Full name" maxlength="100" data-ng-pattern="/^[a-zA-Z0-9 ]*$/" ng-model="userChandni.userFullName" data-ng-init="ctrl.getFullName('${userChandni.userFirstName}','${userChandni.userLastName}')" required autofocus>
                                    <div data-ng-show="userForm.txtFullname.$dirty && userForm.txtFullname.$error.required">
										<span style="color:red;">Please enter Full name.</span>
									</div>
                                </div>
                            </div>

                            <div class="form-group" show-errors>
                                <label for="txtPassword" class="col-lg-2 control-label required-cls">Password</label>
                                <div class="col-lg-10">
                                    <input type="password" required class="form-control" id="txtPassword" name="txtPassword" placeholder="Password" maxlength="25" ng-model="userChandni.userPassword" required data-ng-init="userChandni.userPassword ='${userChandni.userPassword}'">
                                    <div data-ng-show="userForm.txtPassword.$dirty && userForm.txtPassword.$error.required">
										<span style="color:red;">Please enter Password.</span>
									</div>
                                </div>
                            </div>

                            <div class="form-group" show-errors>
                                <label class="col-lg-2 control-label required-cls">Gender</label>
                                <div class="col-lg-10">
                                    <div class="radio" ng-repeat="item in genderOptions">
                                        <label>
                                            <input type="radio" name="gender" id="genderMale" ng-value="{{item.genderId}}"  ng-checked="{{item.selected}}" ng-model="userChandni.userGender" data-ng-init="ctrl.setGender('${userChandni.userGender}')">
                                            {{item.name}}
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group" show-errors>
                                <label for="txtEmail" class="col-lg-2 control-label required-cls">Email</label>
                                <div class="col-lg-10">
                                    <input type="text" required  ng-pattern="/^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/" maxlength="100" class="form-control" ng-change="ctrl.onEmailAddressChange(userChandni.userEmail)" id="txtEmail" name="txtEmail" placeholder="Email Address" ng-model="userChandni.userEmail" data-ng-init="userChandni.userEmail ='${userChandni.userEmail}'">
                                    <div data-ng-show="userForm.txtEmail.$error.pattern">
										<span style="color:red;">Please enter valid Email address.</span>
									</div>
									<div data-ng-show="userForm.txtEmail.$dirty && userForm.txtEmail.$error.required">
										<span style="color:red;">Please enter Email address.</span>
									</div>
									<div data-ng-show="userForm.txtEmail.$dirty && flag">
										<span style="color:red;">Email address already exist.</span>
									</div>
                                </div>
                            </div>

                            <div class="form-group" show-errors>
                                <label for="txtDob" class="col-lg-2 control-label">Date Of Birth</label>
                                <div class="col-lg-10">
                                    <input type="text" name="txtDob" id="txtDob" class="form-control" maxlength="10" placeholder="MM/DD/YYYY" ng-model="userChandni.userDateofbirth"  data-ng-init="ctrl.setDob('${userChandni.userDob}')" />
                                </div>
                            </div>

                            <div class="form-group" show-errors>
                                <label for="txtPhoneNumber" class="col-lg-2 control-label">Phone number</label>
                                <div class="col-lg-10">
                                    <input type="text" data-ng-pattern="/^[0-9]*$/" id="txtPhoneNumber" maxlength="16" name="txtPhoneNumber" class="form-control" placeholder="Phone number" ng-model="userChandni.userPhoneNumber" data-ng-init="userChandni.userPhoneNumber ='${userChandni.userPhoneNumber}'"/>
                                     <div data-ng-show="userForm.txtPhoneNumber.$error.pattern">
										<span style="color:red;">Please enter valid Phone number.</span>
									</div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="hobby" class="col-lg-2 control-label">Hobbies</label>
                                <div class="col-lg-10">
                                    <div class="checkbox" ng-repeat="item in hobbiesOption" data-ng-init="ctrl.getHobbiesInit('${userChandni.userHobbies}')">
                                        <label>
                                            <input type="checkbox" ng-click="ctrl.getHobbies($index, item.text)" ng-checked="ctrl.checkItem(item.text)"> {{item.text}}
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="ddlCity" class="col-lg-2 control-label">City</label>
                                <div class="col-lg-10">
                                    <select class="form-control" id="ddlCity" ng-model="userChandni.userCity" data-ng-init="ctrl.getUserCity('${userChandni.userCity}')">
                                        <option value="">-- Select City --</option>
                                        <option value="{{item.value}}" ng-repeat="item in cityOptions">{{item.text}}</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="textAddress" class="col-lg-2 control-label">Address</label>
                                <div class="col-lg-10">
                                    <textarea class="form-control" rows="3" id="textAddress" maxlength="500" ng-model="userChandni.userAddress" data-ng-init="userChandni.userAddress ='${userChandni.userAddress}'"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="userImage" class="col-lg-2 control-label">User Image</label>
                                 <div class="col-lg-10">
                                    <input type="file" name="file" id="fileUserImage" class="form-control" file-upload="profileimagefile" accept="image/*" data-ng-init="userChandni.userImage ='${userChandni.userImage}'">
                                  	<img ng-show='userChandni.userImage != ""' ng-src="${pageContext.request.contextPath}/resources/images_chandni/{{userChandni.userImage}}" class="img-thumbnail" style="width: 50px;height: 50px;" />
								  	<div ng-if="ctrl.imageError">
    									<span style="color:red;">File size exceeds 3MB.</span>
  								  	</div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-lg-10 col-lg-offset-2">
                                    <button type="submit" class="btn btn-primary" ng-click="userForm.$valid && ctrl.onSave(userForm.$invalid)">Submit</button>
                                    <a href="chandni-user-list" class="btn btn-default">Cancel</a>
                                </div>
                            </div>
                        </fieldset>
                    </form>
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