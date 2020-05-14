export { serviceIndexItem,serviceDeleteItem as serviceDeletetem, serviceEditItem, serviceDownloadPdfItem} from './ItemService';

export function getCurrentDate(separator=''){
    let currDate = new Date()
    let date = currDate.getDate();
    let month = currDate.getMonth() + 1;
    let year = currDate.getFullYear();

    return `${year}${separator}${month<10?`0${month}`:`${month}`}${separator}${date}`
}