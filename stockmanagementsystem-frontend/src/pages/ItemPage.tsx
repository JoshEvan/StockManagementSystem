import React from 'react'
import { Link, RouteComponentProps } from 'react-router-dom';
import { Dashboard } from '../components/template/Dashboard';
import { CustomTable } from '../components/organism';
import { IItem, IIndexItemRequest } from '../data/interfaces';
import { serviceIndexItem } from '../data/services';
import "regenerator-runtime/runtime.js";

interface Props extends RouteComponentProps{};

// history itu didapet dari props
// kalo pake Link nya react-router ga perlu props history
// history di pass dari Route nya react-router-dom

interface IItemPage{
	rawContent:IItem[],
	dataPayload:IIndexItemRequest
}

const colName: string[] = ["NUM","ITEM CODE", "NAME", "DESCRIPTION", "PRICE", "STOCK", "CAPACITY","TOTAL SOLD", "GENERATED INCOME"]

export class ItemPage extends React.Component<Props,IItemPage> {
	
	state:IItemPage;
	constructor(props:Props){
		super(props);
		this.state = {
			rawContent:[],
			dataPayload:{
				sortByAmountIncome:0,
				sortByItemCode:0,
				sortByAmountSold:0
			}
		}
	}

	async componentDidMount(){
		console.log("posting index request")
		await serviceIndexItem(this.state.dataPayload).subscribe(
			(res) => {
				console.log("RES:"+Object.keys(res).length);
				console.log(res.data["items"]);
				this.setState({
					rawContent: res.data["items"]
				});
				console.log(this.state.rawContent[0].itemCode);
				console.log("STATE:"+Object.keys(this.state.rawContent).length);
			},
			(err)=>{
				console.log("axios err:"+err);
			}
		);
	}

	render(){
		return (
			<Dashboard 
			titlePage = {"Items"}			
			content={
				<div>
					{/* {console.log("ATTABLE:"+this.state.rawContent[0].itemCode)} */}
					<CustomTable 
						test={"test"} 
						header={colName}
						body={
							this.state.rawContent.map(
							(c:IItem, idx:number) => {
								// console.log("TOTABLE:"+c.itemCode)
								return(
									<React.Fragment>
									<tr>
										<td key={colName[0]}>{idx+1}</td>
										<td key={colName[1]}>{c.itemCode}</td>
										<td key={colName[2]}>{c.name}</td>
										<td key={colName[3]}>{c.description}</td>
										<td key={colName[4]}>{c.price}</td>
										<td key={colName[5]}>{c.stock}</td>
										<td key={colName[6]}>{c.capacity}</td>
										<td key={colName[7]}>{c.totalSold}</td>
										<td key={colName[8]}>{c.incomeAmount}</td>
									</tr>
									</React.Fragment>
								);
							}
						)}
					/>
				</div>
			}/>
		)
	}
};

// export default ItemPage;