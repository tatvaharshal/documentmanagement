/// <reference path='../../_allUrvashi.ts' />
var DemoUrvashi;
(function (DemoUrvashi) {
    'use strict';
    var UserServiceUrvashi = (function () {
        function UserServiceUrvashi(http, $q) {
            var _this = this;
            this.success = function (response) { return response.data; };
            this.fail = function (error) {
                var msg = error.data.description;
                var reason = 'query for people failed.';
                return _this.$q.reject(msg);
            };
            this._http = http;
            this.$q = $q;
        }
        UserServiceUrvashi.prototype.saveUser = function (user) {
            return this._http.post("/saveUser", user)
                .then(this.success)
                .catch(this.fail);
        };
        UserServiceUrvashi.prototype.listUser = function () {
            return this._http.get("/listUser")
                .then(this.success)
                .catch(this.fail);
        };
        UserServiceUrvashi.prototype.deleteUser = function (id) {
            console.log("inside service + " + id);
            return this._http.post("/deleteUser", id)
                .then(this.success)
                .catch(this.fail);
        };
        UserServiceUrvashi.prototype.updateUser = function (user) {
            return this._http.post("/updateUser", user)
                .then(this.success)
                .catch(this.fail);
        };
        UserServiceUrvashi.prototype.getUserById = function (id) {
            return this._http.get("/getUserById", id)
                .then(this.success)
                .catch(this.fail);
        };
        UserServiceUrvashi.$inject = ["$http", "$q"];
        return UserServiceUrvashi;
    }());
    DemoUrvashi.UserServiceUrvashi = UserServiceUrvashi;
    angular.module("DemoUrvashi").service("userServiceUrvashi", UserServiceUrvashi);
})(DemoUrvashi || (DemoUrvashi = {}));
