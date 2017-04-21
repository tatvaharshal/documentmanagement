/// <reference path='../../_all.ts' />

module Demo {
    export interface ICOCOScope extends ng.IScope {
    	userMasterList: Array<UserMasterChirag>;
        user : UserMasterChirag;
        vm: any;
    	myFile : any;
    	hobbies : string[];	
    	isImageRequired: boolean;
    	status:any;
    	
    	
    	// PIE & BAR Charts:
    	barChartDataChirag : any;
        barChartLabelChirag : any;
        barChartSeriesChirag : any;
        barChartOptionChirag : any;
        
        pieChartDataChirag : any;
        pieChartLabelChirag : any;
        pieChartOptionChirag : any;
    	
    	 //====================================
        sortType: string; // set the default sort type
        sortReverse: boolean;  // set the default sort order
        
        viewby: number;
        totalItems: number;
        currentPage: number;
        itemsPerPage: number;
        maxSize: number; //Number of pager buttons to show
    }
}
