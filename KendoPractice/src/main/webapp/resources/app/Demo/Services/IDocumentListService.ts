/// <reference path='../../_allDocumentList.ts' />
module DocumentList {
    export interface IDocumentListService {
        GetUserList: ($scope: IDocumentListScope) => ng.IPromise<Array<any>>;
        SaveDocument:($scope: IDocumentListScope, docMaster : MasterDocumentDTO)=> ng.IPromise<string>;
        UploadDocument :($scope: IDocumentListScope, docMaster : FileListModel)=> ng.IPromise<string>;
    }
}