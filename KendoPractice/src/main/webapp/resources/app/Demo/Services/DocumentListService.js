/// <reference path='../../_allDocumentList.ts' />
var DocumentList;
(function (DocumentList) {
    'use strict';
    var DocumentListService = (function () {
        function DocumentListService(http, $q) {
            var _this = this;
            //define Succses And fail
            this.success = function (response) { return response.data; };
            this.fail = function (error) {
                var msg = error.data.message;
                var reason = 'query for people failed.';
                return _this.$q.reject(msg);
            };
            this._http = http;
            this.$q = $q;
        }
        DocumentListService.prototype.GetUserList = function ($scope) {
            return this._http.get('/chandni/chandniGetUserList')
                .then(this.success)["catch"](this.fail);
        };
        DocumentListService.prototype.SaveDocument = function ($scope, documentMaster) {
            debugger;
            return this._http.post("/saveDocumentVimal/", documentMaster)
                .then(this.success)["catch"](this.fail);
        };
        return DocumentListService;
    }());
    DocumentListService.$inject = ["$http", "$q"];
    DocumentList.DocumentListService = DocumentListService;
    angular.module("DocumentList").service("DocumentListService", DocumentListService);
})(DocumentList || (DocumentList = {}));
//# sourceMappingURL=DocumentListService.js.map