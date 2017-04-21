/// <reference path='../../_all_harshal.ts' />
var HarshalDemo;
(function (HarshalDemo) {
    'use strict';
    var UserService_harshal = (function () {
        function UserService_harshal(http, $q) {
            var _this = this;
            this.success = function (response) { return response.data; };
            this.fail = function (error) {
                var msg = error.data.description;
                var reason = 'query for people failed.';
                return _this.$q.reject(msg);
            };
            this._http = http;
            this.$q = $q;
            this._url = localStorage._url;
        }
        UserService_harshal.prototype.getUserList_harshal = function ($scope) {
            return this._http.get(this._url + '/user_harshal/usersListData_harshal')
                .then(this.success)
                .catch(this.fail);
        };
        UserService_harshal.prototype.saveUser_harshal = function ($scope, user) {
            return this._http.post(this._url + '/user_harshal/userSave_harshal', user)
                .then(this.success)
                .catch(this.fail);
        };
        UserService_harshal.prototype.deleteUser_harshal = function ($scope, userId) {
            return this._http.post(this._url + '/user_harshal/deleteUser_harshal', userId)
                .then(this.success)
                .catch(this.fail);
        };
        UserService_harshal.prototype.getUserDetailById_harshal = function ($scope, userId) {
            return this._http.post(this._url + '/user_harshal/getUserDetailById_harshal', userId)
                .then(this.success)
                .catch(this.fail);
        };
        UserService_harshal.prototype.updateUser_harshal = function ($scope, user) {
            return this._http.post(this._url + '/user_harshal/userUpdate_harshal', user)
                .then(this.success)
                .catch(this.fail);
        };
        UserService_harshal.prototype.getUserCountPerCity_harshal = function ($scope) {
            return this._http.get(this._url + '/user_harshal/getUserCountPerCity_harshal')
                .then(this.success)
                .catch(this.fail);
        };
        UserService_harshal.$inject = ["$http", "$q"];
        return UserService_harshal;
    }());
    HarshalDemo.UserService_harshal = UserService_harshal;
    angular.module("HarshalDemo").service("userService_harshal", UserService_harshal);
})(HarshalDemo || (HarshalDemo = {}));
