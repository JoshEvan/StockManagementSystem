import React from 'react'
import { Link, RouteComponentProps } from 'react-router-dom';
import { Dashboard } from '../components/template/Dashboard';
import { CustomTable, AlertDialog, CustomizedSnackbars } from '../components/organism';
import { ICustomer, IDeleteItemResponse, HTTPCallStatus, IUpsertCustomerRequest, IUpsertItemResponse} from '../data/interfaces';
import "regenerator-runtime/runtime.js";
import { serviceAddCustomer, serviceEditCustomer, serviceIndexCustomer,serviceDeleteCustomer, serviceIndexProduction } from '../data/services';
import { CustomerForm, ProductionForm } from '../components/organism/form';
import { IProduction } from '../data/interfaces/productions/IProduction';
import { serviceAddProduction } from '../data/services/ProductionService';
import { IUpsertProductionResponse } from '../data/interfaces/productions/IUpsertProduction';

interface Props extends RouteComponentProps{};

interface IProductionPage{
	rawContent:IProduction[],
	snackbar:{
		isShown:boolean,
		severity:string,
		message:[]
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

const colName: string[] = ["NUM","ID", "ITEM CODE", 
"PRODUCER", "PRODUCTION DATE", "QUANTITY", "ACTION"]

const TITLE = "Production"

const initProduction={
	id:'',
	itemCode:'',
	producer:'',
    productionDate:'',
    quantity:0
}

export class ProductionPage extends React.Component<Props,any> {
	
	state:IProductionPage;
	constructor(props:Props){
		super(props);
		this.state = {
			rawContent:[],
			snackbar:{
				isShown:false,
				severity:"info",
				message:[]
			},
			addDialog:{
				isShown:false,
				usingAction:false,
				title:"Add new "+TITLE,
				content:(
					<ProductionForm
						isEdit = {false}
						submitData = {this.addData}
						item= {
							initProduction
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
				message:[]
			}
		});
	}
	
	deleteConfirm = (isYes:boolean, key:string) => {
		if(isYes) this.deleteCustomer(key);
	}

	addData = async (data:IProduction) => {
        console.log("posting insert production request")
		await serviceAddProduction(data).subscribe(
			(res:IUpsertProductionResponse) => {
				if(res.data['status'] == HTTPCallStatus.Success){
					this.loadAll()
                }
                console.log(res.data['message'])
				this.setState({
					snackbar:{
						isShown:true,
						severity: ((res.data['status'] == HTTPCallStatus.Success) ? "success" : "error"),
						message:res.data['message']
					}
                })
                console.log(this.state.snackbar.isShown,"snackbar on")
			},
			(err)=>{
                console.log("add "+TITLE+" err:"+err);
                console.log(typeof err.message)
                console.log(typeof err.message.split())
                console.log(err.message.split())
				this.setState({
					snackbar:{
						isShown:true,
						severity:"error",
						message:err.message.split()
					}
				})
			}
		)
		this.setState({
			addDialog:{
				isShown:false,
				content:(
					<ProductionForm
						submitData = {this.addData}
						item={initProduction}
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
					this.loadAll()
				}
				this.setState({
					snackbar:{
						isShown:true,
						severity: ((res.data['status'] == HTTPCallStatus.Success) ? "success" : "error"),
						message:res.data['message'].split().split()
					}
				})
			},
			(err)=>{
				console.log("edit item err:"+err);
				this.setState({
					snackbar:{
						isShown:true,
						severity:"error",
						message:err
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
						message:res.data['message'].split()
					}
				})
			},
			(err)=>{
				console.log("delete customer err:"+err);
				this.setState({
					snackbar:{
						isShown:true,
						severity:"error",
						message:err
					}
				})
			}
		);
	}

	loadAll = async () => {
		console.log("posting index request "+TITLE)
		await serviceIndexProduction().subscribe(
			(res) => {
				console.log("RES:"+Object.keys(res).length);
				console.log(res.data["productions"]);
				this.setState({
					rawContent: res.data["productions"]
				});
				console.log("STATE:"+Object.keys(this.state.rawContent).length);
			},
			(err)=>{
				console.log("axios err:"+err);
			}
		);
	}

	async componentDidMount(){
		this.loadAll();
	}

	render(){
		return (
			<Dashboard 
			titlePage = {TITLE}			
			content={
				<div>
					<div>
						{
							this.state.snackbar.isShown &&
							(<CustomizedSnackbars
								severity={this.state.snackbar.severity}
								msg={this.state.snackbar.message}
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
							(c:IProduction, idx:number) => {
								// console.log("TOTABLE:"+c.itemCode)
								return(
									<React.Fragment>
									<tr>
										<td>{idx+1}</td>
										<td>{c.id}</td>
										<td>{c.itemCode}</td>
										<td>{c.producer}</td>
										<td>{c.productionDate}</td>
										<td>{c.quantity}</td>
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
														<ProductionForm
															isEdit = {true}
															submitData = {this.editItem}
															item={
																{
																	id:c.id,
																	itemCode:c.itemCode,
																	producer:c.producer,
                                                                    productionDate:c.productionDate,
                                                                    quantity:c.quantity
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
												dialogTitle={"This following "+ TITLE+" will be deleted"}
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
