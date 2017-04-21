/// <reference path='../../_allChandni.ts' />
var DemoChandni;
(function (DemoChandni) {
    var BaseChandniController = (function () {
        function BaseChandniController($scope) {
            this.$baseScope = $scope;
        }
        BaseChandniController.prototype.BaseInit = function () {
        };
        return BaseChandniController;
    }());
    DemoChandni.BaseChandniController = BaseChandniController;
    angular.module("DemoChandni").controller("baseChandniController", BaseChandniController);
})(DemoChandni || (DemoChandni = {}));
