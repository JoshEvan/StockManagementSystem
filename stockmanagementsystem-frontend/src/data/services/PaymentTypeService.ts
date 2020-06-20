import { getBaseUrl, JOSEPH_URL } from "../../configs/api";
import Axios from "axios-observable";
import { Observable } from "rxjs";
import { IPayemntType } from "../interfaces/paymentTypes/IPaymentType";

const usingBaseUrl = getBaseUrl()

export const serviceIndexPaymentType = () : Observable<any>=> {
    return Axios.get(
        usingBaseUrl+JOSEPH_URL.PAYTYPE.INDEX
    )
}

export const serviceAddPayType = (dataPayload: IPayemntType) : Observable<any>=> {
    return Axios.post(
        usingBaseUrl+JOSEPH_URL.PAYTYPE.ADD,
        dataPayload
    )
}

export const serviceDeletePayType = (dataPayload: string) : Observable<any> => {
    return Axios.delete(
        usingBaseUrl+JOSEPH_URL.PAYTYPE.DELETE+dataPayload
    )
}

export const serviceEditPayType = (dataPayload:IPayemntType): Observable<any> => {
    return Axios.put(
        usingBaseUrl+JOSEPH_URL.PAYTYPE.EDIT,
        dataPayload
    )
}