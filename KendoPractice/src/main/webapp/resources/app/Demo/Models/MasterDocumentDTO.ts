/// <reference path='../../_allDocumentList.ts' />

module DocumentList {
    'use strict';
    export class MasterDocumentDTO {
        public masterDocumentId:Number;
        public documentId:Number;
        public documentName: string;
        public documentDescription: string;
        public filePath: Array<Any>;
        public fileSize: Number;
        public creationDate:Date ;
        public validFrom: Date;
        public validTo: Date;
        public verticalData: string;
        public documentStatus: string;
        public documentType: string;
        public documentTag: string;
        public documentLinkId: Number;
        public userId: Number;
        public groupDetails:string;
        public roleDetails: string;
        public addressScope: string;
        public importDate: Date;
        constructor() { }
    }
}