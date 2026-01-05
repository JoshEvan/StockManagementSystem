import React from 'react';
import { Formik, useField } from 'formik';
import { TextField, Button, Select,FormControl, InputLabel } from '@material-ui/core';
import * as yup from 'yup';


const validationSchema = yup.object({
    itemCode: yup.string().required("Item Code must be filled"),
    quantity: yup.number().positive("Produced Quantity must be more than 0"),
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

export class TransactionDetailForm extends React.Component<any,any>{
    constructor(props){
        super(props)
    }

	render(){
		return (
			<div>
				<Formik
					initialValues={{
                        updatedItemCode:this.props.item.updatedItemCode,
						itemCode:this.props.item.itemCode,
                        note:this.props.item.note,
						quantity:this.props.item.quantity,
						quantityPcs: this.props.item.quantityPcs
					}}
					onSubmit = {(data, { setSubmitting }) => {
						setSubmitting(true)
						console.log("SUBMITTING")
						data.quantity = data.quantity * 12 + data.quantityPcs;
						this.props.submitData(data);
						
						setSubmitting(false);
						console.log("done submit add data")
					}}
					validationSchema = {validationSchema}>
					{
					({ errors, values, isSubmitting, /*handleChange, handleBlur, */handleSubmit }) => (
					<form onSubmit={handleSubmit}>
						
                        <div style={{padding:'2%'}}>   
                            <FormControl
                                variant="outlined" style={{width:"100%"}}>
                                <InputLabel htmlFor="outlined-age-native-simple">item codes</InputLabel>
                                <SelectWValidation
									// native
									inputProps={{ displayEmpty:true}}
									name="itemCode"
									label="item codes"
                                >
                                <option aria-label="None" value=""/>
                                {
                                this.props.itemCodes.map(
                                    (e:string) => {
                                        return <option value={e}
										selected={
											(e === this.props.item.itemCode && this.props.isEdit) // ?
										}
										>{e}</option>
                                    }
                                )}
                                </SelectWValidation>
                            </FormControl>
                        </div>

						<div style={{padding:'2%', display:'flex', alignItems:'center', gap: '8px'}}>
							<div style={{flex: 1, width: '50%'}}>
							<TextFieldWValidation
								placeholder="item quantity (dzs)"
								name="quantity"
								type="number"
								as={TextField}
								/>
							</div>
							<span style={{fontWeight: 700}}>+</span>
							<div style={{flex: 1, width: '50%'}}>
							<TextFieldWValidation
								placeholder="item quantity (remainder pcs)"
								name="quantity"
								type="number"
								as={TextField}
								/>
							</div>
						</div>

                        <div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="detail note"
								name="note"
                                type="input"
                                as={TextField}
								/>
						</div>
						
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