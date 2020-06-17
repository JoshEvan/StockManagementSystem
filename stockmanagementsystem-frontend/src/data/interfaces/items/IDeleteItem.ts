import { HTTPCallStatus } from "..";

export interface IDeleteItemResponse{
	status: HTTPCallStatus,
    data:string[]
}