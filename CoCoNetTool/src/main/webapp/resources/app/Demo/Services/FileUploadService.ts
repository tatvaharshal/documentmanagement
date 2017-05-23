/**
 * Created by pca43 on 09-May-17.
 */
/// <reference path='../../_allDocumentList.ts' />
module DocumentList	 {
    'use strict';
    export class FileUploadService implements IFileUploadService {
        private _http: ng.IHttpService;
        static $inject = ["$http"];
        constructor(http: ng.IHttpService) {
            this._http = http;
        }
        public uploadFile(file: any, uploadUrl: any) {
            debugger;
            /*var fd = new FormData();
             fd.append("file", file, "lolz");*/
            /*this._http.post(uploadUrl, fd ).success(function () { })
             .error(function (data) { });*/

            let xhr: XMLHttpRequest = new XMLHttpRequest();
            xhr.onreadystatechange = () => {
                if (xhr.readyState === 4) {

                }
            };

            xhr.open("POST", uploadUrl, true);
            let formData = new FormData();
            formData.append("file", file, "file");
            xhr.send(formData);
        }
    }
    angular.module("DocumentList").service("fileUploadService", FileUploadService);
}