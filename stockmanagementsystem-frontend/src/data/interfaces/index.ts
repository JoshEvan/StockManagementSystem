export enum HTTPCallStatus{
    Success = 'SUCCESS',
    Failed = 'FAILED'
}
export interface BaseResponse{
    status:HTTPCallStatus,
    data:{}
}

export { ICRUDResponse } from './ICRUD'

export { IItem } from './items/IItem';
export { IIndexItemRequest, IIndexItemResponse } from './items/IIndexItem';
export { IDeleteItemResponse } from './items/IDeleteItem';
export { IUpsertItemRequest,IUpsertItemResponse } from './items/IUpsertItem';

export { ICustomer } from './customers/ICustomer'
export { IIndexCustomerResponse } from './customers/IIndexCustomer'
export { IUpsertCustomerRequest,IUpsertCustomerResponse } from './customers/IUpsertCustomer'

export { IProduction } from './productions/IProduction'
export { IIndexProductionResponse } from './productions/IIndexProduction'

export { ITransaction, ITransactionDetail, initTransaction, initTransactionDetail } from './transactions/ITransaction'
export { IIndexTransactionRequest, getInitIndexTransactionRequest } from './transactions/IIndexTransaction'
export { IUpsertTransactionRequest,IUpsertTransactionDetailRequest } from './transactions/IUpsertTransaction'

export { ILoginRequest } from './ILogin';