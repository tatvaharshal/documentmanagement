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
/**
 * Created by pca43 on 11-May-17.
 */
/// <reference path='../../_allDocumentList.ts' />
var DocumentList;
(function (DocumentList) {
    //  import BaseDocumentListController = DocumentList.BaseDocumentListController;
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
            _this.isUploaded = false;
            //uloadPDF Validation
            _this.validateFiles = function (file) {
                debugger;
                if (file.size > 5242880) {
                    alert("File Size Should Not bE greter then 5 MB");
                }
                else {
                    //var file = this.$scope.file;
                    if (file != null || file != undefined) {
                        this.fileUploadService.uploadFile(file, "./fileUpload");
                        //Step-2 fill up dto with other details
                    }
                }
            };
            $scope.vm = _this;
            _this.DocumentListService.GetDocumentList(_this.$scope).then(function (data) {
                debugger;
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
                            field: "documentName",
                            title: "Document name"
                        }, {
                            field: "creationDate",
                            title: "Creation date",
                            template: function (dataitem) {
                                var d = new Date(dataitem.creationDate);
                                var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                                return formattedDate;
                            }
                        }, {
                            field: "importDate",
                            title: "import Date",
                            template: function (dataitem) {
                                var d = new Date(dataitem.importDate);
                                var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                                return formattedDate;
                            }
                        }, {
                            field: "fileSize",
                            title: "File size(KB)"
                        }, {
                            field: "documentDescription",
                            title: "description"
                        },
                        {
                            field: "documentStatus",
                            title: "Document status"
                        },
                        {
                            field: "documentType",
                            title: "Document Type"
                        },
                        {
                            field: "addressScope",
                            title: "Addres scope"
                        }, {
                            field: "verticalData",
                            title: "Vertical Id"
                        }, {
                            field: "validFrom",
                            title: "Valid from",
                            template: function (dataitem) {
                                var d = new Date(dataitem.validFrom);
                                var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                                return formattedDate;
                            }
                        }, {
                            field: "validTo",
                            title: "Valid to",
                            template: function (dataitem) {
                                var d = new Date(dataitem.validTo);
                                var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                                return formattedDate;
                            }
                        },
                        {
                            field: "documentTag",
                            title: "Document tag"
                        },
                        {
                            title: "Edit",
                            template: '<a href="./DocumentDetail?DocumentId=#: documentId #">Edit</a>'
                        }
                    ]
                };
            });
            return _this;
        }
        //Methods
        //convert date
        DocumentListController.prototype.convertDate = function (timestamp) {
            var d = new Date(timestamp);
            var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
            return formattedDate;
        };
        // Init
        DocumentListController.prototype.initialiseEditPage = function (id, $scope) {
            var _this = this;
            debugger;
            this.DocumentListService.GetDocumentByID(id).then(function (data) {
                debugger;
                _this.doc = data;
                _this.$scope.doc = data;
                //this.$scope.list.documentTags = this.doc.documentTag.split(",");
            });
        };
        //SaveDocument
        DocumentListController.prototype.saveDocument = function () {
            debugger;
            this.doc = this.$scope.doc;
            this.list = this.$scope.list;
            if (this.list != undefined) {
                this.doc.documentTag = this.$scope.list.documentTags.join(", ");
            }
            /*  var file = this.$scope.myFile;
              if (file != null || file != undefined) {
                  this.fileUploadService.uploadFile(file, "./fileUpload");
              }*/
            /* this.DocumentListService.SaveDocument(this.$scope, this.doc).then((data) =>{
                 this.doc = data;
                 alert(this.doc.documentId);
                 if(data.success == 'success'){
                     alert("file uploadede succsesfully");
                     // this.$window.location.href="/userChirag/userList#/?status=save";
                 } else{alert("fail");}
             }).catch(err =>{
                 if(err == 409){
                     alert("erro ocured");
                     // this.$window.location.href="/userChirag/userList#/?status=conflict";
                 }
             });*/
            /*  var file = this.$scope.myFile;
              if (file != null || file != undefined) {
                  this.fileUploadService.uploadFile(file, "./fileUpload");
              }*/
        };
        //updateDocument
        DocumentListController.prototype.updateDocument = function () {
            debugger;
            alert("in save docmethod");
            this.doc = this.$scope.doc;
            this.list = this.$scope.list;
            if (this.list != undefined) {
                this.doc.documentTag = this.$scope.list.documentTags.join(", ");
            }
            this.DocumentListService.UpdateDocument(this.$scope, this.doc).then(function (data) {
                debugger;
                if (data == 'success') {
                    alert("file uploadede succsesfully");
                    // this.$window.location.href="/userChirag/userList#/?status=save";
                }
                else {
                    alert("fail");
                }
            })["catch"](function (err) {
                if (err == 409) {
                    alert("erro ocured");
                    // this.$window.location.href="/userChirag/userList#/?status=conflict";
                }
            });
        };
        //onDelete
        DocumentListController.prototype.onDelete = function (id, name) {
            var _this = this;
            var options = {
                template: '<div class="modal-header"><h3 class="modal-title">Delete Document</h3></div><div class="modal-body">The Document With Name ' + name + ' will be deleted.Are you sure you want to continue?</div><div class="modal-footer"><button class="btn btn-primary" type="button" ng-click="Dctrl.save(' + id + ')">Ok</button><button class="btn btn-default" type="button" ng-click="Dctrl.cancel()">Cancel</button></div>',
                controller: 'DocumentPopupController as Dctrl',
                windowClass: 'app-modal-window',
                resolve: {}
            };
            this.$modal.open(options).result
                .then(function (updatedItem) { return _this.onConfirm(updatedItem); });
        };
        DocumentListController.prototype.onConfirm = function (item) { };
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