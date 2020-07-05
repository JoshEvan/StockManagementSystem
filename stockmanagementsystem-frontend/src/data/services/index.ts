import Axios from 'axios-observable';

Axios.defaults.headers["Authorization"] = 'Bearer '+localStorage.getItem("JWT")

export { serviceIndexItem,serviceDeleteItem as serviceDeletetem, serviceEditItem, serviceDownloadPdfItem} from './ItemService';

export { serviceIndexCustomer,serviceEditCustomer, serviceAddCustomer, serviceDeleteCustomer } from './CustomerService';

export { serviceIndexProduction, serviceDeleteProduction, serviceAddProduction, serviceEditProduction } from './ProductionService';

export { serviceIndexPaymentType, serviceAddPayType, serviceDeletePayType, serviceEditPayType } from './PaymentTypeService';

export { serviceIndexTransaction, serviceDeleteTransaction, serviceAddTransaction, serviceEditTransaction, serviceDownloadPdfTransaction } from './TransaactionService';

export { serviceLogin } from './LoginService';

export function getCurrentDate(separator=''){
    let currDate = new Date()
    let date = currDate.getDate();
    let month = currDate.getMonth() + 1;
    let year = currDate.getFullYear();

    return `${year}${separator}${month<10?`0${month}`:`${month}`}${separator}${date}`
}