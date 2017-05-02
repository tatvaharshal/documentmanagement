/// <reference path='../../_allDocumentList.ts' />
var DocumentList;
(function (DocumentList) {
    var BaseDocumentListController = (function () {
        function BaseDocumentListController($scope) {
            this.$baseScope = $scope;
        }
        BaseDocumentListController.prototype.BaseInit = function () {
        };
        return BaseDocumentListController;
    }());
    DocumentList.BaseDocumentListController = BaseDocumentListController;
    angular.module("DocumentList").controller("BaseDocumentListController", BaseDocumentListController);
})(DocumentList || (DocumentList = {}));
//# sourceMappingURL=BaseDocumentListController.js.map