/// <reference path='../../_allDocumentList.ts' />
module DocumentList {
    'use strict';
    export class DocumentLinkDTO {
        public documentLinkId:Number;

        public userId:string;

        public groupDetails: string[];
        public groupDetail: string;

        public roleDetails: string[];
        public roleDetail: string;
        constructor() { }
    }
}
