/// <reference path='../../_allUrvashi.ts' />

module DemoUrvashi {
    export class BaseControllerUrvashi {
        'use strict';

        private $baseScope: IUrvashiScope;

        constructor($scope: IUrvashiScope) {
            this.$baseScope = $scope;
        }
    }
    
    angular.module("DemoUrvashi").controller("baseControllerUrvashi", BaseControllerUrvashi);
}