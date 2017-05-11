/**
 * Created by pca43 on 09-May-17.
 */
/// <reference path='../../_allDocumentList.ts' />
var DocumentList;
(function (DocumentList) {
    'use strict';
    var FileUploadService = (function () {
        function FileUploadService(http) {
            this._http = http;
        }
        FileUploadService.prototype.uploadFile = function (file, uploadUrl) {
            var fd = new FormData();
            fd.append('file', file);
            alert("fd " + uploadUrl);
            this._http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: { 'Content-Type': undefined }
            }).success(function () { })
                .error(function (data) { });
        };
        return FileUploadService;
    }());
    FileUploadService.$inject = ["$http"];
    DocumentList.FileUploadService = FileUploadService;
    angular.module("DocumentList").service("fileUploadService", FileUploadService);
})(DocumentList || (DocumentList = {}));
//# sourceMappingURL=FileUploadService.js.map