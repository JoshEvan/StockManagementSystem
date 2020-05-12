export enum HTTPCallStatus{
    Success = 'SUCCESS',
    Failed = 'FAILED'
}
export interface BaseResponse{
    status:HTTPCallStatus,
    data:{}
}
export { IItem } from './IItem';
export { IIndexItemRequest, IIndexItemResponse } from './IIndexItem';
export { IDeleteItemResponse } from './IDeleteItem';
export { IAddItemRequest,IAddItemResponse } from './IAddItem';