/// <reference path='../../_allChandni.ts' />

module DemoChandni {
    export class UserDashboardChandniController extends BaseChandniController {
        'use strict';

        public _window: any;
        public static $inject = [
            '$scope',
            '$location',
            '$window',
            'userChandniService',
        ];

        /// Conctructor
        constructor(private $scope: ICOCOChandniScope, private $location: ng.ILocationService, private $window: ng.IWindowService, private userChandniService: IUserChandniService) {
            super($scope);
        }

        // Init
        public Init() {
            super.BaseInit();
        }

        public usersList() {
            this.userChandniService.GetUsersByCity(this.$scope).then((data) => {
                // pie chart configuration
                this.$scope.pieChartUserLabel = JSON.parse(data[0]);
                this.$scope.pieChartUserData = JSON.parse(data[1]);
                this.$scope.colors = ['#72C02C', '#3498DB', '#717984', '#F1C40F'];
                this.$scope.pieChartUserOption = {
                    title: {
                        display: true,
                        text: 'Users / City (Pie Chart)'
                    }
                };
                
                // bar chart configuration
                this.$scope.barChartUserLabel = JSON.parse(data[0]);
                this.$scope.barChartUserSeries = ['Users'];
                this.$scope.barChartUserData = JSON.parse(data[1]);
                this.$scope.barChartUserOption = {
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
        }
    }
    angular.module("DemoChandni").controller("userDashboardChandniController", UserDashboardChandniController);
}