/// <reference path='../../_all.ts' />

module Demo {
    export interface IEmployeeService {
        AddEmployee($scope: IEmployeeScope, employeeMaster: EmployeeMasterParth): ng.IHttpPromise<string>;

        GetAllEmployees(): ng.IHttpPromise<EmployeeMasterParth>;

        GetEmployeeById($scope: IEmployeeScope, employeeId: number): ng.IHttpPromise<EmployeeMasterParth>;

        DeleteEmployee($scope: IEmployeeScope, employeeId: number): ng.IHttpPromise<string>;
    }
}