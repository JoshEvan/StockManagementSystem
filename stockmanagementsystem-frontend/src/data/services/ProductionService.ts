import { getBaseUrl, JOSEPH_URL } from "../../configs/api";
import { Observable } from "rxjs";
import { IIndexProductionResponse, IProduction } from '../interfaces'
import Axios from "axios-observable";
const usingBaseUrl = getBaseUrl()

export const serviceIndexProduction = (): Observable<IIndexProductionResponse> => {
    return Axios.get(
        usingBaseUrl+JOSEPH_URL.PRODUCTION.INDEX
    )
}

export const serviceAddProduction = (dataPayload:IProduction) : Observable<any> => {
    return Axios.post(
        usingBaseUrl+JOSEPH_URL.PRODUCTION.ADD,
        dataPayload
    )
} 

export const serviceDeleteProduction = (dataPayload:string) : Observable<any> => {
    return Axios.delete(
        usingBaseUrl+JOSEPH_URL.PRODUCTION.DELETE+dataPayload
    )
}

export const serviceEditProduction = (dataPayload:IProduction) : Observable<any> => {
    return Axios.put(
        usingBaseUrl+JOSEPH_URL.PRODUCTION.EDIT,
        dataPayload
    )
} 