import { ICustomer } from './ICustomer'

export interface IIndexCustomerResponse{
    data: {
        customers:ICustomer[]
    }
}