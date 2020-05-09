import { IItem } from "./IItem";

export interface IIndexItemRequest{
    sortByItemCode:number,
    sortByAmountIncome:number,
    sortByAmountSold:number
}

export interface IIndexItemResponse{
    data:{items:IItem[]}
}