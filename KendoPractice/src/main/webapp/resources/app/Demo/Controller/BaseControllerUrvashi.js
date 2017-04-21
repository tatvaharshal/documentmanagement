/// <reference path='../../_allUrvashi.ts' />
var DemoUrvashi;
(function (DemoUrvashi) {
    var BaseControllerUrvashi = (function () {
        function BaseControllerUrvashi($scope) {
            this.$baseScope = $scope;
        }
        return BaseControllerUrvashi;
    }());
    DemoUrvashi.BaseControllerUrvashi = BaseControllerUrvashi;
    angular.module("DemoUrvashi").controller("baseControllerUrvashi", BaseControllerUrvashi);
})(DemoUrvashi || (DemoUrvashi = {}));
