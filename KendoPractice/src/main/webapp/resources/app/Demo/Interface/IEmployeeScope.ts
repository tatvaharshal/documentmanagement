/// <reference path='../../_all.ts' />

module Demo {
    export interface IEmployeeScope extends ng.IScope {        
        vm: any;
        employees: EmployeeMasterParth[];
        technologies: string[];
        mydateOfBirth: string;
        myFile: any;
        employeeMaster: EmployeeMasterParth;
        labels:string[];
        data:number[];
    }
}