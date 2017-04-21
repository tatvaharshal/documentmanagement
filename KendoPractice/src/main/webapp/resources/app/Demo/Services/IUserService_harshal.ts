/// <reference path='../../_all_harshal.ts' />

module HarshalDemo {
    export interface IUserService_harshal {
    	getUserList_harshal : ($scope: ICOCOScope_harshal) => ng.IPromise<Array<UserMaster_harshal>>;
    	
    	saveUser_harshal : ($scope: ICOCOScope_harshal, user : UserMaster_harshal)  => ng.IPromise<string>;
    	
    	deleteUser_harshal : ($scope: ICOCOScope_harshal, userId : any)  => ng.IPromise<string>;
    	
    	getUserDetailById_harshal : ($scope: ICOCOScope_harshal, userId : any)  => ng.IPromise<UserMaster_harshal>;
    	
    	updateUser_harshal : ($scope: ICOCOScope_harshal, user : UserMaster_harshal)  => ng.IPromise<string>;
        
        getUserCountPerCity_harshal : ($scope: ICOCOScope_harshal) => ng.IPromise<Array<string>>;
    }
}