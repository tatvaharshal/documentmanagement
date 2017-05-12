/// <reference path='../../_allDocumentList.ts' />
module DocumentList
{
    export interface IDocumentListScope extends ng.IScope
    {
        mainGridOptions :any;
        vm: any;
        doc:MasterDocumentDTO;
        list:Multivalue;
       myFile:any;
        documentTags:string[];
    }

}