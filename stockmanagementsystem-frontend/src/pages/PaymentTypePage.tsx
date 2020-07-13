import React from 'react'
import { RouteComponentProps } from 'react-router-dom';
import { Dashboard } from '../components/template/Dashboard';
import { CustomTable, AlertDialog, CustomizedSnackbars } from '../components/organism';
import { HTTPCallStatus, IUpsertItemResponse, ICRUDResponse} from '../data/interfaces';
import "regenerator-runtime/runtime.js";
import { PayTypeForm } from '../components/organism/form';
import { IUpsertProductionResponse } from '../data/interfaces/productions/IUpsertProduction';
import { IPayemntType } from '../data/interfaces/paymentTypes/IPaymentType';
import { serviceAddPayType, serviceEditPayType, serviceDeletePayType, serviceIndexPaymentType } from '../data/services';

interface Props extends RouteComponentProps{};

interface IPaymentTypePage{
	rawContent:IPayemntType[],
	snackbar:{
		isShown:boolean,
		severity:string,
		message:string[]
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

const colName: string[] = ["NUM","ID", "PAYMENT TYPE","ACTION"]

const TITLE = "Payment Type"

const initPaymentType={
	id:'',
	paymentType:''
}

export class PaymentTypePage extends React.Component<Props,any> {
	
	state:IPaymentTypePage;
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
					<PayTypeForm
						isEdit = {false}
						submitData = {this.addData}
						item= {
							initPaymentType
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
		if(isYes) this.deleteData(key);
	}

	addData = async (data:IPayemntType) => {
        console.log("posting insert production request")
		await serviceAddPayType(data).subscribe(
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
					<PayTypeForm
						submitData = {this.addData}
						item={initPaymentType}
					/>
				)
			}
		})
	}

	editData = async (data:IPayemntType) => {
		await serviceEditPayType(data).subscribe(
			(res:IUpsertItemResponse) => {
				if(res.data['status'] == HTTPCallStatus.Success){
					this.loadAll()
				}
				this.setState({
					snackbar:{
						isShown:true,
						severity: ((res.data['status'] == HTTPCallStatus.Success) ? "success" : "error"),
						message:res.data['message']
					}
				})
			},
			(err)=>{
				console.log("edit item err:"+err);
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
			editDialog:{isShown:false}
		})
	}

	deleteData = async (key:string) => {
		await serviceDeletePayType(key).subscribe(	
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
						message:res.data['message']
					}
				})
			},
			(err)=>{
				console.log("delete payment type err:"+err);
				this.setState({
					snackbar:{
						isShown:true,
						severity:"error",
						message:err.message.split()
					}
				})
			}
		);
	}

	loadAll = async () => {
		console.log("posting index request "+TITLE)
		await serviceIndexPaymentType().subscribe(
			(res) => {
				console.log("RES:"+Object.keys(res).length);
				console.log(res.data["paymentTypes"]);
				this.setState({
					rawContent: res.data["paymentTypes"]
				});
				console.log("STATE:"+Object.keys(this.state.rawContent).length);
			},
			(err)=>{
				console.log("axios err:"+err);
				this.setState({
					snackbar:{
						isShown:true,
						severity:"error",
						message:err.message.split()
					}
				})
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
							(c:IPayemntType, idx:number) => {
								// console.log("TOTABLE:"+c.itemCode)
								return(
									<React.Fragment>
									<tr>
										<td>{idx+1}</td>
										<td>{c.id}</td>
										<td>{c.paymentType}</td>
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
														<PayTypeForm
															isEdit = {true}
															submitData = {this.editData}
															item={
																{
																	id:c.id,
																	paymentType:c.paymentType
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
