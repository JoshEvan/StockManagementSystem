
const API_URL = {
    DEV: {
        BASE_URL: 'http://localhost:8080/api/v1/joseph'
    },
    PROD: {
        BASE_URL:'https://joseph-sms.herokuapp.com/api/v1/joseph'
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
    },
    TRANSACTION:{
        INDEX: '/transaction/',
        DELETE:'/transaction/delete/',
        ADD:'/transaction/insert/',
        EDIT:'/transaction/update',
        DOWNLOAD_PDF:'/transaction/report'
    }
}

export const getBaseUrl = () =>{
    let URL: string;
    if(process.env.NODE_ENV === 'production'){
        URL = API_URL.PROD.BASE_URL;
    }else URL = API_URL.DEV.BASE_URL;
    return URL;
}

export const getLoginUrl = () => {
    if(process.env.NODE_ENV === 'production'){
        return 'https://joseph-sms.herokuapp.com/login'
    }
    return 'http://localhost:8080/login'
}