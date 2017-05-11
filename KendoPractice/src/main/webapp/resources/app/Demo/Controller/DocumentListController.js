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
        function DocumentListController($scope, $location, $window, $modal, DocumentListService, $filter, fileUploadService) {
            var _this = _super.call(this, $scope) || this;
            _this.$scope = $scope;
            _this.$location = $location;
            _this.$window = $window;
            _this.$modal = $modal;
            _this.DocumentListService = DocumentListService;
            _this.$filter = $filter;
            _this.fileUploadService = fileUploadService;
            //uloadPDF
            // uploadFiles = function (file) {
            //      debugger;
            //      if(file.size>5242880)
            //      {
            //          alert("File Size Should Not bE greter then 5 MB")
            //      }
            //      else {
            //          alert("File size: "+file.size + "Bit");
            //          this.file = file;
            //          alert(file);
            //          this.DocumentListService.UploadDocument(this.$scope, this.file).then((data) =>{
            //              debugger;
            //              if(data == 'success'){
            //              }
            //          }).catch(err =>{
            //              if(err == 409){
            //              }
            //          });
            //      }
            //  }
            // saveDocument(){
            //     debugger;
            //     this.doc = this.$scope.doc;
            //
            //     this.DocumentListService.SaveDocument(this.$scope, this.doc).then((data) =>{
            //         if(data.success == 'success'){
            //             alert("Succses");
            //             //this.$window.location.href="/userChirag/userList#/?status=save";
            //         }
            //     }).catch(err =>{
            //         alert("Fail");
            //         if(err == 409){
            //             //this.$window.location.href="/userChirag/userList#/?status=conflict";
            //         }
            //     });
            // }
            //uloadPDF
            _this.uploadFiles = function (file) {
                debugger;
                if (file.size > 5242880) {
                    alert("File Size Should Not bE greter then 5 MB");
                }
                else {
                    if (file != null || file != undefined) {
                        // var name = file.name;
                        // var uploadUrl = "./fileUpload";
                        // file.filePath = file;
                        // file.fileSize = file.size;
                        // file.documentName = file.name;
                        // file.documentDescription = file.type;
                        this.fileUploadService.uploadFile(file, "./fileUpload");
                    }
                }
            };
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
                            field: "documentData.documentName",
                            title: "Document name"
                        }, {
                            field: "documentData.creationDate",
                            title: "Creation date"
                        }, {
                            field: "documentData.validFrom",
                            title: "Valid From"
                        }, {
                            field: "documentData.fileSize",
                            title: "File size(KB)"
                        }, {
                            field: "documentData.documentDescription",
                            title: "description"
                        },
                        {
                            field: "documentData.documentStatus",
                            title: "Document status"
                        },
                        {
                            field: "documentData.documentType",
                            title: "Document Type"
                        },
                        {
                            field: "documentData.addressScope",
                            title: "Addres scope"
                        }, {
                            field: "documentData.verticalData",
                            title: "Vertical Id"
                        }, {
                            field: "documentData.validFrom",
                            title: "Valid from"
                        }, {
                            field: "documentData.validTo",
                            title: "Valid to"
                        },
                        {
                            field: "documentData.documentTag",
                            title: "Document tag"
                        },
                        {
                            field: "documentData.importDate",
                            title: "import Date"
                        }
                    ]
                };
            });
            return _this;
            // $scope.vm.uploadFiles = function (file, errFiles) {
            //     alert("File size: "+file.size + "Bit");
            // }
        }
        // saving Document
        DocumentListController.prototype.saveDocument = function () {
            debugger;
            this.doc = this.$scope.doc;
            this.DocumentListService.SaveDocument(this.$scope, this.doc).then(function (data) {
                debugger;
                if (data == 'CREATED') {
                    alert("file uploadede succsesfully");
                }
                else {
                    alert("fail");
                }
            })["catch"](function (err) {
                if (err == 409) {
                    alert("erro ocured");
                }
            });
        };
        return DocumentListController;
    }(BaseDocumentListController));
    DocumentListController.$inject = [
        '$scope',
        '$location',
        '$window',
        '$modal',
        'DocumentListService',
        '$filter',
        'fileUploadService'
    ];
    DocumentList.DocumentListController = DocumentListController;
    angular.module("DocumentList").controller("DocumentListController", DocumentListController);
})(DocumentList || (DocumentList = {}));
//# sourceMappingURL=DocumentListController.js.map