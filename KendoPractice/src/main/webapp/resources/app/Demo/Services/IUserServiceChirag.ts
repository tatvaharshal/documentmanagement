/// <reference path='../../_all.ts' />

module Demo {
    export interface IUserServiceChirag {
    	
    	GetUserList:()=> ng.IPromise<Array<UserMasterChirag>>;
    	
    	GetEditPage:(id:number)=> ng.IPromise<any>;
    	GetUserByID:(id:number)=> ng.IPromise<any>;
    	SaveUser:($scope: ICOCOScope, userMaster : UserMasterChirag)=> ng.IPromise<string>;
    	
    	UpdateUser:($scope: ICOCOScope, userMaster : UserMasterChirag)=> ng.IPromise<string>;
    	
    	DeleteUser:($scope: ICOCOScope, id : number)=> ng.IPromise<string>;
    	
    	getCharts:($scope: ICOCOScope)=> ng.IPromise<string[]>
    }
}