import React from 'react';
import { Formik, Field, useField, FieldAttributes, FieldArray } from 'formik';
import { TextField, Button, Checkbox, Radio, Select, MenuItem, TextareaAutosize, Typography, FormControl, InputLabel } from '@material-ui/core';
import * as yup from 'yup';
import { serviceIndexItem, serviceIndexCustomer, serviceIndexPaymentType } from '../../../data/services';
import { IItem, ICustomer, ITransactionDetail, initTransactionDetail } from '../../../data/interfaces';
import { IPayemntType } from '../../../data/interfaces/paymentTypes/IPaymentType';
import { CustomTable, AlertDialog } from '..';
import { TransactionDetailForm } from './TransactionDetailForm';


const validationSchema = yup.object({
	id: yup.string().required("Transaction Id must be filled"),
    customerId: yup.string().required("Customer Id must be filled"),
    paymentId: yup.string().required("Payment Id must be filled"),
    // paymentStatus:yup.string().required("Payment ")
    itemCode: yup.string().required("Item Code must be filled"),
    transactionDate: yup.string().required("Transaction Date must be filled"),
    quantity: yup.string().required("Produced Quantity must be filled"),
})

const TextFieldWValidation:any = ({placeholder,type,...props}) => {
    const [field, meta] = useField<{}>(props); 
	const errorText = meta.error && meta.touched ? meta.error : "";
	return(
		<TextField label={placeholder} type={type} InputLabelProps={props.InputLabelProps}
			{...field} helperText={errorText} error={!!errorText} variant="outlined" />
	)
}

const SelectWValidation:any = ({...props}) => {
    const [field, meta] = useField<{}>(props); 
    const errorText = meta.error && meta.touched ? meta.error : "";
    console.log(meta, "METAFROMCONTROL");
	return(
		<Select {...props}
			{...field} helperText={errorText} error={!!errorText} variant="outlined" />
	)
}

const detailColName: string[] = [
	"NUM","ITEM CODE","QTY","NOTE","ACTION"
]

interface ITransactionForm{
	itemCodes:string[],
	customerDatas:string[],
	paymentTypes:string[],
	transactionDetails:ITransactionDetail[],
	addDialog:{
		isShown:boolean,
		usingAction:boolean,
		title:string,
		content:any,
		dialogYes:string,
		dialogNo:string
	},
	editDialog:{
		isShown:boolean
	}
}

export class TransactionForm extends React.Component<any,any>{
	state:ITransactionForm;
    constructor(props){
        super(props)
        this.state={
            itemCodes:[],
            customerDatas:[],
			paymentTypes:[],
			transactionDetails:[],
			addDialog:{
				isShown:false,
				usingAction:false,
				title:"Add new transaction detail",
				content:(
					<TransactionDetailForm
						submitData = {this.passDetailState}
						item= {
							initTransactionDetail
						}
						itemCodes={[]}
					/>
				),
				dialogYes:"yes",
				dialogNo:"no"
			},
			editDialog:{
				isShown:false
			}
        }
    }

    componentDidMount(){
        this.loadAllItemCodes()
        this.loadAllCustomers()
        this.loadAllPayTypes()
	}
	
	passDetailState = (data:ITransactionDetail[]) => {
		console.log("pass detail state")
		this.setState({
			transactionDetails:this.state.transactionDetails.concat(data)
		})
		console.log(this.state.transactionDetails)
	}

    loadAllItemCodes = () => {
        console.log("posting index request items")
		serviceIndexItem({sortByAmountIncome:0,
            sortByItemCode:0,
            sortByAmountSold:0}).subscribe(
			(res) => {
				console.log(res.data["items"]);
				this.setState({
					itemCodes: res.data["items"].map(
                        (e :IItem) => {
                            return e.itemCode
                        }
                    )
                });
                console.log(this.state.itemCodes)
				this.setAddDialog();
			},
			(err)=>{
				console.log("axios err:"+err);
			}
		);
    }

    loadAllCustomers = () => {
        console.log("posting index request customers")
		serviceIndexCustomer().subscribe(
			(res) => {
				console.log(res.data["customers"]);
				this.setState({
					customerDatas: res.data["customers"].map(
                        (e :ICustomer) => {
                            return e.id// + " - " + e.name
                        }
                    )
                });
                console.log(this.state.itemCodes)
			},
			(err)=>{
				console.log("axios err:"+err);
			}
		);
    }

    loadAllPayTypes= () => {
        console.log("posting index request pay types")
		serviceIndexPaymentType().subscribe(
			(res) => {
				console.log(res.data["paymentTypes"]);
				this.setState({
					paymentTypes: res.data["paymentTypes"].map(
                        (e :IPayemntType) => {
                            return e.id// + " - " + e.paymentType
                        }
                    )
                });
                console.log(this.state.itemCodes)
			},
			(err)=>{
				console.log("axios err:"+err);
			}
		);
	}
	
	setAddDialog = () => {
		this.setState({
			addDialog:{
				isShown:false,
				usingAction:false,
				title:"Add new transaction detail",
				content:(
					<TransactionDetailForm
						submitData = {this.passDetailState}
						item= {
							initTransactionDetail
						}
						itemCodes = {this.state.itemCodes}
					/>
				)
			}
		})
	}

	deleteData = (key:string) => {
		var array = [...this.state.transactionDetails];
		var pos = array.map((e :ITransactionDetail)=>{return e.itemCode}).indexOf(key)
		if (pos !== -1) {
			array.splice(pos, 1);
			this.setState({transactionDetails: array});
		}
	}

	deleteConfirm = (isYes:boolean, key:string) => {
		if(isYes) this.deleteData(key);
	}

	render(){
		return (
			<div>
				
				<Formik
					initialValues={{
						id:this.props.item.id,
						itemCode:this.props.item.itemCode,
                        customerId:this.props.item.customerId,
                        paymentId:this.props.item.paymentId,
                        paymentStatus:this.props.item.paymentStatus,
                        note:this.props.item.note,
						transactionDate:this.props.item.transactionDate,
						quantity:this.props.item.quantity
					}}
					onSubmit = {(data, { setSubmitting }) => {
						setSubmitting(true)
						data["test"] = "test"
						console.log(data);
						console.log("SUBMITTING")

						this.props.submitData(data, this.state.transactionDetails);
						
						setSubmitting(false);
						console.log("done submit add data")
					}}
					validationSchema = {validationSchema}>
					{
					({ errors, values, isSubmitting, /*handleChange, handleBlur, */handleSubmit }) => (
					
					<form onSubmit={handleSubmit}>
					

						{!this.props.isEdit && <div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="transaction id"
								name="id" 
								type="input" 
								as={TextField}/>
						</div>}

                        <div style={{padding:'2%'}}>   
                            <FormControl
                                variant="outlined" style={{width:"100%"}}
                                >
                                <InputLabel htmlFor="outlined-age-native-simple">buyer customer</InputLabel>
                                
                                <SelectWValidation
									// native
									inputProps={{ displayEmpty:true}}
									// value={data.itemCode}
									name="customerId"
									// onChange={handleChange}
									label="buyer customer"
                                >
                                <option aria-label="None" value=""/>
                                {
                                this.state.customerDatas.map(
                                    (e:string) => {
                                        return <option value={e}
										selected={
											(e === this.props.item.customerId && this.props.isEdit)
										}
										>{e}</option>
                                    }
                                )}
                                
                               
                                </SelectWValidation>
                            </FormControl>
                        </div>
                        <div style={{padding:'2%'}}>   
                            <FormControl
                                variant="outlined" style={{width:"100%"}}>
                                <InputLabel htmlFor="outlined-age-native-simple">payment types</InputLabel>
                                <SelectWValidation
									// native
									inputProps={{ displayEmpty:true}}
									// value={data.itemCode}
									name="paymentId"
									// onChange={handleChange}
									label="payment types"
                                >
                                <option aria-label="None" value=""/>
                                {
                                this.state.paymentTypes.map(
                                    (e:string) => {
                                        return <option value={e}
										selected={
											(e === this.props.item.paymentType && this.props.isEdit)
										}
										>{e}</option>
                                    }
                                )}
                                </SelectWValidation>
                            </FormControl>
                        </div>

                        <div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="payment status"
								name="paymentStatus"
                                type="input"
                                as={TextField}
								/>
						</div>

                        <div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="transaction note"
								name="note"
                                type="input"
                                as={TextField}
								/>
						</div>

						<div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="transaction date"
								name="transactionDate" 
								type="date" 
								as={TextField}
								InputLabelProps={{
										shrink: true,
								}}/>
						</div>
						<CustomTable
							addDialog = {this.state.addDialog}
							header={detailColName}
							body={
								this.state.transactionDetails.map(
									(e:ITransactionDetail, idx:number) => {
										return (
											<React.Fragment>
												<tr>
													<td>{idx+1}</td>
													<td>{e.itemCode}</td>
													<td>{e.quantity}</td>
													<td>{e.note}</td>
													<td>{
														<React.Fragment>
															<AlertDialog
																color="primary"
																param={e.itemCode}
																parentAllowance = {this.state.editDialog.isShown}
																buttonTitle="edit"
																parentCallbackOpen={()=>this.setState({editDialog:{isShown:true}})}
																dialogTitle="Update item"
																usingAction={false}
																dialogContent={
																	<TransactionForm
																		// submitData = {this.editItem}
																		// item={
																		// 	// {
																		// 	// 	itemCode:c.itemCode,
																		// 	// 	name:c.name,
																		// 	// 	price:c.priceDec,
																		// 	// 	stock:c.stock,
																		// 	// 	capacity:c.capacity,
																		// 	// 	description:c.description,
																		// 	// }
																		// }
																	/>
																}
															/>
															<AlertDialog
																color="secondary"
																usingAction={true}
																parentAllowance = {true}
																param={e.itemCode}
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
										)
									}
								)
							}
						/>
						
						<Button disabled={isSubmitting} type="submit">
							submit
						</Button>
					</form>
					)}
				</Formik>
			</div>
		)
	}
}