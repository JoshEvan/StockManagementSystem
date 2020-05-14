import React from 'react'
import { Link, RouteComponentProps } from 'react-router-dom';
import { Dashboard } from '../components/template/Dashboard';
import { CustomTable, AlertDialog, CustomizedSnackbars } from '../components/organism';
import { IItem, IIndexItemRequest, IDeleteItemResponse, HTTPCallStatus, IUpsertItemRequest, IUpsertItemResponse} from '../data/interfaces';
import { serviceIndexItem, getCurrentDate } from '../data/services';
import "regenerator-runtime/runtime.js";
import { Button } from '@material-ui/core';
import { async } from 'rxjs/internal/scheduler/async';
import { serviceDeleteItem, serviceAddItem, serviceEditItem, serviceDownloadPdfItem } from '../data/services/ItemService';
import { Form } from '../components/organism/form';

interface Props extends RouteComponentProps{};

// history itu didapet dari props
// kalo pake Link nya react-router ga perlu props history
// history di pass dari Route nya react-router-dom

interface IItemPage{
	rawContent:IItem[],
	viewConstraint:IIndexItemRequest,
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
		dialogYes:string,
		dialogNo:string,
	},
	editDialog:{
		isShown:boolean
	}
}

const colName: string[] = ["NUM","ITEM CODE", "NAME", 
"DESCRIPTION", "PRICE", "STOCK", "CAPACITY","TOTAL SOLD", "GENERATED INCOME","ACTION"]

const initItem={
	itemCode:'',
	name:'',
	description:'',
	price:0,
	stock:0,
	capacity:0,
}

export class ItemPage extends React.Component<Props,any> {
	
	state:IItemPage;
	constructor(props:Props){
		super(props);
		this.state = {
			rawContent:[],
			viewConstraint:{
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
				content:(
					<Form
						submitData = {this.addItem}
						item= {
							initItem
						}
					/>
				), // TODO: FORM
				dialogNo:"cancel",
				dialogYes:"yes"
			},
			editDialog:{
				isShown:true
			}
		}
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

	addItem = async (data:IUpsertItemRequest) => {
		await serviceAddItem(data).subscribe(
			(res:IUpsertItemResponse) => {
				if(res.data['status'] == HTTPCallStatus.Success){
					// TODO: set viewConstraint to default ?
					
					this.loadAllItems()
				}
				this.setState({
					snackbar:{
						isShown:true,
						severity: ((res.data['status'] == HTTPCallStatus.Success) ? "success" : "error"),
						msg:res.data['msg']
					}
				})
			},
			(err)=>{
				console.log("add item err:"+err);
				this.setState({
					snackbar:{
						isShown:true,
						severity:"error",
						msg:err
					}
				})
			}
		)
		this.setState({
			addDialog:{
				isShown:false,
				content:(
					<Form
						submitData = {this.addItem}
						item={initItem}
					/>
				)
			}
		})
	}

	editItem = async (data:IUpsertItemRequest) => {
		await serviceEditItem(data).subscribe(
			(res:IUpsertItemResponse) => {
				if(res.data['status'] == HTTPCallStatus.Success){
					// TODO: set viewConstraint to default ?
					this.loadAllItems()
				}
				this.setState({
					snackbar:{
						isShown:true,
						severity: ((res.data['status'] == HTTPCallStatus.Success) ? "success" : "error"),
						msg:res.data['msg']
					}
				})
			},
			(err)=>{
				console.log("edit item err:"+err);
				this.setState({
					snackbar:{
						isShown:true,
						severity:"error",
						msg:err
					}
				})
			}
		)
		this.setState({
			editDialog:{isShown:false}
		})
	}

	deleteItem = async (key:string) => {
		await serviceDeleteItem(key).subscribe(	
			(res:IDeleteItemResponse) => {
				if(res.data['status'] == HTTPCallStatus.Success){
					var array = [...this.state.rawContent]
					var index = array.map((e) => {
						return e.itemCode
					}).indexOf(key);
					array.splice(index,1);

					this.setState({rawContent:array});
				}
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

	loadAllItems = async () => {
		console.log("posting index request")
		await serviceIndexItem(this.state.viewConstraint).subscribe(
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
	
	downloadPdf = async() => {
		await serviceDownloadPdfItem(this.state.viewConstraint).subscribe(
			(res) => {
				const url = window.URL.createObjectURL(new Blob([res.data]));
				const link = document.createElement('a');
				link.href = url;
				link.setAttribute('download', 'ItemReport'+getCurrentDate("_")+'.pdf');
				document.body.appendChild(link);
				link.click();
			}
		)
	}

	async componentDidMount(){
		this.loadAllItems();
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
					
					<div style={{float:'left',width:'auto',padding:'1% 1% 1% 0%'}}>
						<Button variant="outlined" color={"primary"} onClick={this.downloadPdf}>
							{"download pdf"}
						</Button>
					</div>
					{/* {console.log("ATTABLE:"+this.state.rawContent[0].itemCode)} */}
					<CustomTable 
						addDialog={
							this.state.addDialog
						}
						isShown={this.state.addDialog.isShown}
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
												<AlertDialog
													color="primary"
													param={c.itemCode}
													parentAllowance = {this.state.editDialog.isShown}
													buttonTitle="edit"
													parentCallbackOpen={()=>this.setState({editDialog:{isShown:true}})}
													dialogTitle="Update item"
													usingAction={false}
													dialogContent={
														<Form
															submitData = {this.editItem}
															item={
																{
																	itemCode:c.itemCode,
																	name:c.name,
																	price:c.priceDec,
																	stock:c.stock,
																	capacity:c.capacity,
																	description:c.description,
																}
															}
														/>
													}
												/>
											<AlertDialog
												color="secondary"
												usingAction={true}
												parentAllowance = {true}
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