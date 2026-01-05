interface IItem{
 num:number,
 itemCode:string,
 name:string,
 description:string,
 price:string,
 stock:number,
 stockInfo?: string,
 capacity:number,
 totalSold:number,
 incomeAmountDec:any,
 incomeAmount:string,
 priceDec:any,
 actionEdit:any,
 actionDelete:any,
}

export { IItem };