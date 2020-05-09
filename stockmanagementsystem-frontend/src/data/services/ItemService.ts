import { getBaseUrl, JOSEPH_URL } from "../../configs/api";
import  Axios from  'axios-observable';
import { Observable } from "rxjs";
import { IIndexItemRequest, IIndexItemResponse } from "../interfaces";

const usingBaseUrl = getBaseUrl()

const serviceIndexItem = (dataPayload:IIndexItemRequest): Observable<IIndexItemResponse> => {
    return Axios.post(
        usingBaseUrl+JOSEPH_URL.INDEX_ITEM,
        dataPayload
    )
}

export {serviceIndexItem};