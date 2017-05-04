var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
/// <reference path='../../_allDocumentList.ts' />
var DocumentList;
(function (DocumentList) {
    var DocumentListController = (function (_super) {
        __extends(DocumentListController, _super);
        /// Conctructor
        function DocumentListController($scope, $location, $window, $modal, DocumentListService, $filter) {
            var _this = _super.call(this, $scope) || this;
            _this.$scope = $scope;
            _this.$location = $location;
            _this.$window = $window;
            _this.$modal = $modal;
            _this.DocumentListService = DocumentListService;
            _this.$filter = $filter;
            $scope.vm = _this;
            _this.DocumentListService.GetUserList(_this.$scope).then(function (data) {
                $scope.userChandniList = data;
                $scope.mainGridOptions = {
                    dataSource: {
                        type: "json",
                        data: data,
                        pageSize: 5
                    },
                    columnMenu: true,
                    filterable: true,
                    groupable: true,
                    sortable: true,
                    pageable: true,
                    columns: [{
                            field: "userFirstName",
                            title: "First Name"
                        }, {
                            field: "userEmail",
                            title: "Email"
                        }, {
                            field: "userPhoneNumber",
                            title: "PhoneNumber"
                        }, {
                            field: "userAddress",
                            title: "Address"
                        }, {
                            field: "userCity",
                            title: "City"
                        }]
                };
            });
            $scope.vm.uploadFiles = function (file, errFiles) {
                alert("File size: " + file.size + "KB");
            };
            return _this;
        }
        DocumentListController.prototype.saveDocument = function () {
            debugger;
            this.doc = this.$scope.doc;
            debugger;
            this.DocumentListService.SaveDocument(this.$scope, this.doc).then(function (data) {
                debugger;
                if (data == 'success') {
                    /*this.$window.location.href="/saveDocumentVimal";*/
                }
            })["catch"](function (err) {
                debugger;
                if (err == 409) {
                    /*this.$window.location.href="/listdocument#/?status=conflict";*/
                }
            });
        };
        return DocumentListController;
    }(DocumentList.BaseDocumentListController));
    DocumentListController.$inject = [
        '$scope',
        '$location',
        '$window',
        '$modal',
        'DocumentListService',
        '$filter'
    ];
    DocumentList.DocumentListController = DocumentListController;
    angular.module("DocumentList").controller("DocumentListController", DocumentListController);
})(DocumentList || (DocumentList = {}));
//# sourceMappingURL=DocumentListController.js.map