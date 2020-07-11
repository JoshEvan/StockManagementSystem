import { Observable, BehaviorSubject } from "rxjs";
import Axios from "axios-observable";
import { getLoginUrl } from "../../configs/api";
import { ILoginRequest } from "../interfaces";

// const currentUserSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('loggedInUser')));

export const serviceLogin = (dataPayload: ILoginRequest): Observable<any> => {
    return Axios.post(
        getLoginUrl(),
        dataPayload
    )
}

export const setBehaviorSubject =  (user) => {
    // currentUserSubject.next(user)
}