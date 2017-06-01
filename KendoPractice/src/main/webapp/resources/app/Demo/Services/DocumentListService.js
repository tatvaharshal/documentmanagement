/// <reference path='../../_allDocumentList.ts' />
var DocumentList;
(function (DocumentList) {
    'use strict';
    var DocumentListService = (function () {
        function DocumentListService(http, $q) {
            //define Succses And fail
            this.success = function (response) { return response.data; };
            this.fail = function (error) { return error.data; };
            this._http = http;
            this.$q = $q;
        }
        //Getting The list
        DocumentListService.prototype.GetDocumentList = function ($scope) {
            debugger;
            return this._http.get('./documentList')
                .then(this.success)["catch"](this.fail);
        };
        //Get Document By Id
        DocumentListService.prototype.GetDocumentByID = function (id) {
            debugger;
            return this._http.get('./documentListByID/' + id)
                .then(this.success)["catch"](this.fail);
        };
        //Saving The document
        DocumentListService.prototype.SaveDocument = function ($scope, documentMaster) {
            debugger;
            return this._http.post("./DocumentAdd/", documentMaster)
                .then(this.success)["catch"](this.fail);
        };
        //Update The Document
        DocumentListService.prototype.UpdateDocument = function ($scope, documentMaster) {
            debugger;
            return this._http.post("./updateDocument/", documentMaster)
                .then(this.success)["catch"](this.fail);
        };
        return DocumentListService;
    }());
    DocumentListService.$inject = ["$http", "$q"];
    DocumentList.DocumentListService = DocumentListService;
    angular.module("DocumentList").service("DocumentListService", DocumentListService);
})(DocumentList || (DocumentList = {}));
//# sourceMappingURL=DocumentListService.js.map