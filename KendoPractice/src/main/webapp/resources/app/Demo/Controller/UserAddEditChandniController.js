/// <reference path='../../_allChandni.ts' />
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var DemoChandni;
(function (DemoChandni) {
    var UserAddEditChandniController = (function (_super) {
        __extends(UserAddEditChandniController, _super);
        /// Conctructor
        function UserAddEditChandniController($scope, $location, $window, userChandniService, fileUploadChandniService) {
            var _this = _super.call(this, $scope) || this;
            _this.$scope = $scope;
            _this.$location = $location;
            _this.$window = $window;
            _this.userChandniService = userChandniService;
            _this.fileUploadChandniService = fileUploadChandniService;
            _this._location = $location;
            _this.$scope.flag = false;
            return _this;
        }
        // Init
        UserAddEditChandniController.prototype.Init = function () {
            _super.prototype.BaseInit.call(this);
            this.$scope.genderOptions = [
                { genderId: 0, name: "Male" },
                { genderId: 1, name: "Female" }
            ];
            this.$scope.cityOptions = [
                { text: "Ahmedabad", value: "Ahmedabad" },
                { text: "Gandhinagar", value: "Gandhinagar" },
                { text: "Rajkot", value: "Rajkot" },
                { text: "Mehsana", value: "Mehsana" },
                { text: "Surat", value: "Surat" },
                { text: "Delhi", value: "Delhi" },
                { text: "Chennai", value: "Chennai" },
            ];
            this.$scope.hobbiesOption = [
                { text: "Cricket", value: "Cricket" },
                { text: "Football", value: "Football" },
                { text: "Tennis", value: "Tennis" },
                { text: "Chess", value: "Chess" },
            ];
            this.$scope.arrayHobbies = [];
        };
        UserAddEditChandniController.prototype.convertDate = function (timestamp) {
            var d = new Date(timestamp);
            var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
            return formattedDate;
        };
        UserAddEditChandniController.prototype.onSave = function (formIsValid) {
            var _this = this;
            this.$scope.showErrorsCheckValidity = true;
            if (!this.$scope.flag && this.$scope.userForm.$valid && !this.$scope.imageError) {
                this.data = this.$scope.userChandni;
                if (this.$scope.userChandni.userId == "") {
                    this.data.userId = 0;
                }
                else {
                    this.data.userId = this.$scope.userChandni.userId;
                }
                if (this.$scope.userChandni.userGender == "") {
                    this.data.userGender = 0;
                }
                this.data.userDob = new Date(document.getElementById("txtDob").value);
                if (this.$scope.userChandni.userFullName != "" && this.$scope.userChandni.userFullName != undefined) {
                    var fullname = this.$scope.userChandni.userFullName.split(' ');
                    this.data.userFirstName = fullname[0] == undefined ? "First" : fullname[0];
                    this.data.userLastName = fullname[1] == undefined ? " " : fullname[1];
                }
                this.data.userHobbies = this.$scope.arrayHobbies.join(",").replace(/^,|,$/g, '');
                var file = this.$scope.profileimagefile;
                if (file != null || file != undefined) {
                    var uploadUrl = "/chandni/userImageUpload";
                    this.fileUploadChandniService.uploadFileToUrl(file, uploadUrl);
                    this.data.userImage = file.name;
                }
                this.userChandniService.addUpdateUser(this.$scope, this.data).then(function (response) {
                    if (response == 'success') {
                        // Store
                        if (_this.$scope.userChandni.userId == "") {
                            localStorage.setItem("success", "User added successfully");
                        }
                        else {
                            localStorage.setItem("success", "User updated successfully");
                        }
                        _this.$window.location.href = "/chandni/chandni-user-list";
                    }
                });
            }
        };
        UserAddEditChandniController.prototype.getHobbies = function (value, item) {
            if (this.$scope.arrayHobbies.indexOf(item) === -1) {
                this.$scope.arrayHobbies.push(item);
            }
            else {
                var index = this.$scope.arrayHobbies.indexOf(item);
                this.$scope.arrayHobbies.splice(index, 1);
            }
        };
        UserAddEditChandniController.prototype.getFullName = function (firstName, lastName) {
            if (this.$scope.userChandni.userId != 0) {
                this.$scope.userChandni.userFullName = firstName + " " + lastName;
            }
        };
        UserAddEditChandniController.prototype.getUserCity = function (value) {
            this.$scope.userChandni.userCity = value;
        };
        UserAddEditChandniController.prototype.getHobbiesInit = function (value) {
            this.$scope.arrayHobbies = value.split(',');
        };
        UserAddEditChandniController.prototype.checkItem = function (item) {
            var checked = false;
            for (var i = 0; i < this.$scope.arrayHobbies.length; i++) {
                if (item == this.$scope.arrayHobbies[i]) {
                    checked = true;
                }
            }
            return checked;
        };
        UserAddEditChandniController.prototype.onEmailAddressChange = function (email) {
            var _this = this;
            this.userChandniService.FindUserByEmail(this.$scope, email).then(function (response) {
                _this.$scope.flag = response;
            });
        };
        UserAddEditChandniController.prototype.getFormattedDate = function (date) {
            var year = date.getFullYear();
            var month = (1 + date.getMonth()).toString();
            month = month.length > 1 ? month : '0' + month;
            var day = date.getDate().toString();
            day = day.length > 1 ? day : '0' + day;
            return month + '/' + day + '/' + year;
        };
        UserAddEditChandniController.prototype.setDob = function (dob) {
            if (dob != "") {
                this.$scope.userChandni.userDateofbirth = this.getFormattedDate(new Date(dob));
            }
        };
        UserAddEditChandniController.prototype.setGender = function (value) {
            if (value != null) {
                this.$scope.userChandni.userGender = value;
            }
        };
        return UserAddEditChandniController;
    }(DemoChandni.BaseChandniController));
    UserAddEditChandniController.$inject = [
        '$scope',
        '$location',
        '$window',
        'userChandniService',
        'fileUploadChandniService'
    ];
    DemoChandni.UserAddEditChandniController = UserAddEditChandniController;
    angular.module("DemoChandni").controller("userAddEditChandniController", UserAddEditChandniController);
})(DemoChandni || (DemoChandni = {}));
