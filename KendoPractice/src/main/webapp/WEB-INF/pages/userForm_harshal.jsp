<!-- saved from url=(0022)http://internet.e-mail -->
<!DOCTYPE html>
<html>
<head>
    <title>_HarShaL_ Application</title>
    <link href="https://bootswatch.com/cerulean/bootstrap.min.css" rel="stylesheet" />
    <link href="https://bootswatch.com/assets/css/custom.min.css" rel="stylesheet" />
    <link href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>
    
    <script src="${pageContext.request.contextPath}/resources/app/angular.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/ui-bootstrap-tpls-0.13.4.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-sanitize.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/angular-chart.js"></script>
	<script src="${pageContext.request.contextPath}/resources/scripts/angular-route.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/app/Demo/HarshalAPP.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Services/FileUploadService_harshal.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Services/UserService_harshal.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Directive/FileUploadDirective_harshal.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/BaseController_harshal.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/Demo/Controller/UserController_harshal.js"></script>
    
    <style type="text/css">
    	.ng-touched.ng-invalid.ng-invalid-required{
    		border: 1px solid red;
    	}
    </style>   
    
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
    <div class="container" ng-controller="userController_harshal as ctrl" ng-init="ctrl.getUserDetailById('${userId}');">
        <div class="row">
        	<div class="col-lg-10">
                <div class="well bs-component">
                    <form class="form-horizontal" name="userForm_harshal" ng-submit="userForm_harshal.$valid && ctrl.userSaveUpdate(user.id) && hobbies.length != 0" >
                        <fieldset>
                            <legend>Add or Edit User Details</legend>
							<input type="hidden" ng-model="user.id" >
							
							<div class="form-group">
								<div class="col-lg-2 col-lg-offset-2">
									<a href="${pageContext.request.contextPath}/resources/images_harshal/{{user.profileimage}}" target="_blank" ng-if="user.id != undefined"><img src="${pageContext.request.contextPath}/resources/images_harshal/{{user.profileimage}}" class="img-thumbnail" alt="userImage" /></a>
								</div>
							</div>
							
                            <div class="form-group">
                                <label for="txtFullname" class="col-lg-2 control-label"><span style="color:red;">*</span>Full name :</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" name="txtFullname" id="txtFullname" placeholder="Full name" ng-model="user.fullname" required maxlength="100" data-ng-pattern="/^[a-zA-Z0-9 ]*$/" >
                                </div>
                                <div class="col-lg-10 col-lg-offset-2" data-ng-show="userForm_harshal.txtFullname.$dirty && userForm_harshal.txtFullname.$error.required || userForm_harshal.txtFullname.$dirty && userForm_harshal.txtFullname.$invalid">
									<span style="color: red;">Please Insert full name or insert valid full name.</span>
								</div>
                            </div>

                            <div class="form-group">
                                <label for="txtPassword" class="col-lg-2 control-label"><span style="color:red;">*</span>Password :</label>
                                <div class="col-lg-10">
                                    <input type="password" class="form-control" name="txtPassword" id="txtPassword" placeholder="Password" ng-model="user.password" required>
                                </div>
                                <div class="col-lg-10 col-lg-offset-2" data-ng-show="userForm_harshal.txtPassword.$dirty && userForm_harshal.txtPassword.$error.required">
									<span style="color: red;">Please Insert password.</span>
								</div>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-2 control-label"><span style="color:red;">*</span>Gender :</label>
                                <div class="col-lg-10">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="gender" id="genderMale" value="Male" checked="" ng-model="user.gender" ng-required="!user.gender">
                                            Male
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="gender" id="genderFemale" value="Female" ng-model="user.gender" ng-required="!user.gender">
                                            Female
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="txtEmail" class="col-lg-2 control-label"><span style="color:red;">*</span>Email :</label>
                                <div class="col-lg-10">
                                    <input type="email" class="form-control" id="txtEmail" name="txtEmail" placeholder="Email Address" ng-model="user.email" required ng-pattern='/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/'>
                                </div>
                                <div class="col-lg-10 col-lg-offset-2" data-ng-show="userForm_harshal.txtEmail.$error.pattern || userForm_harshal.txtEmail.$dirty && userForm_harshal.txtEmail.$error.required">
									<span style="color: red;">Please Insert Valid Email address.</span>
								</div>
                            </div>

                            <div class="form-group">
                                <label for="txtDob" class="col-lg-2 control-label"><span style="color:red;">*</span>Date Of Birth :</label>
                                <div class="col-lg-10">
                                    <input type="text" id="txtDob" name="txtDob" class="form-control" placeholder="yyyy-mm-dd" ng-model="user.dateofbirth" required="required"/>
                                </div>
                                <div class="col-lg-10 col-lg-offset-2" data-ng-show="userForm_harshal.txtDob.$dirty && userForm_harshal.txtDob.$error.required">
									<span style="color: red;">Please Insert date of birth.</span>
								</div>
                            </div>

                            <div class="form-group">
                                <label for="txtPhoneNumber" class="col-lg-2 control-label"><span style="color:red;">*</span>Phone number :</label>
                                <div class="col-lg-10">
                                    <input type="text" id="txtPhoneNumber" name="txtPhoneNumber" class="form-control" placeholder="Phone number" ng-model="user.mobilenumber" required="required" ng-pattern="/^\d{10}$/"/>
                                </div>
                                <div class="col-lg-10 col-lg-offset-2" data-ng-show="userForm_harshal.txtPhoneNumber.$error.pattern || userForm_harshal.txtPhoneNumber.$dirty && userForm_harshal.txtPhoneNumber.$error.required">
									<span style="color: red;">Please Insert Valid Mobile number.</span>
								</div>
                            </div>

                            <div class="form-group">
                                <label for="hobby" class="col-lg-2 control-label"><span style="color:red;">*</span>Hobbies :</label>
                                <div class="col-lg-10">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="hobby" value="Cricket" ng-checked="hobbies.indexOf('Cricket') > -1" ng-click="ctrl.selectHobby('Cricket')" ng-required="hobbies.length==0"> Cricket
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="hobby" value="Football" ng-checked="hobbies.indexOf('Football') > -1" ng-click="ctrl.selectHobby('Football')" ng-required="hobbies.length==0"> Football
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="hobby" value="Tennis" ng-checked="hobbies.indexOf('Tennis') > -1" ng-click="ctrl.selectHobby('Tennis')" ng-required="hobbies.length==0"> Tennis
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="hobby" value="Chess" ng-checked="hobbies.indexOf('Chess') > -1" ng-click="ctrl.selectHobby('Chess')" ng-required="hobbies.length==0"> Chess
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="ddlCity" class="col-lg-2 control-label"><span style="color:red;">*</span>City :</label>
                                <div class="col-lg-10">
                                    <select class="form-control" id="ddlCity" name="txtCity" ng-model="user.city" required="required">
                                        <option value="">--Select--</option>
                                        <option selected="selected" value="Ahmedabad">Ahmedabad</option>
                                        <option value="Gandhinagar">Gandhinagar</option>
                                        <option value="Rajkot">Rajkot</option>
                                        <option value="Mehsana">Mehsana</option>
                                        <option value="Surat">Surat</option>
                                    </select>
                                </div>
                                <div class="col-lg-10 col-lg-offset-2" data-ng-show="userForm_harshal.txtCity.$dirty && userForm_harshal.txtCity.$error.required">
									<span style="color: red;">Please Select City.</span>
								</div>
                            </div>

                            <div class="form-group">
                                <label for="textAddress" class="col-lg-2 control-label"><span style="color:red;">*</span>Address :</label>
                                <div class="col-lg-10">
                                    <textarea class="form-control" rows="3" id="textAddress" name="txtAddress" ng-model="user.address" required="required"></textarea>
                                </div>
                                <div class="col-lg-10 col-lg-offset-2" data-ng-show="userForm_harshal.txtAddress.$dirty && userForm_harshal.txtAddress.$error.required">
									<span style="color: red;">Please Enter address.</span>
								</div>
                            </div>

                            <div class="form-group">
                                <label for="fileUserImage" class="col-lg-2 control-label" ng-if="user.id == undefined">User Image :</label>
                                <label for="fileUserImage" class="col-lg-2 control-label" ng-if="user.id > 0">Change Image :</label>
                                <div class="col-lg-10">
                                    <input type="file" name="file" id="fileUserImage" class="form-control" file-upload="profileimagefile" accept="image/*">
                                </div>
                            </div>
							<div class="form-group">
							    <span class="col-lg-10 col-lg-offset-2 text-muted"><em><span style="color:red;">*</span> Indicates required field</em></span>
							</div> 
                            <div class="form-group">
                                <div class="col-lg-10 col-lg-offset-2">
                                    <button type="submit" class="btn btn-primary" >Submit</button>
                                    <a href="${pageContext.request.contextPath}/user_harshal/usersList_harshal" class="btn btn-default">Cancel</a>
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
            format: 'yyyy-mm-dd',
            autoclose: true
        });
    </script>
</body>
</html>