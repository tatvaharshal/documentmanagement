/// <reference path='../../_allDocumentList.ts' />
module DocumentList {
    export interface IDocumentListService {
        GetDocumentByID:(id:number)=> ng.IPromise<any>;
        GetDocumentList: ($scope: IDocumentListScope) => ng.IPromise<Array<any>>;
        SaveDocument:($scope: IDocumentListScope, docMaster : MasterDocumentDTO)=> ng.IPromise<string>;
        UpdateDocument:($scope: IDocumentListScope, docMaster : MasterDocumentDTO)=> ng.IPromise<string>;
    }
}