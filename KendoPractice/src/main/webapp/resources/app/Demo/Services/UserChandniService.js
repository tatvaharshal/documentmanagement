/// <reference path='../../_allChandni.ts' />
var DemoChandni;
(function (DemoChandni) {
    'use strict';
    var UserChandniService = (function () {
        function UserChandniService(http, $q) {
            var _this = this;
            this.success = function (response) { return response.data; };
            this.fail = function (error) {
                var msg = error.data.message;
                var reason = 'query for people failed.';
                return _this.$q.reject(msg);
            };
            this._http = http;
            this.$q = $q;
        }
        UserChandniService.prototype.GetUserList = function ($scope) {
            return this._http.get('/chandni/chandniGetUserList')
                .then(this.success)["catch"](this.fail);
        };
        UserChandniService.prototype.deleteUser = function ($scope, userId) {
            return this._http.post('/chandni/deleteUser', userId)
                .then(this.success)["catch"](this.fail);
        };
        UserChandniService.prototype.addUpdateUser = function ($scope, user) {
            if ($scope.userChandni.userId == 0) {
                return this._http.post("/chandni/createUser", user)
                    .then(this.success)["catch"](this.fail);
            }
            else {
                return this._http.post("/chandni/updateUser", user)
                    .then(this.success)["catch"](this.fail);
            }
        };
        UserChandniService.prototype.FindUserByEmail = function ($scope, email) {
            return this._http.get('/chandni/findUserByEmail?email=' + email)
                .then(this.success)["catch"](this.fail);
        };
        UserChandniService.prototype.GetUsersByCity = function ($scope) {
            return this._http.get('/chandni/getUsersByCity')
                .then(this.success)["catch"](this.fail);
        };
        return UserChandniService;
    }());
    UserChandniService.$inject = ["$http", "$q"];
    DemoChandni.UserChandniService = UserChandniService;
    angular.module("DemoChandni").service("userChandniService", UserChandniService);
})(DemoChandni || (DemoChandni = {}));
