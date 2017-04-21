/// <reference path='../../_all_harshal.ts' />
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var HarshalDemo;
(function (HarshalDemo) {
    var UserController_harshal = (function (_super) {
        __extends(UserController_harshal, _super);
        /// Conctructor
        function UserController_harshal($scope, $location, $window, userService_harshal, fileUploadService_harshal, $modal, $filter) {
            _super.call(this, $scope);
            this.$scope = $scope;
            this.$location = $location;
            this.$window = $window;
            this.userService_harshal = userService_harshal;
            this.fileUploadService_harshal = fileUploadService_harshal;
            this.$modal = $modal;
            this.$filter = $filter;
            this._url = localStorage._url;
            $scope.hobbies = [];
            console.log(this._url);
        }
        UserController_harshal.prototype.selectHobby = function (hobby) {
            if (this.$scope.hobbies.indexOf(hobby) > -1) {
                this.$scope.hobbies = this.$scope.hobbies.filter(function (x) { return x != hobby; });
            }
            else {
                this.$scope.hobbies.push(hobby);
            }
        };
        UserController_harshal.prototype.userList = function () {
            var _this = this;
            this.status = this.$location.search().status;
            if (!angular.isUndefined(this.status) && this.status != null && this.status != "") {
                this.$scope.status = this.status.trim();
            }
            this.users = this.$scope.users = new Array();
            this.userService_harshal.getUserList_harshal(this.$scope).then(function (data) {
                _this.users = data;
                _this.$scope.users = data;
                console.log(' users = ' + _this.$scope.users);
            });
        };
        UserController_harshal.prototype.userSaveUpdate = function (userId) {
            var _this = this;
            if (!angular.isUndefined(this.$scope.userForm_harshal)) {
                this.user = this.$scope.user;
                this.user.hobby = this.$scope.hobbies.join(", ");
                var file = this.$scope.profileimagefile;
                if (file != null || file != undefined) {
                    var name = file.name;
                    var uploadUrl = this._url + "/user_harshal/userImageUpload_harshal";
                    this.fileUploadService_harshal.uploadFileToUrl(file, uploadUrl);
                    this.user.profileimage = name;
                }
                if (!angular.isUndefined(userId) && userId != null && userId != "" && userId > 0) {
                    this.userService_harshal.updateUser_harshal(this.$scope, this.user).then(function (data) {
                        if (data == 'success') {
                            _this.$window.location.href = _this._url + "/user_harshal/usersList_harshal#/?status=update";
                        }
                    });
                }
                else {
                    this.userService_harshal.saveUser_harshal(this.$scope, this.user).then(function (data) {
                        if (data.success == 'success') {
                            _this.$window.location.href = _this._url + "/user_harshal/usersList_harshal#/?status=save";
                        }
                    });
                }
            }
        };
        UserController_harshal.prototype.userDelete = function (userId) {
            this.$modal.open({
                template: '<div class="modal-header"><h3 class="modal-title">Confirm</h3></div><div class="modal-body">Are you sure you want to delete this user ?</div><div class="modal-footer"><button class="btn btn-primary" type="button" ng-click="modal.onConfirm(' + userId + ')">Ok</button><button class="btn btn-default" type="button" ng-click="modal.close()">Cancel</button></div>',
                controller: 'modalInstanceController_harshal',
                controllerAs: 'modal'
            });
        };
        UserController_harshal.prototype.getUserDetailById = function (userId) {
            var _this = this;
            if (!angular.isUndefined(userId) && userId != null && userId != "" && userId > 0) {
                this.userService_harshal.getUserDetailById_harshal(this.$scope, userId).then(function (data) {
                    _this.user = data;
                    _this.$scope.hobbies = _this.user.hobby.split(", ");
                    _this.user.dateofbirth = _this.$filter('date')(data.dateofbirth, 'yyyy-MM-dd');
                    _this.$scope.user = _this.user;
                    console.log(' users = ' + _this.$scope.user);
                });
            }
        };
        UserController_harshal.prototype.userGraphData = function () {
            var _this = this;
            this.userService_harshal.getUserCountPerCity_harshal(this.$scope).then(function (data) {
                // pie chart configuration
                _this.$scope.pieChartLabel_harshal = JSON.parse(data[0]);
                _this.$scope.pieChartData_harshal = JSON.parse(data[1]);
                _this.$scope.pieChartOption_harshal = {
                    title: {
                        display: true,
                        text: 'User per City Pie Chart'
                    }
                };
                // bar chart configuration
                _this.$scope.barChartLabel_harshal = JSON.parse(data[0]);
                _this.$scope.barChartSeries_harshal = ['Users'];
                _this.$scope.barChartData_harshal = JSON.parse(data[1]);
                _this.$scope.barChartOption_harshal = {
                    title: {
                        display: true,
                        text: 'User per City Bar Chart'
                    },
                    scales: {
                        yAxes: [{
                                ticks: {
                                    min: 0,
                                    stepSize: 1
                                }
                            }]
                    }
                };
            });
        };
        // Init
        UserController_harshal.prototype.Init = function () {
            _super.prototype.BaseInit.call(this);
        };
        UserController_harshal.$inject = [
            '$scope',
            '$location',
            '$window',
            'userService_harshal',
            'fileUploadService_harshal',
            '$modal',
            '$filter'
        ];
        return UserController_harshal;
    }(HarshalDemo.BaseController_harshal));
    HarshalDemo.UserController_harshal = UserController_harshal;
    angular.module("HarshalDemo").controller("userController_harshal", UserController_harshal);
})(HarshalDemo || (HarshalDemo = {}));
