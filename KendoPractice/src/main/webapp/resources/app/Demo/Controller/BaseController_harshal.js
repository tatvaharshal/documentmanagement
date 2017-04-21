/// <reference path='../../_all_harshal.ts' />
var HarshalDemo;
(function (HarshalDemo) {
    var BaseController_harshal = (function () {
        function BaseController_harshal($scope) {
            this.$baseScope = $scope;
        }
        BaseController_harshal.prototype.BaseInit = function () {
        };
        return BaseController_harshal;
    }());
    HarshalDemo.BaseController_harshal = BaseController_harshal;
    angular.module("HarshalDemo").controller("baseController_harshal", BaseController_harshal);
})(HarshalDemo || (HarshalDemo = {}));
