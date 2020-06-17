import { HTTPCallStatus } from "..";

export interface IUpsertCustomerRequest{
    id:string,
	name:string,
	description:string,
	contact:string
}

export interface IUpsertCustomerResponse{
	status: HTTPCallStatus,
    data:string[]
}