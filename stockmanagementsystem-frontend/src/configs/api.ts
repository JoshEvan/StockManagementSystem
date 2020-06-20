
const API_URL = {
    DEV: {
        BASE_URL: 'http://localhost:8080/api/v1/joseph'
    }
}

export const JOSEPH_URL={
    ITEM: {
        INDEX: '/item/',
        DELETE:'/item/delete/',
        ADD:'/item/insert/',
        EDIT:'/item/update',
        DOWNLOAD_PDF:'/item/report'
    },
    CUSTOMER: {
        INDEX: '/customer/',
        DELETE:'/customer/delete/',
        ADD:'/customer/insert/',
        EDIT:'/customer/update',
    },
    PRODUCTION: {
        INDEX: '/production/',
        DELETE:'/production/delete/',
        ADD:'/production/insert/',
        EDIT:'/production/update',
    },
    PAYTYPE:{
        INDEX: '/payment/',
        DELETE:'/payment/delete/',
        ADD:'/payment/insert/',
        EDIT:'/payment/update',
    }
}

export const getBaseUrl = () =>{
    let URL: string;
    URL = API_URL.DEV.BASE_URL;
    return URL;
}