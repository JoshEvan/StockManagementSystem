import React from 'react'
import { RouteComponentProps } from 'react-router-dom';
import { Dashboard } from '../components/template/Dashboard';
import { CustomTable, AlertDialog, CustomizedSnackbars } from '../components/organism';
import { HTTPCallStatus, IUpsertItemResponse, ICRUDResponse, IUpsertTransactionRequest, IUpsertTransactionDetailRequest} from '../data/interfaces';
import { getCurrentDate, serviceIndexTransaction, serviceDeleteTransaction, serviceAddTransaction, serviceEditTransaction, serviceDownloadPdfItem, serviceDownloadPdfTransaction } from '../data/services';
import "regenerator-runtime/runtime.js";
import { Button } from '@material-ui/core';
import { TransactionForm } from '../components/organism/form';
import ArrowDropDownIcon from '@material-ui/icons/ArrowDropDown';
import ArrowDropUpIcon from '@material-ui/icons/ArrowDropUp';
import { SimpleExpansionPanel } from '../components/organism/expansion_panel/SimpleExpansionPanel';
import { ITransaction, ITransactionDetail, getInitTransaction } from '../data/interfaces/transactions/ITransaction';
import { getInitIndexTransactionRequest, IIndexTransactionRequest } from '../data/interfaces/transactions/IIndexTransaction';
import { getBaseUrl } from '../configs/api';

interface Props extends RouteComponentProps{};
interface ITransactionPage{
	rawContent:ITransaction[],
	viewConstraint:IIndexTransactionRequest,
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

const colName: string[] = ["NUM","TRANS ID", "CUST ID", 
"PAY TYPE", "PAY STATUS", "DETAILS", "TOTAL","NOTE","LAST UPDATE","ACTION"]
const detailColName: string[] = [
	"NUM","ITEM CODE","PRICE","QTY","SUBTOTAL","NOTE"
]

const TITLE:string = "Transactions"

export class TransactionPage extends React.Component<Props,any> {
	
	state:ITransactionPage;
	constructor(props:Props){
		super(props);
		this.state = {
			rawContent:[],
			viewConstraint:getInitIndexTransactionRequest(),
			snackbar:{
				isShown:false,
				severity:"info",
				msg:[]
			},
			addDialog:{
				isShown:false,
				usingAction:false,
				title:"Add new transactions",
				content:(
					<TransactionForm
						submitData = {this.addData}
						item= {
							getInitTransaction()
						}
					/>
				),
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
		if(isYes) this.deleteData(key);
	}

	addData = async (data:IUpsertTransactionRequest, dataDetail:IUpsertTransactionDetailRequest[]) => {
		data.transactionDetails = dataDetail
		await serviceAddTransaction(data).subscribe(
			(res:IUpsertItemResponse) => {
				if(res.data['status'] == HTTPCallStatus.Success){
					this.loadAllData()
				}
				this.setState({
					snackbar:{
						isShown:true,
						severity: ((res.data['status'] == HTTPCallStatus.Success) ? "success" : "error"),
						msg:res.data['message']
					}
				})
			},
			(err)=>{
				console.log("add item err:"+err);
				this.setState({
					snackbar:{
						isShown:true,
						severity:"error",
						msg:err.message.split()
					}
				})
			}
		)
		this.setState({
			addDialog:{
				isShown:false,
				content:(
					<TransactionForm
						submitData = {this.addData}
						item={getInitTransaction()}
					/>
				)
			}
		})
	}

	editData = async (data:IUpsertTransactionRequest, dataDetail:IUpsertTransactionDetailRequest[]) => {
		data.transactionDetails = dataDetail
		await serviceEditTransaction(data).subscribe(
			(res:ICRUDResponse) => {
				if(res.data['status'] == HTTPCallStatus.Success){
					// TODO: set viewConstraint to default ?
					this.loadAllData()
				}
				this.setState({
					snackbar:{
						isShown:true,
						severity: ((res.data['status'] == HTTPCallStatus.Success) ? "success" : "error"),
						msg:res.data['message']
					}
				})
			},
			(err)=>{
				console.log("edit item err:"+err);
				this.setState({
					snackbar:{
						isShown:true,
						severity:"error",
						msg:err.message.split()
					}
				})
			}
		)
		this.setState({
			editDialog:{isShown:false}
		})
	}

	deleteData = async (key:string) => {
		await serviceDeleteTransaction(key).subscribe(	
			(res:ICRUDResponse) => {
				if(res.data['status'] == HTTPCallStatus.Success){
					var array = [...this.state.rawContent]
					var index = array.map((e) => {
						return e.id
					}).indexOf(key);
					array.splice(index,1);

					this.setState({rawContent:array});
				}
				this.setState({
					snackbar:{
						isShown:true,
						severity: ((res.data['status'] == HTTPCallStatus.Success) ? "success" : "error"),
						msg:res.data['message']
					}
				})
			},
			(err)=>{
				console.log("delete item err:"+err);
				this.setState({
					snackbar:{
						isShown:true,
						severity:"error",
						msg:err.message.split()
					}
				})
			}
		);
	}

	loadAllData = async () => {
		console.log("posting index request")

		await serviceIndexTransaction(this.state.viewConstraint).subscribe(
			(res) => {
				console.log("RES:"+Object.keys(res).length);
				console.log(res.data["transactions"]);
				this.setState({
					rawContent: res.data["transactions"]
				});
				console.log("STATE:"+Object.keys(this.state.rawContent).length);
			},
			(err)=>{
				console.log("axios err:"+err);
				this.setState({
					snackbar:{
						isShown:true,
						severity:"error",
						msg:err.message.split()
					}
				})
			}
		);
	}
	
	downloadPdf = async() => {
		await serviceDownloadPdfTransaction(this.state.viewConstraint).subscribe(
			(res) => {
				const url = window.URL.createObjectURL(new Blob([res.data]));
				const link = document.createElement('a');
				link.href = url;
				link.setAttribute('download', 'TransactionReport'+getCurrentDate("_")+'.pdf');
				document.body.appendChild(link);
				link.click();
			}
		)
	}

	async componentDidMount(){
		this.loadAllData();
	}

	render(){
		return (
			<Dashboard 
			titlePage = {TITLE}
			content={
				<div>
					{/* <SimpleExpansionPanel 
						title="sort"
						content={
							<div>
								<div>
									<ArrowDropUpIcon color={(this.state.viewConstraint.sortByAmountIncome > 0) ? "secondary" : "disabled"}
									onClick = {() => {
													console.log(this.state.viewConstraint)
													const temp = this.state.viewConstraint;
													temp.sortByAmountIncome = 1;
													this.setState({viewConstraint:temp})}}/>
									<ArrowDropDownIcon color={(this.state.viewConstraint.sortByAmountIncome < 0) ? "secondary" : "disabled"}
									onClick = 
										{() =>{const temp = this.state.viewConstraint;
										temp.sortByAmountIncome = -1;
										this.setState({viewConstraint:temp})}}/>
									By Amount Income
								</div>
								<div>
									<ArrowDropUpIcon color={(this.state.viewConstraint.sortByAmountSold > 0) ? "secondary" : "disabled"}
									onClick = 
										{() =>{const temp = this.state.viewConstraint;
										temp.sortByAmountSold = 1;
										this.setState({viewConstraint:temp})}}/>
									<ArrowDropDownIcon color={(this.state.viewConstraint.sortByAmountSold < 0) ? "secondary" : "disabled"}
									onClick = 
										{() =>{const temp = this.state.viewConstraint;
										temp.sortByAmountSold = -1;
										this.setState({viewConstraint:temp})}}/>
									By Amount Sold
								</div>
								<div>
									<ArrowDropUpIcon color={(this.state.viewConstraint.sortByItemCode > 0) ? "secondary" : "disabled"}
									onClick = 
										{() =>{const temp = this.state.viewConstraint;
										temp.sortByItemCode = 1;
										this.setState({viewConstraint:temp})}}/>
									<ArrowDropDownIcon color={(this.state.viewConstraint.sortByItemCode < 0) ? "secondary" : "disabled"}
									onClick = 
										{() =>{const temp = this.state.viewConstraint;
										temp.sortByItemCode = -1;
										this.setState({viewConstraint:temp})}}/>
									By Item Code
								</div>
								<div>
									<Button color="primary" variant="outlined" onClick = {this.loadAllItems}>
										show
									</Button>
									<Button color="secondary" variant="outlined"
										onClick ={() => {
											this.setState({viewConstraint:getInitViewConstraint()})
											console.log(this.state.viewConstraint)
											this.loadAllItems()
										}
										}>
										reset</Button>
								</div>
								
							</div>
						}/> */}
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
							(c:ITransaction, idx:number) => {
								// console.log("TOTABLE:"+c.itemCode)
								return(
									<React.Fragment>
									<tr>
										<td>{idx+1}</td>
										<td>{c.id}</td>
										<td>{c.customerId}</td>
										<td>{c.paymentId}</td>
										<td>{c.paymentStatus}</td>
										<td>
										<SimpleExpansionPanel 
											title={"show details ("+c.transactionDetails.length+")"}
											content={
												<div>
													<CustomTable
														notShowAddButton={true}
														header={detailColName}
														body={
															c.transactionDetails.map(
																(d:ITransactionDetail, idx:number) => {
																	// console.log("TOTABLE:"+c.itemCode)
																	return(
																		<React.Fragment>
																			<tr>
																				<td>{idx+1}</td>
																				<td>{d.itemCode}</td>
																				<td>{d.price}</td>
																				<td>{d.quantity}</td>
																				<td>{d.subTotal}</td>
																				<td>{d.note}</td>
																			</tr>
																		</React.Fragment>
																)
															})
															}
													/>
												</div>
											}/>
										</td>
										<td>{c.total}</td>
										<td>{c.note}</td>
										<td>{c.timestamp}</td>
										<td>{
											<React.Fragment>
												<AlertDialog
													color="primary"
													param={c.id}
													parentAllowance = {this.state.editDialog.isShown}
													buttonTitle="edit"
													parentCallbackOpen={()=>this.setState({editDialog:{isShown:true}})}
													dialogTitle="Update item"
													usingAction={false}
													dialogContent={
														<TransactionForm
															submitData = {this.editData}
															item={
																{
																	id:c.id,
																	customerId:c.customerId,
																	paymentId:c.paymentId,
																	paymentStatus:c.paymentStatus,
																	transactionDetails:c.transactionDetails.map(
																		(d:ITransactionDetail) => {
																			return {
																				itemCode:d.itemCode,
																				note:d.note,
																				quantity:d.quantity
																			}
																		}
																	),
																	transactionDate:c.transactionDate,
																	note:c.note
																}
															}
														/>
													}
												/>
											<AlertDialog
												color="secondary"
												usingAction={true}
												parentAllowance = {true}
												param={c.id}
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