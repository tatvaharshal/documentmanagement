/// <reference path='../../_allUrvashi.ts' />
module DemoUrvashi {
    export interface IUserServiceUrvashi {
    	saveUser : (user: UserUrvashi)  => ng.IPromise<string>;
    	listUser : ()  => ng.IPromise<Array<UserUrvashi>>;
    	deleteUser : (id:any) => ng.IPromise<string>;
    	updateUser : (user:UserUrvashi)  => ng.IPromise<string>;
    	getUserById : (id:number)=>ng.IPromise<UserUrvashi>;
    }
}