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

    // let mockResponse = {
    //     data: {transactions: [
    //         {
    //             num: 1,
    //             id : 'TRX001',
    //             customerId: 'CUST001',
    //             paymentId: 'PAY001',
    //             paymentType: 'Credit Card',
    //             paymentStatus: 'Paid',
    //             transactionDetails: [
    //                 {
    //                     transactionHeaderId: "TRX001",
    //                     itemCode: "ITEM001",
    //                     price: "100,000",
    //                     quantity: 2,
    //                     note: "This is a note",
    //                     timestamp: new Date(),
    //                     subTotal: "200,000",
    //                     subTotalDec: 200000
    //                 }
    //             ],
    //             total: '500,000',
    //             totalDec: 500000,
    //             note: 'This is a note',
    //             timestamp: '2024-01-01 10:00:00'
    //         },
    //     ]},
    // };
    // return new Observable<any>(subscriber => {
    //     subscriber.next(mockResponse);
    //     subscriber.complete();
    // });
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