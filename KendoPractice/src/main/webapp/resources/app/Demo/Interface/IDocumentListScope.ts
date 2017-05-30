/// <reference path='../../_allDocumentList.ts' />
module DocumentList
{
    export interface IDocumentListScope extends ng.IScope
    {
        mainGridOptions :any;
        vm: any;
        doc:MasterDocumentDTO;
        docLink:DocumentLinkDTO;
        list:Multivalue;
        file:any;
        documentTags:string[];
        flag:boolean;
        creationDate:string;
        validFrom:string;
        validTo:string;
    }

}