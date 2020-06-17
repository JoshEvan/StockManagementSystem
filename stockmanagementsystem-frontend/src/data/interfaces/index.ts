export enum HTTPCallStatus{
    Success = 'SUCCESS',
    Failed = 'FAILED'
}
export interface BaseResponse{
    status:HTTPCallStatus,
    data:{}
}
export { IItem } from './items/IItem';
export { IIndexItemRequest, IIndexItemResponse } from './items/IIndexItem';
export { IDeleteItemResponse } from './items/IDeleteItem';
export { IUpsertItemRequest,IUpsertItemResponse } from './items/IUpsertItem';

export { ICustomer } from './customers/ICustomer'
export { IIndexCustomerResponse } from './customers/IIndexCustomer'
export { IUpsertCustomerRequest,IUpsertCustomerResponse } from './customers/IUpsertCustomer'
