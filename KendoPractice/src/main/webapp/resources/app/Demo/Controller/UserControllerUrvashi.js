/// <reference path='../../_allUrvashi.ts' />
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var DemoUrvashi;
(function (DemoUrvashi) {
    var UserControllerUrvashi = (function (_super) {
        __extends(UserControllerUrvashi, _super);
        /// Conctructor
        function UserControllerUrvashi($scope, $location, $window, $modal, userServiceUrvashi, fileUploadServiceUrvashi, $filter) {
            _super.call(this, $scope);
            this.$scope = $scope;
            this.$location = $location;
            this.$window = $window;
            this.$modal = $modal;
            this.userServiceUrvashi = userServiceUrvashi;
            this.fileUploadServiceUrvashi = fileUploadServiceUrvashi;
            this.$filter = $filter;
            $scope.vm = this;
            $scope.gender = ["male", "female"];
            $scope.techArray = ["JAVA", ".Net", "Android", "React"];
            $scope.country = ["India", "UK"];
        }
        UserControllerUrvashi.prototype.saveUser = function () {
            var _this = this;
            if (this.$scope.user.id != "") {
                var file = this.$scope.profileimagefile;
                if (file != null || file != undefined) {
                    var name = file.name;
                    var lastDot = name.lastIndexOf(".");
                    var lastValue = name.slice(lastDot, name.length);
                    if (lastValue == '.png' || lastValue == '.jpg' || lastValue == '.gif' || lastValue == '.jpeg') {
                        var uploadUrl = "/userImageUploadUrvashi";
                        this.fileUploadServiceUrvashi.uploadFileToUrl(file, uploadUrl);
                        this.$scope.user.image = name;
                        this.userServiceUrvashi.updateUser(this.$scope.user).then(function (data) {
                            if (data.success == 'success') {
                                _this.$window.location.href = "UsersListUrvashi";
                            }
                        });
                    }
                    else {
                        this.$scope.errorMessage = "Please Upload Valid User image";
                        this.$scope.error = true;
                    }
                }
                else if (file == undefined) {
                    this.userServiceUrvashi.updateUser(this.$scope.user).then(function (data) {
                        if (data == 'success') {
                            _this.$window.location.href = "UsersListUrvashi";
                        }
                    });
                }
                else {
                    this.$scope.errorMessage = "Please Upload User image";
                    this.$scope.error = true;
                }
            }
            else {
                var file = this.$scope.profileimagefile;
                if (file != null || file != undefined) {
                    var name = file.name;
                    var lastDot = name.lastIndexOf(".");
                    var lastValue = name.slice(lastDot, name.length);
                    if (lastValue == '.png' || lastValue == '.jpg' || lastValue == '.gif' || lastValue == '.jpeg') {
                        var uploadUrl = "/userImageUploadUrvashi";
                        this.fileUploadServiceUrvashi.uploadFileToUrl(file, uploadUrl);
                        this.$scope.user.image = name;
                        this.userServiceUrvashi.saveUser(this.$scope.user).then(function (data) {
                            if (data == 'success') {
                                _this.$window.location.href = "UsersListUrvashi";
                            }
                        });
                    }
                    else {
                        this.$scope.errorMessage = "Please Upload Valid User image";
                        this.$scope.error = true;
                    }
                }
                else {
                    this.$scope.errorMessage = "Please Upload User image";
                    this.$scope.error = true;
                }
            }
        };
        UserControllerUrvashi.prototype.getListOfUser = function () {
            var _this = this;
            this.userServiceUrvashi.listUser().then(function (data) {
                if (data != undefined) {
                    _this.$scope.userList = data;
                }
            });
        };
        UserControllerUrvashi.prototype.getUserImage = function (image) {
            this.$scope.user.image = image;
        };
        UserControllerUrvashi.prototype.deleteuserById = function (id) {
            this.$modal.open({
                template: '<div class="modal-header"><h3 class="modal-title">Confirm</h3></div><div class="modal-body">Are you sure you want to delete this user ?</div><div class="modal-footer"><button class="btn btn-primary" type="button" ng-click="modal.onConfirm(' + id + ')">Ok</button><button class="btn btn-default" type="button" ng-click="modal.close()">Cancel</button></div>',
                controller: 'modalInstanceControllerUrvashi',
                controllerAs: 'modal'
            });
        };
        UserControllerUrvashi.prototype.setArray = function (arr) {
            if (arr != "") {
                arr = arr.replace(" ", "");
                ;
                arr = arr.slice(1, -1);
                var temp = arr.split(",");
                this.$scope.user.techArray = temp;
            }
        };
        UserControllerUrvashi.$inject = [
            '$scope',
            '$location',
            '$window',
            '$modal',
            'userServiceUrvashi',
            'fileUploadServiceUrvashi',
            '$filter'
        ];
        return UserControllerUrvashi;
    }(DemoUrvashi.BaseControllerUrvashi));
    DemoUrvashi.UserControllerUrvashi = UserControllerUrvashi;
    angular.module("DemoUrvashi").controller("userControllerUrvashi", UserControllerUrvashi);
})(DemoUrvashi || (DemoUrvashi = {}));
