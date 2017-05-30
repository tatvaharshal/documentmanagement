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
            };
            $scope.vm = _this;
            _this.DocumentListService.GetDocumentList(_this.$scope).then(function (data) {
                $scope.mainGridOptions = {
                    dataSource: {
                        type: "json",
                        data: data,
                        pageSize: 5
                    },
                    columnMenu: true,
                    filterable: {
                        extra: false,
                        operators: {
                            string: {
                                contains: "Contains",
                                isempty: "Is empty"
                            }
                        }
                    },
                    groupable: true,
                    sortable: true,
                    pageable: true,
                    selectable: "raw",
                    columns: [{
                            field: "documentName",
                            title: "Document name"
                        }, {
                            field: "creationDate",
                            title: "Creation date",
                            filterable: {
                                ui: "datetimepicker",
                                extra: false,
                                operators: {
                                    string: {
                                        gte: "is After or equel to",
                                        lte: "is Before or equel to"
                                    }
                                }
                            },
                            template: function (dataitem) {
                                var d = new Date(dataitem.creationDate);
                                var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                                return formattedDate;
                            }
                        }, {
                            field: "importDate",
                            title: "import Date",
                            filterable: {
                                ui: "datetimepicker",
                                extra: false,
                                operators: {
                                    string: {
                                        gte: "is After or equel to",
                                        lte: "is Before or equel to"
                                    }
                                }
                            },
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
                            filterable: {
                                multi: true
                            },
                            title: "Document Type"
                        },
                        {
                            field: "addressScope",
                            filterable: {
                                multi: true
                            },
                            title: "Addres scope"
                        }, {
                            field: "verticalData",
                            title: "Vertical Id"
                        }, {
                            field: "validFrom",
                            title: "Valid from",
                            filterable: {
                                ui: "datetimepicker",
                                extra: false,
                                operators: {
                                    string: {
                                        gte: "is After or equel to",
                                        lte: "is Before or equel to"
                                    }
                                }
                            },
                            template: function (dataitem) {
                                var d = new Date(dataitem.validFrom);
                                var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                                return formattedDate;
                            }
                        }, {
                            field: "validTo",
                            title: "Valid to",
                            filterable: {
                                ui: "datetimepicker",
                                extra: false,
                                operators: {
                                    string: {
                                        gte: "is After or equel to",
                                        lte: "is Before or equel to"
                                    }
                                }
                            },
                            template: function (dataitem) {
                                var d = new Date(dataitem.validTo);
                                var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
                                return formattedDate;
                            }
                        },
                        {
                            field: "documentTag",
                            title: "Document tag"
                        }
                    ]
                };
            });
            return _this;
        }
        //Methods
        // Init
        DocumentListController.prototype.initialiseEditPage = function (id, $scope) {
            var _this = this;
            debugger;
            this.DocumentListService.GetDocumentByID(id).then(function (data) {
                debugger;
                _this.doc = data;
                _this.$scope.doc = data;
                _this.$scope.creationDate = _this.convertDate(_this.doc.creationDate);
                _this.$scope.validFrom = _this.convertDate(_this.doc.validFrom);
                _this.$scope.validTo = _this.convertDate(_this.doc.validTo);
                var temp = _this.$scope.doc.documentTag.split(",");
                _this.$scope.documentTags = _this.$scope.doc.documentTag.split(",");
                if (_this.doc.addressScope == "None") {
                    _this.$scope.flag = false;
                }
                else {
                    _this.$scope.flag = true;
                }
            });
        };
        //SaveDocument
        DocumentListController.prototype.saveDocument = function () {
            var _this = this;
            debugger;
            var file = this.$scope.file;
            if (file != null || file != undefined) {
                this.fileUploadService.uploadFile(file, "./fileUpload").then(function (data) {
                    debugger;
                    var fileData = JSON.parse(data);
                    _this.doc = _this.$scope.doc;
                    _this.docLink = _this.$scope.docLink;
                    _this.doc.documentId = fileData.documentId;
                    _this.doc.filePath = fileData.filePath;
                    _this.doc.fileSize = fileData.fileSize;
                    if (_this.$scope.doc.addressScope == "UserId") {
                        _this.docLink.userId = _this.$scope.docLink.userId;
                        var objToString = JSON.stringify(_this.docLink);
                        // var final ="["+objToString+"]";
                        _this.doc.documentLinkDTO = JSON.parse(objToString);
                    }
                    if (_this.$scope.doc.addressScope == "Group") {
                        _this.docLink.groupDetail = _this.$scope.docLink.groupDetails.join(",");
                        var something = _this.docLink.groupDetail;
                        var formated = "{" + '"groupDetails"' + ":" + '"' + something + '"' + "}";
                        //   var final ="["+formated+"]";
                        _this.doc.documentLinkDTO = JSON.parse(formated);
                    }
                    if (_this.$scope.doc.addressScope == "Role") {
                        _this.docLink.roleDetail = _this.$scope.docLink.roleDetails.join(",");
                        var something = _this.docLink.roleDetail;
                        var formated = "{" + '"roleDetails"' + ":" + '"' + something + '"' + "}";
                        //  var final ="["+formated+"]";
                        _this.doc.documentLinkDTO = JSON.parse(formated);
                    }
                    if (_this.$scope.list != undefined) {
                        _this.doc.documentTag = _this.$scope.list.documentTags.join(",");
                    }
                    _this.DocumentListService.SaveDocument(_this.$scope, _this.doc).then(function (response) {
                        debugger;
                        alert(response.status);
                        if (data != undefined) {
                            alert("Data Submitted Succsesfully!");
                            _this.$window.location.href = "./DocumentList";
                        }
                        else {
                            alert("Somwthing Went Wrong");
                        }
                    })["catch"](function (err) {
                        debugger;
                        alert("error: " + err);
                    });
                });
            }
            else {
                alert("File is undefined");
            }
        };
        //updateDocument
        DocumentListController.prototype.updateDocument = function () {
            var _this = this;
            debugger;
            alert("in updaet");
            this.doc = this.$scope.doc;
            this.list = this.$scope.list;
            this.docLink = this.$scope.docLink;
            this.documentTags = this.$scope.documentTags;
            var creationDate = new Date(this.$scope.creationDate);
            var validFrom = new Date(this.$scope.validFrom);
            var validTo = new Date(this.$scope.validTo);
            this.doc.creationDate = creationDate;
            this.doc.validFrom = validFrom;
            this.doc.validTo = validTo;
            if (this.$scope.doc.addressScope == "UserId" && this.$scope.docLink != undefined) {
                this.docLink.userId = this.$scope.docLink.userId;
                var objToString = JSON.stringify(this.docLink);
                this.doc.documentLinkDTO = JSON.parse(objToString);
            }
            if (this.$scope.doc.addressScope == "Group" && this.$scope.docLink != undefined) {
                this.docLink.groupDetail = this.$scope.docLink.groupDetails.join(",");
                var something = this.docLink.groupDetail;
                var formated = "{" + '"groupDetails"' + ":" + '"' + something + '"' + "}";
                this.doc.documentLinkDTO = JSON.parse(formated);
            }
            if (this.$scope.doc.addressScope == "Role" && this.$scope.docLink != undefined) {
                this.docLink.roleDetail = this.$scope.docLink.roleDetails.join(",");
                var something = this.docLink.roleDetail;
                var formated = "{" + '"roleDetails"' + ":" + '"' + something + '"' + "}";
                this.doc.documentLinkDTO = JSON.parse(formated);
            }
            if (this.documentTags != undefined) {
                this.doc.documentTag = this.$scope.documentTags.join(",");
            }
            this.DocumentListService.UpdateDocument(this.$scope, this.doc).then(function (data) {
                debugger;
                if (data != undefined) {
                    alert("Changes updated succesesfully");
                    _this.$window.location.href = "./DocumentList";
                }
                else {
                    alert("Something went wrong");
                }
            })["catch"](function (err) {
                debugger;
                alert("erro ocured : " + err);
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
        //convert date
        DocumentListController.prototype.convertDate = function (timestamp) {
            var d = new Date(timestamp);
            var formattedDate = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
            return formattedDate;
        };
        //handle Click Event on grid raw
        DocumentListController.prototype.onRawClick = function (num) {
            var grid = $("#kGrid").data().kendoGrid;
            var selectedRow = grid.select();
            var selectedDataItem = grid.dataItem(selectedRow);
            var docId = selectedDataItem.documentId;
            this.$window.location.href = "DocumentDetail?DocumentId=" + docId + "";
        };
        return DocumentListController;
    }(DocumentList.BaseDocumentListController));
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