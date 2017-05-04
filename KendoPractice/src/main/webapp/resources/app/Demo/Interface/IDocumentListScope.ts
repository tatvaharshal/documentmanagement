/// <reference path='../../_allDocumentList.ts' />
module DocumentList
{
    export interface IDocumentListScope extends ng.IScope
    {
        userChandni: DocumentListModel;
        userChandniList: DocumentListModel[];
        mainGridOptions :any;
        vm: any;
        doc:any;
    }

}