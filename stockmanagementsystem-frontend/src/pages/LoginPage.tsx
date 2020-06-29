import React from 'react';
import { Formik, Field, useField, FieldAttributes, FieldArray } from 'formik';
import { TextField, Button, Checkbox, Radio, Select, MenuItem, TextareaAutosize, Typography, FormControl, InputLabel, Container, Grid, Paper } from '@material-ui/core';
import * as yup from 'yup';
import CSS from 'csstype';
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

const coloredBg:CSS.Properties = {
	background: 'linear-gradient(to right, #03478c, #008033)',
	width:'100%',
	height:'100vh',
	margin:'0'
}

export class LoginPage extends React.Component<any,any>{
	render(){
		return (
			<div style={coloredBg}>
				<Container  style={{paddingTop:'15%',textAlign:'center'}}>
								<Paper elevation={3} style={{padding:'2%'}}>
								<Typography variant="h4" component="h2">
										Sign in
								</Typography>
								<Typography variant="overline" display="block" gutterBottom>
									Joseph	
								</Typography>
										<Formik
											initialValues={{
													// id:this.props.item.id,
													// paymentType: this.props.item.padding
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
												<Button disabled={isSubmitting} type="submit">
														sign in
												</Button>
										</form>
										)}
								</Formik>
							</Paper>
				</Container>
			</div>
		)
	}
}