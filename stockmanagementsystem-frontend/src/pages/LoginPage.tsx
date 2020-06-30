import React from 'react';
import { Formik, Field, useField, FieldAttributes, FieldArray } from 'formik';
import { TextField, Button, Checkbox, Radio, Select, MenuItem, TextareaAutosize, Typography, FormControl, InputLabel, Container, Grid, Paper } from '@material-ui/core';
import * as yup from 'yup';
import CSS from 'csstype';
import { ILoginRequest } from '../data/interfaces';

const validationSchema = yup.object({
	username: yup.string().required("Username must be filled"),
	password: yup.string().required("Password must be filled"),
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

	submitLogin = (data: ILoginRequest) => {
		
	}

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
													username:'',
													password: ''
											}}
											
											onSubmit = {(data, { setSubmitting }) => {
													setSubmitting(true)
													console.log(data);
													console.log("SUBMITTING")

													this.submitLogin(data);
													
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
																placeholder="username"
																name="username" 
																type="input" 
																as={TextField}/>
												</div>}
												<div style={{padding:'2%'}}>
														<TextFieldWValidation
																placeholder="password"
																name="password"
																type="password"
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