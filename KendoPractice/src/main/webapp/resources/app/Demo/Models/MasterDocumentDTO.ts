/// <reference path='../../_allDocumentList.ts' />
module DocumentList {
    'use strict';
    export class MasterDocumentDTO {
        public masterDocumentId:Number;
        public documentId:Number;
        public documentName: string;
        public documentDescription: string;
        public filePath: Array<any>;
        public fileSize: Number;
        public creationDate:any ;
        public validFrom: any;
        public validTo: any;
        public verticalData: string;
        public documentStatus: any;
        public documentType: any;
        public documentTag: any;
        public documentLinkId: Number;
       /* public userId: string;
        public groupDetails:string;
        public roleDetails: string;*/
        public addressScope: any;
        public importDate: any;
        public documentLinkDTO:string ;
        constructor() { }
    }
}