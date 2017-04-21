/// <reference path='../../_all.ts' />
var Demo;
(function (Demo) {
    'use strict';
    var EmployeeService = (function () {
        function EmployeeService(http, $q) {
            var _this = this;
            this.success = function (response) { return response; };
            this.fail = function (error) {
                var msg = error.data.description;
                var reason = 'query for people failed.';
                return _this.$q.reject(msg);
            };
            this._http = http;
            this.$q = $q;
        }
        EmployeeService.prototype.AddEmployee = function ($scope, employeeMaster) {
            return this._http.post("/createEmployee/", employeeMaster)
                .then(this.success)
                .catch(this.fail);
        };
        EmployeeService.prototype.DeleteEmployee = function ($scope, employeeId) {
            return this._http.post("/deleteemployee/", employeeId)
                .then(this.success)
                .catch(this.fail);
        };
        EmployeeService.prototype.GetAllEmployees = function () {
            return this._http.get("/getallemployee")
                .then(this.success)
                .catch(this.fail);
        };
        EmployeeService.prototype.GetEmployeeById = function ($scope, employeeId) {
            return this._http.post("/getemployeebyid", employeeId)
                .then(this.success)
                .catch(this.fail);
        };
        EmployeeService.$inject = ["$http", "$q"];
        return EmployeeService;
    }());
    Demo.EmployeeService = EmployeeService;
    angular.module("Demo").service("employeeService", EmployeeService);
})(Demo || (Demo = {}));
