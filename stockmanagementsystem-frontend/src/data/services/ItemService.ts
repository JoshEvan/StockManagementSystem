import { getBaseUrl, JOSEPH_URL } from "../../configs/api";
import  Axios from  'axios-observable';
import { Observable } from "rxjs";
import { IIndexItemRequest, IIndexItemResponse, IDeleteItemResponse, IAddItemRequest } from "../interfaces";

const usingBaseUrl = getBaseUrl()

const serviceIndexItem = (dataPayload:IIndexItemRequest): Observable<IIndexItemResponse> => {
    return Axios.post(
        usingBaseUrl+JOSEPH_URL.INDEX_ITEM,
        dataPayload
    )
}

export const serviceDeleteItem = (dataPayload:string): Observable<any> => {
    return Axios.delete(
        usingBaseUrl+JOSEPH_URL.DELETE_ITEM+dataPayload
    )
}

export const serviceAddItem = (dataPayload:IAddItemRequest): Observable<any> => {
    return Axios.post(
        usingBaseUrl+JOSEPH_URL.ADD_ITEM,
        dataPayload
    )
}


export {serviceIndexItem};