<!-- saved from url=(0022)http://internet.e-mail -->
<!DOCTYPE html>
<html>
<head>
<title>Demo Application</title>
<link href="https://bootswatch.com/cerulean/bootstrap.min.css"
	rel="stylesheet" />
<link href="https://bootswatch.com/assets/css/custom.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/content/form.css"
	rel="stylesheet" />
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.css"
	rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/bootstrap.min.js"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/angular.js"></script>
<script
	src="http://vitalets.github.io/checklist-model/checklist-model.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/ui-bootstrap-tpls-0.13.4.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/angular-route.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/angular-sanitize.js"></script>
<script
	src="https://angular-file-upload.appspot.com/js/ng-file-upload-shim.js"></script>
<script
	src="https://angular-file-upload.appspot.com/js/ng-file-upload.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/app/Demo/DemoAppUrvashi.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Services/FileUploadServiceUrvashi.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Services/UserServiceUrvashi.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Directive/FileUploadDirectiveUrvashi.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Controller/BaseControllerUrvashi.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Controller/UserControllerUrvashi.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/app/Demo/Controller/ModalInstanceControllerUrvashi.js"></script>

</head>
<body ng-app="DemoUrvashi" ng-controller="userControllerUrvashi as ctrl">
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="UsersListUrvashi" class="navbar-brand">Demo Application</a>
				<button class="navbar-toggle" type="button" data-toggle="collapse"
					data-target="#navbar-main">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="navbar-collapse collapse" id="navbar-main">
				<ul class="nav navbar-nav">
					<li><a href="UsersListUrvashi">Manage Users</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-lg-10">
				<div class="well bs-component">
					<form class="form-horizontal" novalidate="novalidate"
						name="userForm">
						<fieldset>
							<legend>Add or Edit Employee Details </legend>
							
							<div class="form-group" data-ng-show="error">
								<p style="color: red;">{{errorMessage}}</p>
							</div>
							
							<div class="form-group">
							
								<label for="txtUsername" class="col-lg-2 control-label required">Username
									:</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" id="txtUsername" name="txtUsername"
										required placeholder="Username" ng-model="user.username" maxlength="50" 
										ng-init="user.username = '${edituser.username}'">
								</div>
								<!-- <p ng-show="userForm.txtUsername.$invalid && !userForm.txtUsername.$pristine" class="error">You name is required.</p> -->
								<div
									ng-show="userForm.txtUsername.$invalid && !userForm.txtUsername.$pristine">
									<span style="color: red;">Username is required.</span>
								</div>
							</div>
							<input type="hidden" ng-model="user.id"
								ng-init="user.id = '${edituser.id}'">
							<div class="form-group">
								<label for="txtPassword" class="col-lg-2 control-label required">Password
									:</label>
								<div class="col-lg-10">
									<input type="password" class="form-control" id="txtPassword" name="txtPassword" required
										placeholder="Password" ng-model="user.password" maxlength="10" 
										ng-init="user.password = '${edituser.password}'">
								</div>
								<div
									ng-show="userForm.txtPassword.$invalid && !userForm.txtPassword.$pristine">
									<span style="color: red;">Please Enter Password.</span>
								</div>
							</div>

							<div class="form-group">
								<label for="txtEmail" class="col-lg-2 control-label required">Email
									:</label>
								<div class="col-lg-10">
									<input type="email" class="form-control" id="txtEmail" required
										ng-pattern="/^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/"
										maxlength="100" name="txtEmail" placeholder="Email Address"
										ng-model="user.email"
										ng-init="user.email = '${edituser.email}'">
								</div>

								<div data-ng-show="userForm.txtEmail.$error.pattern">
									<span style="color: red;">Please enter valid Email
										address.</span>
								</div>
								<div
									data-ng-show="userForm.txtEmail.$dirty && userForm.txtEmail.$error.required">
									<span style="color: red;">Email address is required.</span>
								</div>

							</div>

							<div class="form-group">
								<label for="textAddress" class="col-lg-2 control-label">Address
									:</label>
								<div class="col-lg-10">
									<textarea class="form-control" rows="3" id="textAddress"
										ng-model="user.address"
										ng-init="user.address = '${edituser.address}'"></textarea>
								</div>
							</div>

							<div class="form-group">
								<label for="ddlCountry" class="col-lg-2 control-label required">Country
									:</label>
								<div class="col-lg-10">
									<select class="form-control" id="ddlCountry" name="ddlCountry" required
										ng-model="user.country"
										ng-init="user.country = '${edituser.country}'">
										<option ng-repeat="c in country" value="{{c}}">{{c}}</option>
									</select>
								</div>
								<div
									data-ng-show="userForm.ddlCountry.$dirty && userForm.ddlCountry.$error.required">
									<span style="color: red;">Please Select Country</span>
								</div>
								
							</div>


							<div class="form-group">
								<label class="col-lg-2 control-label required">Gender :</label>
								<div class="col-lg-10">

									<div class="radio">
										<label ng-repeat="g in gender"> <input type="radio" required
											name="gender" id="genderMale" ng-model="user.gender"
											value="{{g}}" ng-init="user.gender = '${edituser.gender}'">
											{{g}}
										</label>
									</div>
									
								</div>

								<div class="form-group" ng-init="ctrl.setArray('${techArray}')">
									<label for="technologies" class="col-lg-2 control-label">Technologies
										:</label>
									<div class="col-lg-10">
										<div class="checkbox">
											<label ng-repeat="t in techArray"> <input
												type="checkbox" checklist-model="user.techArray"
												checklist-value="t"> {{t}}&nbsp;
											</label>
										</div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label for="txtDob" class="col-lg-2 control-label">Date
									Of Birth :</label>
								<div class="col-lg-10">
									<input type="text" id="txtDob" class="form-control" maxlength="40"
										ng-model="user.dateOfBirth" placeholder="YYYY/MM/DD"
										ng-init="user.dateOfBirth = '${edituser.dateOfBirth}'" />
								</div>
							</div>

							<div class="form-group">
								<label for="txtPhoneNumber" class="col-lg-2 control-label required">Phone
									number :</label>
								<div class="col-lg-10">
									<input type="text" id="txtPhoneNumber" class="form-control" ng-pattern="/^[0-9]{1,10}$/"
										placeholder="Phone number" ng-model="user.phone" name="txtPhoneNumber"
										ng-init="user.phone = '${edituser.phone}'" required/>
								</div>
								
								<div data-ng-show="userForm.txtPhoneNumber.$error.pattern">
									<span style="color: red;">Please enter valid Phone number
										address.</span>
								</div>
							</div>
							
							<div class="form-group" data-ng-init="user.image='${edituser.image}'">
								<div class="col-lg-2 col-lg-offset-2" ng-if="user.image!=''">
									<a
										href="${pageContext.request.contextPath}/resources/image_urvashi/${edituser.image}"
										target="_blank"><img
										src="${pageContext.request.contextPath}/resources/image_urvashi/${edituser.image}"
										class="img-thumbnail" /></a>
								</div>
								
							</div>
									
							<div class="form-group">
								<label for="fileUserImage" class="col-lg-2 control-label required">User Image :</label> 
								<!-- <label for="fileUserImage" class="col-lg-2 control-label " ng-if="user.id > 0">Change Image :</label> -->

								<div class="col-lg-10">
									<input type="file" name="file" id="fileUserImage"
										class="form-control" file-upload="profileimagefile"
										accept="image/*">
								</div>
								<div
									data-ng-show="userForm.file.$dirty && userForm.file.$error.required">
									<span style="color: red;">Please Select Image</span>
								</div>
								
							</div>

							<div class="form-group">
								<div class="col-lg-10 col-lg-offset-2">
									<button type="submit" class="btn btn-primary"
										ng-click="ctrl.saveUser(userForm.$invalid)"
										ng-disabled="userForm.$invalid">Submit</button>
									<a href="UsersListUrvashi" class="btn btn-default">Cancel</a>
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
			format : 'yyyy-mm-dd',
			autoclose : true
		});
	</script>
</body>
</html>