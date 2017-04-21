/// <reference path='../../_allChandni.ts' />
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var DemoChandni;
(function (DemoChandni) {
    var UserDashboardChandniController = (function (_super) {
        __extends(UserDashboardChandniController, _super);
        /// Conctructor
        function UserDashboardChandniController($scope, $location, $window, userChandniService) {
            var _this = _super.call(this, $scope) || this;
            _this.$scope = $scope;
            _this.$location = $location;
            _this.$window = $window;
            _this.userChandniService = userChandniService;
            return _this;
        }
        // Init
        UserDashboardChandniController.prototype.Init = function () {
            _super.prototype.BaseInit.call(this);
        };
        UserDashboardChandniController.prototype.usersList = function () {
            var _this = this;
            this.userChandniService.GetUsersByCity(this.$scope).then(function (data) {
                // pie chart configuration
                _this.$scope.pieChartUserLabel = JSON.parse(data[0]);
                _this.$scope.pieChartUserData = JSON.parse(data[1]);
                _this.$scope.colors = ['#72C02C', '#3498DB', '#717984', '#F1C40F'];
                _this.$scope.pieChartUserOption = {
                    title: {
                        display: true,
                        text: 'Users / City (Pie Chart)'
                    }
                };
                // bar chart configuration
                _this.$scope.barChartUserLabel = JSON.parse(data[0]);
                _this.$scope.barChartUserSeries = ['Users'];
                _this.$scope.barChartUserData = JSON.parse(data[1]);
                _this.$scope.barChartUserOption = {
                    title: {
                        display: true,
                        text: 'Users / City (Bar Chart)'
                    },
                    scales: {
                        yAxes: [{
                                ticks: {
                                    min: 0,
                                    stepSize: 1
                                }
                            }]
                    }
                };
            });
        };
        return UserDashboardChandniController;
    }(DemoChandni.BaseChandniController));
    UserDashboardChandniController.$inject = [
        '$scope',
        '$location',
        '$window',
        'userChandniService',
    ];
    DemoChandni.UserDashboardChandniController = UserDashboardChandniController;
    angular.module("DemoChandni").controller("userDashboardChandniController", UserDashboardChandniController);
})(DemoChandni || (DemoChandni = {}));
