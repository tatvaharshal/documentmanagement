/// <reference path='../../_allDocumentList.ts' />

module DocumentList {
    'use strict';

    export class DocumentListModel {
        public userId: any;
        public userFullName: any;
        public userFirstName: any;
        public userLastName: any;
        public userPassword: any;
        public userGender: any;
        public userEmail: any;
        public userDob: any;
        public userDateofbirth: any;
        public userPhoneNumber: any;
        public userHobbies: any;
        public userHobbies0: any;
        public userHobbies1: any;
        public userHobbies2: any;
        public userHobbies3: any;
        public userCity: any;
        public userAddress: any;
        public userImage: any;

        //============
        public docAddressScope: string;
        public docType: string;
        public docUser: string;
        public docUserId: number;
        public docGroup: Array<any>;
        public docRole: string;
        public docStatus: string;
        public docCreationDate: Date;
        public docValidFromDate: Date;
        public docValidToDate: Date;
        public docDescription: string;
        public docTags: Array<any>;
        public docName: string;
        public Isvalid: boolean = false;

        constructor(
            ) { }
    }
}