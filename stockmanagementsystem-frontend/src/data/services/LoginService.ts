import { Observable } from "rxjs";
import Axios from "axios-observable";
import { getLoginUrl } from "../../configs/api";
import { ILoginRequest } from "../interfaces";

export const serviceLogin = (dataPayload: ILoginRequest): Observable<any> => {
    return Axios.post(
        getLoginUrl(),
        dataPayload
    )
}