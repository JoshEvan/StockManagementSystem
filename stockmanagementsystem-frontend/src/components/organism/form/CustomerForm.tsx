import React from 'react';
import { Formik, Field, useField, FieldAttributes, FieldArray } from 'formik';
import { TextField, Button, Checkbox, Radio, Select, MenuItem, TextareaAutosize, Typography } from '@material-ui/core';
import * as yup from 'yup';


const validationSchema = yup.object({
	id: yup.string().required("Customer Id must be filled"),
	name: yup.string().required("Customer Name must be filled")
})

const TextFieldWValidation:React.FC<FieldAttributes<{}>> = ({placeholder,type,...props}) => {
	const [field, meta] = useField<{}>(props); // hook dari formik
	const errorText = meta.error && meta.touched ? meta.error : "";
	// kalo ada error dan udah diiisi/ disentuh
	return(
		<TextField label={placeholder} type={type}
			{...field} helperText={errorText} error={!!errorText} variant="outlined" />
	)
}


const TextAreaWValidation:React.FC<FieldAttributes<{}>> = ({placeholder,...props}) => {
	const [field, meta] = useField<{}>(props); // hook dari formik
	const errorText = meta.error && meta.touched ? meta.error : "";
	// kalo ada error dan udah diiisi/ disentuh
	return(
		
		<TextareaAutosize aria-label={placeholder} rowsMin={3} placeholder={placeholder} {...field} 
		helperText={errorText} error={!!errorText} />
	)
}


export class CustomerForm extends React.Component<any,any>{
	render(){
		return (
			<div>
				{/* formik punya 2 props, (initstate && fgsi onSubmit ketika submit)
				onSubmit ada 2 param data form, object
				*/}
				<Formik
					initialValues={{
						id:this.props.item.id,
						description:this.props.item.description,
						name:this.props.item.name,
                        contact:this.props.item.contact,
					}}
					
					onSubmit = {(data, { setSubmitting }) => {
						// setSubmitting itu untuk async request ke server dan sembari disable button (ini function)
						setSubmitting(true)
						console.log(data);

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
								placeholder="customer id"
								name="id" 
								type="input" 
								as={TextField}/>
						</div>}

						<div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="customer name"
								name="name" 
								type="input" 
								as={TextField}/>
						</div>

						<div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="customer contact"
								name="contact" 
								type="input" 
								as={TextField}/>
						</div>
						
						<div style={{padding:'2%'}}>
							<TextAreaWValidation
								placeholder="customer description"
								name="description"
								type="input"
								// as={TextareaAutosize}
								/>
						</div>
						
						<Button disabled={isSubmitting} type="submit">
							{/* diable button saat nunggu request */}
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