import { getBaseUrl, JOSEPH_URL } from "../../configs/api";
import { Observable } from "rxjs";
import Axios from "axios-observable";
import { IIndexTransactionRequest } from "../interfaces";

const baseUrl = getBaseUrl()

export const serviceIndexTransaction = (dataPayload: IIndexTransactionRequest) : Observable<any>=> {
    return Axios.post(
        baseUrl+JOSEPH_URL.TRANSACTION.INDEX,
        dataPayload
    )
}