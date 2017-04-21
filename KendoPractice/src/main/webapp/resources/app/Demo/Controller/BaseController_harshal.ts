/// <reference path='../../_all_harshal.ts' />

module HarshalDemo {
    export class BaseController_harshal {
        'use strict';

        private $baseScope: ICOCOScope_harshal;

        constructor($scope: ICOCOScope_harshal) {
            this.$baseScope = $scope;
        }

        public BaseInit() {
        }

    }

    angular.module("HarshalDemo").controller("baseController_harshal", BaseController_harshal);
}