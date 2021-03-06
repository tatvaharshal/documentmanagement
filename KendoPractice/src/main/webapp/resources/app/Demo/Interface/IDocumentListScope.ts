/// <reference path='../../_allDocumentList.ts' />
module DocumentList
{
    export interface IDocumentListScope extends ng.IScope
    {
        mainGridOptions :any;
        vm: any;
        doc : MasterDocumentDTO;
        docLink:DocumentLinkDTO;
        docList: Array<MasterDocumentDTO>;
        docLinkList:Array<DocumentLinkDTO>;
        list:Multivalue;
        file:any;
        isImageRequired: boolean;
        documentTag:string[];
        groupDetails:string[];
        roleDetails:string[];
        flag:boolean;
        addressScope:any;
        status:any;
        scopeOption:any;
        groupOption:any;
        roleOption:any;
        docTagOption:any;
        docTypeOption:any;
        group:any;
        role:any;
        docTag:any;
        user:any;
        scopeOptionEdit:any;
        success_message:boolean;
        failure_message:boolean;
        statusMessage:string;

    }

}