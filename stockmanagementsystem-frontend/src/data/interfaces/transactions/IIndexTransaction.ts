export interface IIndexTransactionRequest{
    customerFilter: string[],
    itemFilter:string[],
    paymentFilter: string[],
    dateFilter:string,
    endDateFilter:string,
    sortByDate:number,
    sortByTotal:number
}

export const getInitIndexTransactionRequest = () : IIndexTransactionRequest => ({
	customerFilter: [],
    itemFilter:[],
    paymentFilter: [],
    dateFilter:"",
    endDateFilter:"",
    sortByDate:0,
    sortByTotal:0
})
