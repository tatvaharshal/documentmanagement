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
            debugger;
            /*var fd = new FormData();
             fd.append("file", file, "lolz");*/
            /*this._http.post(uploadUrl, fd ).success(function () { })
             .error(function (data) { });*/
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                }
            };
            xhr.open("POST", uploadUrl, true);
            var formData = new FormData();
            formData.append("file", file, "file");
            xhr.send(formData);
        };
        return FileUploadService;
    }());
    FileUploadService.$inject = ["$http"];
    DocumentList.FileUploadService = FileUploadService;
    angular.module("DocumentList").service("fileUploadService", FileUploadService);
})(DocumentList || (DocumentList = {}));
//# sourceMappingURL=FileUploadService.js.map