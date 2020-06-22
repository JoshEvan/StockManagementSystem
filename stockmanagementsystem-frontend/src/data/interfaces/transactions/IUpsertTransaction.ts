export interface IUpsertTransactionRequest{
    id:string,
    customerId:string,
    paymentId:string,
    transactionDate:string,
    paymentStatus:string,
    note:string,
    transactionDetails:IUpsertTransactionDetailRequest[]
}

export interface IUpsertTransactionDetailRequest{
    itemCode:string,
    quantity:number,
    note:string
}