/// <reference path='../../_all.ts' />
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var Demo;
(function (Demo) {
    var EmployeeController = (function (_super) {
        __extends(EmployeeController, _super);
        /// Conctructor
        function EmployeeController($scope, $location, employeeService, $filter, fileUploadService) {
            _super.call(this, null);
            this.$scope = $scope;
            this.$location = $location;
            this.employeeService = employeeService;
            this.$filter = $filter;
            this.fileUploadService = fileUploadService;
            this.count = 0;
            $scope.employees = this.employees;
            $scope.vm = this;
            $scope.technologies = [];
            $scope.labels = ["Download Sales", "In-Store Sales", "Mail-Order Sales"];
            $scope.data = [300, 500, 100];
            this.GetAllEmployee();
            $scope.$watch('employeeMaster.birthDate', function (newValue) {
                if (newValue != undefined) {
                    $scope.mydateOfBirth = $filter('date')(new Date(newValue), 'MM/dd/yyyy');
                }
            });
        }
        // Init
        EmployeeController.prototype.Init = function () {
        };
        EmployeeController.prototype.GetAllEmployee = function () {
            var _this = this;
            this.employeeService.GetAllEmployees().then(function (response) {
                _this.employees = response.data;
                _this.$scope.employees = response.data;
            });
        };
        EmployeeController.prototype.SelectTechnology = function (techId) {
            if (this.$scope.technologies.indexOf(techId) > -1) {
                this.$scope.technologies = this.$scope.technologies.filter(function (x) { return x != techId; });
            }
            else {
                this.$scope.technologies.push(techId);
            }
        };
        EmployeeController.prototype.AddEmployee = function () {
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
            this.employeeService.AddEmployee(this.$scope, this.$scope.employeeMaster).then(function (response) {
                if (response.status == 200) {
                    alert("You have successfully updated employee");
                    location.href = "/employeelist";
                }
            });
        };
        EmployeeController.prototype.EditEmployeeInit = function (employeeId) {
            var _this = this;
            if (employeeId > 0) {
                this.employeeService.GetEmployeeById(this.$scope, employeeId).then(function (response) {
                    if (response.status == 200) {
                        _this.$scope.employeeMaster = response.data;
                        _this.$scope.technologies = _this.$scope.employeeMaster.technologies.split(",");
                    }
                });
            }
        };
        EmployeeController.prototype.DeleteEmployee = function (employeeId) {
            var _this = this;
            this.employeeService.DeleteEmployee(this.$scope, employeeId).then(function (response) {
                _this.GetAllEmployee();
            });
        };
        EmployeeController.$inject = [
            '$scope',
            '$location',
            'employeeService',
            '$filter',
            'fileUploadService'
        ];
        return EmployeeController;
    }(Demo.BaseController));
    Demo.EmployeeController = EmployeeController;
    angular.module("Demo").controller("employeeController", EmployeeController);
})(Demo || (Demo = {}));
