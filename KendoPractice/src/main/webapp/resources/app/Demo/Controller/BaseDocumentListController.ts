/// <reference path='../../_allDocumentList.ts' />

module DocumentList
{
    export class BaseDocumentListController
    {
        'use strict';
        private $baseScope: IDocumentListScope;
        constructor($scope: IDocumentListScope)
        {
            this.$baseScope = $scope;
        }
        public BaseInit()
        {
        }
    }
    angular.module("DocumentList").controller("BaseDocumentListController", BaseDocumentListController);
}