/// <reference path='../../_allChandni.ts' />

module DemoChandni {
    export class BaseChandniController {
        'use strict';

        private $baseScope: ICOCOChandniScope;

        constructor($scope: ICOCOChandniScope) {
            this.$baseScope = $scope;
        }

        public BaseInit() {
        }
    }

    angular.module("DemoChandni").controller("baseChandniController", BaseChandniController);
}