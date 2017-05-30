/**
 * Created by pca43 on 09-May-17.
 */
/// <reference path='../../_allDocumentList.ts' />
var DocumentList;
(function (DocumentList) {
    'use strict';
    var FileUploadService = (function () {
        function FileUploadService(http, $q) {
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
        FileUploadService.prototype.uploadFile = function (file, uploadUrl) {
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    var test = xhr.response;
                    deferred.resolve(test);
                }
            };
            xhr.open("POST", uploadUrl, true);
            var formData = new FormData();
            formData.append("file", file);
            xhr.send(formData);
            var deferred = this.$q.defer();
            return deferred.promise;
        };
        return FileUploadService;
    }());
    FileUploadService.$inject = ["$http", "$q"];
    DocumentList.FileUploadService = FileUploadService;
    angular.module("DocumentList").service("fileUploadService", FileUploadService);
})(DocumentList || (DocumentList = {}));
//# sourceMappingURL=FileUploadService.js.map