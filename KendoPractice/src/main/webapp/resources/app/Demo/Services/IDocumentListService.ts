/// <reference path='../../_allDocumentList.ts' />
module DocumentList {
    export interface IDocumentListService {
        GetDocumentList: ($scope: IDocumentListScope) => ng.IPromise<Array<any>>;
        GetDocumentByID:(id:number)=> ng.IPromise<any>;
        SaveDocument:($scope: IDocumentListScope, docMaster : MasterDocumentDTO)=> ng.IPromise<string>;
        UpdateDocument:($scope: IDocumentListScope, docMaster : MasterDocumentDTO)=> ng.IPromise<string>;
    }
}