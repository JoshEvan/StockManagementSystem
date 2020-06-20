import { HTTPCallStatus } from ".";

export interface ICRUDResponse{
	status: HTTPCallStatus,
    data:string[]
}