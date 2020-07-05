export interface ITransaction{
	id : string,
	customerId: string,
	paymentId: string,
	transactionDate: Date,
	paymentStatus: string,
	transactionDetails: ITransactionDetail[],
	timestamp: Date,
	note:string,
	total:string,
	totalDec: number
}

export interface ITransactionDetail{
	transactionHeaderId: string,
	itemCode: string,
	price: string,
	quantity: number,
	note: string,
	timestamp: Date,
	subTotal: string,
	subTotalDec: number
}

export const getInitTransaction = () => {
	return {id : "",
	customerId: "",
	paymentId: "",
	transactionDate: Date.now(),
	paymentStatus: "",
	transactionDetails: [],
	timestamp: Date.now(),
	note:"",
	total:"",
	totalDec: 0}
}

export const initTransactionDetail = {
	transactionHeaderId: "",
	itemCode: "",
	price: "",
	quantity: 0,
	note: "",
	timestamp: Date.now(),
	subTotal: "",
	subTotalDec: 0
}