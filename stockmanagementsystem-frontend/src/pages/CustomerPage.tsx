import React from 'react'
import { Link, RouteComponentProps } from 'react-router-dom';
import { Dashboard } from '../components/template/Dashboard';
import { CustomTable, AlertDialog, CustomizedSnackbars } from '../components/organism';
import { ICustomer, IIndexItemRequest, IDeleteItemResponse, HTTPCallStatus, IUpsertCustomerRequest, IUpsertItemResponse} from '../data/interfaces';
import "regenerator-runtime/runtime.js";
import { Button, Paper, Card, CardContent, Typography } from '@material-ui/core';
import { async } from 'rxjs/internal/scheduler/async';
import { serviceAddCustomer, serviceEditCustomer, serviceIndexCustomer,serviceDeleteCustomer } from '../data/services';
import { CustomerForm } from '../components/organism/form';

interface Props extends RouteComponentProps{};

// history itu didapet dari props
// kalo pake Link nya react-router ga perlu props history
// history di pass dari Route nya react-router-dom

interface ICustomerPage{
	rawContent:ICustomer[],
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

const colName: string[] = ["NUM","ID", "NAME", 
"DESCRIPTION", "CONTACT", "TOTAL AMOUNT SPEND", "ACTION"]

const initCustomer={
	id:'',
	name:'',
	description:'',
	contact:'',
}

export class CustomerPage extends React.Component<Props,any> {
	
	state:ICustomerPage;
	constructor(props:Props){
		super(props);
		this.state = {
			rawContent:[],
			snackbar:{
				isShown:false,
				severity:"info",
				msg:[]
			},
			addDialog:{
				isShown:false,
				usingAction:false,
				title:"Add new customer",
				content:(
					<CustomerForm
						isEdit = {false}
						submitData = {this.addCustomer}
						item= {
							initCustomer
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
		if(isYes) this.deleteCustomer(key);
	}

	addCustomer = async (data:IUpsertCustomerRequest) => {
		await serviceAddCustomer(data).subscribe(
			(res:IUpsertItemResponse) => {
				if(res.data['status'] == HTTPCallStatus.Success){
					// TODO: set viewConstraint to default ?
					
					this.loadAllCustomers()
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
					<CustomerForm
						submitData = {this.addCustomer}
						item={initCustomer}
					/>
				)
			}
		})
	}

	editItem = async (data:IUpsertCustomerRequest) => {
		await serviceEditCustomer(data).subscribe(
			(res:IUpsertItemResponse) => {
				if(res.data['status'] == HTTPCallStatus.Success){
					// TODO: set viewConstraint to default ?
					this.loadAllCustomers()
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

	deleteCustomer = async (key:string) => {
		await serviceDeleteCustomer(key).subscribe(	
			(res:IDeleteItemResponse) => {
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
				console.log("delete customer err:"+err);
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

	loadAllCustomers = async () => {
		console.log("posting index request customers")
		await serviceIndexCustomer().subscribe(
			(res) => {
				console.log("RES:"+Object.keys(res).length);
				console.log(res.data["customers"]);
				this.setState({
					rawContent: res.data["customers"]
				});
				console.log("STATE:"+Object.keys(this.state.rawContent).length);
			},
			(err)=>{
				console.log("axios err:"+err);
			}
		);
	}

	async componentDidMount(){
		this.loadAllCustomers();
	}

	render(){
		return (
			<Dashboard 
			titlePage = {"Customers"}			
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
				
					<CustomTable 
						addDialog={
							this.state.addDialog
						}
						isShown={this.state.addDialog.isShown}
						header={colName}
						body={
							this.state.rawContent.map(
							(c:ICustomer, idx:number) => {
								// console.log("TOTABLE:"+c.itemCode)
								return(
									<React.Fragment>
									<tr>
										<td>{idx+1}</td>
										<td>{c.id}</td>
										<td>{c.name}</td>
										<td>{c.description}</td>
										<td>{c.contact}</td>
										<td>{c.totalAmountSpend}</td>
										<td>{
											<React.Fragment>
												<AlertDialog
													color="primary"
													param={c.id}
													parentAllowance = {this.state.editDialog.isShown}
													buttonTitle="edit"
													parentCallbackOpen={()=>this.setState({editDialog:{isShown:true}})}
													dialogTitle="Update"
													usingAction={false}
													dialogContent={
														<CustomerForm
															isEdit = {true}
															submitData = {this.editItem}
															item={
																{
																	id:c.id,
																	name:c.name,
																	contact:c.contact,
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
												param={c.id}
												buttonTitle="delete"
												dialogTitle="This following customer will be deleted"
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
