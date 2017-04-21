/// <reference path='../../_allChandni.ts' />

module DemoChandni {
    export interface IUserChandniService {
        GetUserList: ($scope: ICOCOChandniScope) => ng.IPromise<Array<any>>;
        deleteUser: ($scope: ICOCOChandniScope, userId: any) => ng.IHttpPromise<string>;
        addUpdateUser: ($scope: ICOCOChandniScope, user: any) => ng.IHttpPromise<string>;
        FindUserByEmail: ($scope: ICOCOChandniScope, email: string) => ng.IHttpPromise<boolean>;
        GetUsersByCity: ($scope: ICOCOChandniScope) => ng.IHttpPromise<UserChandni>;
    }
}