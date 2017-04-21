/// <reference path='../../_all.ts' />
var Demo;
(function (Demo) {
    var BaseController = (function () {
        function BaseController($scope) {
            this.$baseScope = $scope;
        }
        return BaseController;
    }());
    Demo.BaseController = BaseController;
    angular.module("Demo").controller("baseController", BaseController);
})(Demo || (Demo = {}));
