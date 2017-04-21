/// <reference path='../../_all.ts' />

module Demo {
    'use strict';

    export class EmployeeService implements IEmployeeService {

        private _http: ng.IHttpService;
        private $q: ng.IQService;
        
        static $inject = ["$http", "$q"];

        constructor(http: ng.IHttpService, $q: ng.IQService) {
            this._http = http;
            this.$q = $q;
        }

        private success: (response: any) => {} = (response) => response;

        private fail: (error: any) => {} = (error) => {
            var msg = error.data.description;
            var reason = 'query for people failed.';
            return this.$q.reject(msg);
        }

        public AddEmployee($scope: IEmployeeScope, employeeMaster: EmployeeMasterParth): ng.IHttpPromise<string> {         
            return this._http.post("/createEmployee/", employeeMaster)
                .then(this.success)
                .catch(this.fail);
        }

        public DeleteEmployee($scope: IEmployeeScope, employeeId: number): ng.IHttpPromise<string> {
            return this._http.post("/deleteemployee/", employeeId)
                .then(this.success)
                .catch(this.fail);
        }

        public GetAllEmployees(): ng.IHttpPromise<EmployeeMasterParth> {
            return this._http.get("/getallemployee")
                .then(this.success)
                .catch(this.fail);
        }

        public GetEmployeeById($scope: IEmployeeScope, employeeId: number): ng.IHttpPromise<EmployeeMasterParth> {
            return this._http.post("/getemployeebyid", employeeId)
                .then(this.success)
                .catch(this.fail);
        }
    }

    angular.module("Demo").service("employeeService", EmployeeService);

}