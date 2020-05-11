import React from 'react';
import { Formik, Field, useField, FieldAttributes, FieldArray } from 'formik';
import { TextField, Button, Checkbox, Radio, Select, MenuItem, TextareaAutosize, Typography } from '@material-ui/core';
import * as yup from 'yup';


const validationSchema = yup.object({
// key: rule
	// firstName: yup.string().required("error message (ada default juga kalo dikosongin)").max(10),
	itemCode: yup.string().required("Item Code must be filled"),
	itemName: yup.string().required("Item Name must be filled"),
	itemPrice: yup.string().required("Item Price must be filled"),
	itemStock: yup.string().required("Item Stock must be filled"),
	itemCap: yup.string().required("Item Capacity must be filled"),
	pets: yup.array().of(yup.object(
		{name:yup.string().required()}
	))
})

const TextFieldWValidation:React.FC<FieldAttributes<{}>> = ({placeholder,type,...props}) => {
	const [field, meta] = useField<{}>(props); // hook dari formik
	const errorText = meta.error && meta.touched ? meta.error : "";
	// kalo ada error dan udah diiisi/ disentuh
	return(
		<TextField label={placeholder} type={type}
			{...field} helperText={errorText} error={!!errorText} variant="outlined" />
		
		// ... field sebarin kaya values,onchange, dll (kaya paste in aja)
		// error={!!errorText} ubah T jadi F dan sebaliknya

	)
}


const TextAreaWValidation:React.FC<FieldAttributes<{}>> = ({placeholder,...props}) => {
	const [field, meta] = useField<{}>(props); // hook dari formik
	const errorText = meta.error && meta.touched ? meta.error : "";
	// kalo ada error dan udah diiisi/ disentuh
	return(
		
		<TextareaAutosize aria-label={placeholder} rowsMin={3} placeholder={placeholder} {...field} helperText={errorText} error={!!errorText} />
		// <TextField placeholder={placeholder} {...field} helperText={errorText} error={!!errorText}/>
		// ... field sebarin kaya values,onchange, dll (kaya paste in aja)
		// error={!!errorText} ubah T jadi F dan sebaliknya
	)
}


export class Form extends React.Component<any,any>{
	render(){
		return (
			<div>
				{/* formik punya 2 props, (initstate && fgsi onSubmit ketika submit)
				onSubmit ada 2 param data form, object
				*/}
				<Formik
					initialValues={{
					itemCode:'',
					
					isTall:false,
					lastName:"",
					food:[],
					yogurt:'',
					pets:[{ type:'cat', name:'jarvis',id:Math.random()+""}]}}
					onSubmit = {(data, { setSubmitting }) => {
						// setSubmitting itu untuk async request ke server dan sembari disable button (ini function)
						setSubmitting(true)
						console.log(data);
						setSubmitting(false);
					}}
					validationSchema = {validationSchema}
					/* // VALIDASI tanpa yup */
					// validate = {values => {
					// const errors:Record<string,string> = {};
					// if(values.firstName.includes("bob")){
					// errors.firstName = "no bob";
					// }
					// return errors;
					// }}
				>

					{/* ( ini properties dari formik yg bisa diakses ) => {} pake ini karena render prop... */}
					{
					// values ini object curr state dari form
					({ errors, values, isSubmitting, /*handleChange, handleBlur, */handleSubmit }) => (
					// display content form
					// ini dibawah sama aj kaya lgsg <Form> // import dari formik
					<form onSubmit={handleSubmit}>
						{/* <TextField
						name="firstName" // nama ini harus sejalan dengan initValues (state dari currForm)
						value={values.firstName}
						onChange={handleChange}
						onBlur={handleBlur}/> */}

						<div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="item code"
								name="itemCode" 
								type="input" 
								as={TextField}/>
						</div>

						<div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="item name"
								name="itemName" 
								type="input" 
								as={TextField}/>
						</div>

						<div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="item price"
								name="itemPrice" 
								type="number" 
								as={TextField}/>
						</div>
						
						<div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="item stock"
								name="itemStock" 
								type="number"
								as={TextField}/>
						</div>

						
						<div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="item capacity dozen/box"
								name="itemCap" 
								type="number" 
								as={TextField}/>
									
						</div>

						<div style={{padding:'2%'}}>
							<TextAreaWValidation
								placeholder="item description"
								name="itemDesc"
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