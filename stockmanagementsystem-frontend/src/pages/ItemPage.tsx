import React from 'react'
import { Link, RouteComponentProps } from 'react-router-dom';
import { Dashboard } from '../components/template/Dashboard';
import { CustomTable, AlertDialog, CustomizedSnackbars } from '../components/organism';
import { IItem, IIndexItemRequest, IDeleteItemResponse, HTTPCallStatus } from '../data/interfaces';
import { serviceIndexItem } from '../data/services';
import "regenerator-runtime/runtime.js";
import { Button } from '@material-ui/core';
import { async } from 'rxjs/internal/scheduler/async';
import { serviceDeleteItem } from '../data/services/ItemService';
import { Form } from '../components/organism/form';

interface Props extends RouteComponentProps{};

// history itu didapet dari props
// kalo pake Link nya react-router ga perlu props history
// history di pass dari Route nya react-router-dom

interface IItemPage{
	rawContent:IItem[],
	dataPayload:IIndexItemRequest,
	snackbar:{
		isShown:boolean,
		severity:string,
		msg:[]
	},
	addDialog:{
    isShown:boolean,
    title:string,
    content:any,
    usingAction:boolean,
		parentCallback:any,
		dialogYes:string,
		dialogNo:string,
  }
}

const colName: string[] = ["NUM","ITEM CODE", "NAME", 
"DESCRIPTION", "PRICE", "STOCK", "CAPACITY","TOTAL SOLD", "GENERATED INCOME","ACTION"]

export class ItemPage extends React.Component<Props,any> {
	
	state:IItemPage;
	constructor(props:Props){
		super(props);
		this.state = {
			rawContent:[],
			dataPayload:{
				sortByAmountIncome:0,
				sortByItemCode:0,
				sortByAmountSold:0
			},
			snackbar:{
				isShown:false,
				severity:"info",
				msg:[]
			},
			addDialog:{
				isShown:false,
				usingAction:false,
				title:"Add new item",
				content:(<Form/>), // TODO: FORM
				parentCallback:
					this.closeAddDialog,
				dialogNo:"cancel",
				dialogYes:"yes"
			}
		}
	}

	closeAddDialog = (isYes:Boolean) => {
		this.setState({
			addDialog:{
				isShown:false
			}
		});
	}
	
	closeSnackbar = () => {
		this.setState({
			snackbar:{
				isShown:false,
				severity:"info",
				msg:[]
			}
		});
	}
	
	deleteConfirm = (isYes:boolean, key:string) => {
		if(isYes) this.deleteItem(key);
	}

	deleteItem = async (key:string) => {
		await serviceDeleteItem(key).subscribe(
			(res:IDeleteItemResponse) => {

				var array = [...this.state.rawContent]
				var index = array.map((e) => {
					return e.itemCode
				}).indexOf(key);
				array.splice(index,1);

				this.setState({rawContent:array});
				this.setState({
					snackbar:{
						isShown:true,
						severity: ((res.data['status'] == HTTPCallStatus.Success) ? "success" : "error"),
						msg:res.data['msg']
					}
				})
			},
			(err)=>{
				console.log("delete item err:"+err);
				this.setState({
					snackbar:{
						isShown:true,
						severity:"error",
						msg:err
					}
				})
			}
		);
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

					<div>
						{
							this.state.snackbar.isShown &&
							(<CustomizedSnackbars
								severity={this.state.snackbar.severity}
								msg={this.state.snackbar.msg}
								parentCallback={this.closeSnackbar}
							/>)
						}
					</div>

					{/* {console.log("ATTABLE:"+this.state.rawContent[0].itemCode)} */}
					<CustomTable 
						addDialog={
							this.state.addDialog
						}
						header={colName}
						body={
							this.state.rawContent.map(
							(c:IItem, idx:number) => {
								// console.log("TOTABLE:"+c.itemCode)
								return(
									<React.Fragment>
									<tr>
										<td>{idx+1}</td>
										<td>{c.itemCode}</td>
										<td>{c.name}</td>
										<td>{c.description}</td>
										<td>{c.price}</td>
										<td>{c.stock}</td>
										<td>{c.capacity}</td>
										<td>{c.totalSold}</td>
										<td>{c.incomeAmount}</td>
										<td>{
											<React.Fragment>
												<Button variant="outlined" color="primary" >
												Edit
											</Button>
											<AlertDialog
												color="secondary"
												param={c.itemCode}
												buttonTitle="delete"
												dialogTitle="This following item will be deleted"
												dialogYes="Yes"
												dialogNo="Cancel"
												dialogContent="Are you sure ?"
												parentCallback={
													this.deleteConfirm
												}
											/>
											</React.Fragment>
										}</td>
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