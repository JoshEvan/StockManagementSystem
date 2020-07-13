import React from 'react';
import { Formik, Field, useField, FieldAttributes, FieldArray } from 'formik';
import { TextField, Button, Checkbox, Radio, Select, MenuItem, TextareaAutosize, Typography, FormControl, InputLabel, Container, Grid, Paper, Snackbar } from '@material-ui/core';
import * as yup from 'yup';
import CSS from 'csstype';
import { ILoginRequest } from '../data/interfaces';
import { serviceLogin } from '../data/services';
import jwt_decode from 'jwt-decode';
import { Redirect, useHistory, withRouter } from 'react-router-dom';
import { CustomizedSnackbars } from '../components/organism';

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
	
	submitLogin = async (data: ILoginRequest)  => {
		await serviceLogin(data).subscribe(
			(res) => {
				// console.log("result of login")
				// console.log(res)
				// console.log(res.headers)
				// console.log(res.headers["authorization"])

				var JWTToken = res.headers["authorization"].replace('Bearer ','')
				// var user = jwt_decode(JWTToken)
				// console.log(user)

				localStorage.setItem("JWT",JWTToken)
				this.closeSnackbar()
				this.setState({pass:true})
				location.reload() // preventing error pass state true when log out then log in back without refresh page
			},
			(err) => {
				console.log(typeof err.response.status)
				console.log(err.response.status === 403)
				if(err.response.status === 403){
					console.log("in")
					this.setState({
						snackbar:{
							isShown:true,
							msg:("invalid username and password").split(),
							severity:"error"
						}
					})
					console.log(this.state.snackbar.isShown)
				}
			}
		)
	}

	closeSnackbar = () => {
		this.setState({
			snackbar:{
				isShown:false,
				severity:"info",
				msg:[]
			},
			pass:false
		});
	}

	constructor(props){
		super(props)
		this.state={
			snackbar:{
				isShown:false,
				msg:"",
				parentCallback:this.closeSnackbar()
			}
		}
	}

	render(){
		if(localStorage.getItem("JWT")){
			// for preventin 1 user, relogin, and also refreshing page, so react will read the newest localStorage of JWT
			return  <Redirect to="/" />
		}
		if(this.state.pass){
			// location.reload()
			return <Redirect to="/" />
		}
		
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
						{this.state.snackbar.isShown && 
							<span style={{width:"fit-content"}}>
								<CustomizedSnackbars
								severity={this.state.snackbar.severity}
								msg={this.state.snackbar.msg}
								parentCallback={this.closeSnackbar}
								/>
							</span>
						}
							<Formik
								initialValues={{
										username:'',
										password: ''
								}}
								
								onSubmit = {(data, { setSubmitting }) => {
										setSubmitting(true)
										// console.log(data);
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
