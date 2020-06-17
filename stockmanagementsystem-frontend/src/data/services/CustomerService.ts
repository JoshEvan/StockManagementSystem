import { getBaseUrl, JOSEPH_URL } from "../../configs/api";
import { Observable, from } from "rxjs";
import { IIndexCustomerResponse,IUpsertCustomerRequest,IUpsertCustomerResponse } from "../interfaces";
import Axios from "axios-observable";

const usingBaseUrl = getBaseUrl()

export const serviceIndexCustomer = (): Observable<IIndexCustomerResponse> => {
    // from method rxjs convert Promise to Observable (Axios.get return a Promise)
    return Axios.get(
        usingBaseUrl+JOSEPH_URL.CUSTOMER.INDEX
    )
}

export const serviceAddCustomer = (dataPayload:IUpsertCustomerRequest): Observable<any> => {
    return Axios.post(
        usingBaseUrl+JOSEPH_URL.CUSTOMER.ADD,
        dataPayload
    )
}

export const serviceEditCustomer = (dataPayload:IUpsertCustomerRequest): Observable<any> => {
    return Axios.put(
        usingBaseUrl+JOSEPH_URL.CUSTOMER.EDIT,
        dataPayload 
    )
}

export const serviceDeleteCustomer = (dataPayload:string): Observable<any> => {
    return Axios.delete(
        usingBaseUrl+JOSEPH_URL.CUSTOMER.DELETE+dataPayload
    )
}