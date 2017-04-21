/// <reference path='../../_all.ts' />
var Demo;
(function (Demo) {
    'use strict';
    var UserServiceChirag = (function () {
        function UserServiceChirag(http, $q) {
            var _this = this;
            this.success = function (response) { return response.data; };
            this.fail = function (error) {
                return _this.$q.reject(error.status);
            };
            this._http = http;
            this.$q = $q;
        }
        UserServiceChirag.prototype.GetEditPage = function (id) {
            return this._http.get('/userChirag/editUser/' + id)
                .then(this.success)["catch"](this.fail);
        };
        UserServiceChirag.prototype.GetUserByID = function (id) {
            return this._http.get('/userChirag/getUser/' + id)
                .then(this.success)["catch"](this.fail);
        };
        UserServiceChirag.prototype.GetUserList = function () {
            return this._http.get('/userChirag/getUserList')
                .then(this.success)["catch"](this.fail);
        };
        UserServiceChirag.prototype.SaveUser = function ($scope, userMaster) {
            return this._http.post("/userChirag/saveUser/", userMaster)
                .then(this.success)["catch"](this.fail);
        };
        UserServiceChirag.prototype.UpdateUser = function ($scope, userMaster) {
            return this._http.post("/userChirag/updateUser/", userMaster)
                .then(this.success)["catch"](this.fail);
        };
        UserServiceChirag.prototype.DeleteUser = function ($scope, id) {
            return this._http.post('/userChirag/deleteUser', id)
                .then(this.success)["catch"](this.fail);
        };
        UserServiceChirag.prototype.getCharts = function ($scope) {
            return this._http.get('/userChirag/getCharts')
                .then(this.success)["catch"](this.fail);
        };
        return UserServiceChirag;
    }());
    UserServiceChirag.$inject = ["$http", "$q"];
    Demo.UserServiceChirag = UserServiceChirag;
    angular.module("Demo").service("userServiceChirag", UserServiceChirag);
})(Demo || (Demo = {}));
