import React from 'react';
import { Formik, Field, useField, FieldAttributes, FieldArray } from 'formik';
import { TextField, Button, Checkbox, Radio, Select, MenuItem, TextareaAutosize, Typography, FormControl, InputLabel } from '@material-ui/core';
import * as yup from 'yup';
import { serviceIndexItem } from '../../../data/services';
import { IItem } from '../../../data/interfaces';


const validationSchema = yup.object({
	id: yup.string().required("Production Id must be filled"),
    producer: yup.string().required("Producer Name must be filled"),
    itemCode: yup.string().required("Item Code must be filled"),
    productionDate: yup.string().required("Production Date must be filled"),
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

interface IProductionForm{
    itemCodes: string[]
}

export class ProductionForm extends React.Component<any,any>{
    constructor(props){
        super(props)
        this.state={
            itemCodes:[]
        }
    }

    componentDidMount(){
        this.loadAllItemCodes()
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
			},
			(err)=>{
				console.log("axios err:"+err);
			}
		);
    }

	render(){
		return (
			<div>
				
				<Formik
					initialValues={{
						id:this.props.item.id,
						itemCode:this.props.item.itemCode,
						producer:this.props.item.producer,
						productionDate:this.props.item.productionDate,
						quantity:this.props.item.quantity
					}}
					
					onSubmit = {(data, { setSubmitting }) => {
						setSubmitting(true)
                        console.log(data);
                        console.log("SUBMITTING")

						this.props.submitData(data);
						
						setSubmitting(false);
						console.log("done submit add data")
					}}
					
					validationSchema = {validationSchema}
					
				>
					{
					({ errors, values, isSubmitting, /*handleChange, handleBlur, */handleSubmit }) => (
					
					<form onSubmit={handleSubmit}>
					

						{!this.props.isEdit && <div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="production id"
								name="id" 
								type="input" 
								as={TextField}/>
						</div>}

                        <div style={{padding:'2%'}}>   
                            <FormControl
                                variant="outlined" style={{width:"100%"}}
                                >
                                <InputLabel htmlFor="outlined-age-native-simple">produced item code</InputLabel>
                                
                                <SelectWValidation
									// native
									inputProps={{ displayEmpty:true}}
									// value={data.itemCode}
									name="itemCode"
									// onChange={handleChange}
									label="produced item code"
                                >
                                
                                <option aria-label="None" value=""/>
                                
                                {
                                this.state.itemCodes.map(
                                    (e:string) => {
                                        return <option value={e}
										selected={
											(e === this.props.item.itemCode && this.props.isEdit)
										}
										>{e}</option>
                                    }
                                )}
                                
                               
                                </SelectWValidation>
                            </FormControl>
                        </div>
						<div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="producer name"
								name="producer" 
								type="input" 
								as={TextField}/>
						</div>

						<div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="production date"
								name="productionDate" 
								type="date" 
								as={TextField}
                                InputLabelProps={{
                                    shrink: true,
                                }}/>
						</div>
						
						<div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="produced quantity"
								name="quantity"
                                type="number"
                                as={TextField}
								/>
						</div>
						
						<Button disabled={isSubmitting} type="submit"
                        >
							submit
						</Button>

					</form>
					)
					}
				</Formik>
			</div>
		)
	}
}