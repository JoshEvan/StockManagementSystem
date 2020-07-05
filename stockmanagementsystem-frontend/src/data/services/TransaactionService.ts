import { getBaseUrl, JOSEPH_URL } from "../../configs/api";
import { Observable } from "rxjs";
import Axios from "axios-observable";
import { IIndexTransactionRequest, IUpsertTransactionRequest } from "../interfaces";
import { serviceConfigCommon } from "../../configs/service";

const baseUrl = getBaseUrl()

export const serviceIndexTransaction = (dataPayload: IIndexTransactionRequest) : Observable<any>=> {
    return Axios.post(
        baseUrl+JOSEPH_URL.TRANSACTION.INDEX,
        dataPayload,
        serviceConfigCommon
    )
}

export const serviceDeleteTransaction = (dataPayload:string) : Observable<any> => {
    return Axios.delete(
        baseUrl+JOSEPH_URL.TRANSACTION.DELETE+dataPayload,
        serviceConfigCommon
    )
}

export const serviceAddTransaction = (dataPayload:IUpsertTransactionRequest) : Observable<any> => {
    return Axios.post(
        baseUrl+JOSEPH_URL.TRANSACTION.ADD,
        dataPayload,
        serviceConfigCommon
    )
}

export const serviceEditTransaction = (dataPayload:IUpsertTransactionRequest) : Observable<any> => {
    return Axios.put(
        baseUrl+JOSEPH_URL.TRANSACTION.EDIT,
        dataPayload,
        serviceConfigCommon
    )
}

export const serviceDownloadPdfTransaction = (dataPayload: IIndexTransactionRequest) : Observable<any> => {
    return Axios.request({
        method:'post',
        url:baseUrl+JOSEPH_URL.TRANSACTION.DOWNLOAD_PDF,
        data: dataPayload,
        responseType:'blob'
    })
}