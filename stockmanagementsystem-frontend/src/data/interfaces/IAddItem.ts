import { HTTPCallStatus } from ".";

export interface IAddItemRequest{
    itemCode:string,
	name:string,
	description:string,
	price:number,
	stock:number,
	capacity:number
}

export interface IAddItemResponse{
	status: HTTPCallStatus,
    data:string[]
}