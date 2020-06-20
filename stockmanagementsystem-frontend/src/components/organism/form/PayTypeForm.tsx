import React from 'react';
import { Formik, Field, useField, FieldAttributes, FieldArray } from 'formik';
import { TextField, Button, Checkbox, Radio, Select, MenuItem, TextareaAutosize, Typography, FormControl, InputLabel } from '@material-ui/core';
import * as yup from 'yup';
import { serviceIndexItem } from '../../../data/services';
import { IItem } from '../../../data/interfaces';

const validationSchema = yup.object({
	id: yup.string().required("Production Id must be filled"),
    paymentType: yup.string().required("Payment Type Name must be filled"),
})

const TextFieldWValidation:any = ({placeholder,type,...props}) => {
    const [field, meta] = useField<{}>(props); 
	const errorText = meta.error && meta.touched ? meta.error : "";
	return(
		<TextField label={placeholder} type={type} InputLabelProps={props.InputLabelProps}
			{...field} helperText={errorText} error={!!errorText} variant="outlined" />
	)
}

export class PayTypeForm extends React.Component<any,any>{
	render(){
		return (
			<div>
				
				<Formik
					initialValues={{
						id:this.props.item.id,
						paymentType: this.props.item.padding
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
								placeholder="payment type id"
								name="id" 
								type="input" 
								as={TextField}/>
						</div>}

						<div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="payment type name"
								name="paymentType"
                                type="input"
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