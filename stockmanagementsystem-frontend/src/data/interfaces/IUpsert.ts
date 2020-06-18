import { HTTPCallStatus } from ".";

export interface IUpsertResponse{
	status: HTTPCallStatus,
    data:string[]
}