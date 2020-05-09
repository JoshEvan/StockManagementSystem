
const API_URL = {
    DEV: {
        BASE_URL: 'http://localhost:8080/api/v1/joseph'
    }
}

export const JOSEPH_URL={
    INDEX_ITEM: '/item/'
}

export const getBaseUrl = () =>{
    let URL: string;
    URL = API_URL.DEV.BASE_URL;
    return URL;
}