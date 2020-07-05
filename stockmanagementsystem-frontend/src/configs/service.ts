export const serviceConfigCommon = {
    withCredentials: false,
    headers:{
        'Content-Type':'application/json',
        'Authorization':'Bearer '+localStorage.getItem("JWT")
    },
}