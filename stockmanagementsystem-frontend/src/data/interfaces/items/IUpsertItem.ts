import { HTTPCallStatus } from "..";

export interface IUpsertItemRequest{
    itemCode:string,
	name:string,
	description:string,
	price:number,
	stock:number,
	capacity:number
}

export interface IUpsertItemResponse{
	status: HTTPCallStatus,
    data:string[]
}