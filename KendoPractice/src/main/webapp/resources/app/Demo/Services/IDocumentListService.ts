/// <reference path='../../_allDocumentList.ts' />
module DocumentList {
    export interface IDocumentListService {
        GetUserList: ($scope: IDocumentListScope) => ng.IPromise<Array<any>>;
        SaveDocument:($scope: IDocumentListScope, docMaster : DocumentListModel)=> ng.IPromise<string>;
    }
}