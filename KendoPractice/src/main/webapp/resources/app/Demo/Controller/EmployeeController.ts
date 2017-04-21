/// <reference path='../../_all.ts' />

module Demo {
    export class EmployeeController extends BaseController {
        'use strict';

        private count: number;
        private employees: EmployeeMasterParth[];
        public static $inject = [
            '$scope',
            '$location',
            'employeeService',
            '$filter',
            'fileUploadService'
        ];

        /// Conctructor
        constructor(private $scope: IEmployeeScope, private $location: ng.ILocationService, private employeeService: IEmployeeService, private $filter: ng.IFilterService, private fileUploadService: IFileUploadService) {
            super(null);
            
            this.count = 0;
            $scope.employees = this.employees;
            $scope.vm = this;
            $scope.technologies = [];

            $scope.labels = ["Download Sales", "In-Store Sales", "Mail-Order Sales"];
            $scope.data = [300, 500, 100];
            
            this.GetAllEmployee();

            $scope.$watch('employeeMaster.birthDate', function (newValue:any) {
                if (newValue != undefined) {                    
                    $scope.mydateOfBirth = $filter('date')(new Date(newValue), 'MM/dd/yyyy');
                }
            });
        }

        // Init
        public Init() {
        }

        GetAllEmployee() {
            this.employeeService.GetAllEmployees().then((response: ng.IHttpPromiseCallbackArg<any>) => {
                this.employees = response.data;
                this.$scope.employees = response.data;
            });
        }

        SelectTechnology(techId: string) {            
            if (this.$scope.technologies.indexOf(techId) > -1) {
                this.$scope.technologies = this.$scope.technologies.filter(x => x != techId);
            }
            else {
                this.$scope.technologies.push(techId);
            }
        }

        AddEmployee() {
            debugger;
            var file = this.$scope.myFile;
            if (file != null || file != undefined) {
                var name = file.name;
                var uploadUrl = "/imageUpload";
                this.fileUploadService.uploadFileToUrl(file, uploadUrl);
                this.$scope.employeeMaster.imageFilePath = name;
            }

            this.$scope.employeeMaster.technologies = this.$scope.technologies.join(",");
            this.$scope.employeeMaster.birthDate = this.$scope.mydateOfBirth;
            this.employeeService.AddEmployee(this.$scope, this.$scope.employeeMaster).then((response: ng.IHttpPromiseCallbackArg<any>) => {
                if (response.status == 200) {
                    alert("You have successfully updated employee");
                    location.href = "/employeelist";
                }
            });            
        }

        EditEmployeeInit(employeeId: number) {
            if (employeeId > 0) {
                this.employeeService.GetEmployeeById(this.$scope, employeeId).then((response: ng.IHttpPromiseCallbackArg<any>) => {
                    if (response.status == 200) {
                        this.$scope.employeeMaster = response.data;
                        this.$scope.technologies = this.$scope.employeeMaster.technologies.split(",");
                    }
                });
            }
        }

        DeleteEmployee(employeeId: number) {            
            this.employeeService.DeleteEmployee(this.$scope, employeeId).then((response: ng.IHttpPromiseCallbackArg<any>) => {
                this.GetAllEmployee();
            });
        }
    }

    angular.module("Demo").controller("employeeController", EmployeeController);
}