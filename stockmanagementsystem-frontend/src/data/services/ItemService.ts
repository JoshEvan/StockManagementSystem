import { getBaseUrl, JOSEPH_URL } from "../../configs/api";
import  Axios from  'axios-observable';
import { Observable } from "rxjs";
import { IIndexItemRequest, IIndexItemResponse, IDeleteItemResponse, IUpsertItemRequest } from "../interfaces";

const usingBaseUrl = getBaseUrl()

const serviceIndexItem = (dataPayload:IIndexItemRequest): Observable<IIndexItemResponse> => {
    return Axios.post(
        usingBaseUrl+JOSEPH_URL.ITEM.INDEX,
        dataPayload
    )
}

export const serviceDeleteItem = (dataPayload:string): Observable<any> => {
    return Axios.delete(
        usingBaseUrl+JOSEPH_URL.ITEM.DELETE+dataPayload
    )
}

export const serviceAddItem = (dataPayload:IUpsertItemRequest): Observable<any> => {
    return Axios.post(
        usingBaseUrl+JOSEPH_URL.ITEM.ADD,
        dataPayload
    )
}

export const serviceEditItem = (dataPayload:IUpsertItemRequest): Observable<any> => {
    return Axios.put(
        usingBaseUrl+JOSEPH_URL.ITEM.EDIT,
        dataPayload 
    )
}

export const serviceDownloadPdfItem = (dataPayload:IIndexItemRequest): Observable<any> => {
    return Axios.request(
       {
           method:'post',
           url: usingBaseUrl+JOSEPH_URL.ITEM.DOWNLOAD_PDF,
           data: dataPayload,
           responseType:'blob' // very important, else corrupted file
       }
    )
}

export {serviceIndexItem};