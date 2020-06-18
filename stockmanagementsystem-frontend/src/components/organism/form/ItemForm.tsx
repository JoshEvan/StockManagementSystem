import React from 'react';
import { Formik, Field, useField, FieldAttributes, FieldArray } from 'formik';
import { TextField, Button, Checkbox, Radio, Select, MenuItem, TextareaAutosize, Typography } from '@material-ui/core';
import * as yup from 'yup';
import { IAddItemRequest } from '../../../data/interfaces';


const validationSchema = yup.object({
// key: rule
	// firstName: yup.string().required("error message (ada default juga kalo dikosongin)").max(10),
	itemCode: yup.string().required("Item Code must be filled"),
	name: yup.string().required("Item Name must be filled"),
	price: yup.string().required("Item Price must be filled"),
	stock: yup.string().required("Item Stock must be filled"),
	capacity: yup.string().required("Item Capacity must be filled")
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
		
		<TextareaAutosize aria-label={placeholder} rowsMin={3} placeholder={placeholder} {...field} 
		helperText={errorText} error={!!errorText} />
		// <TextField placeholder={placeholder} {...field} helperText={errorText} error={!!errorText}/>
		// ... field sebarin kaya values,onchange, dll (kaya paste in aja)
		// error={!!errorText} ubah T jadi F dan sebaliknya
	)
}


export class ItemForm extends React.Component<any,any>{
	render(){
		return (
			<div>
				{/* formik punya 2 props, (initstate && fgsi onSubmit ketika submit)
				onSubmit ada 2 param data form, object
				*/}
				<Formik
					initialValues={{
						itemCode:this.props.item.itemCode,
						description:this.props.item.description,
						name:this.props.item.name,
						price:this.props.item.price,
						stock:this.props.item.stock,
						capacity:this.props.item.capacity,
						errors:'',
						values:''
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
								name="name" 
								type="input" 
								as={TextField}/>
						</div>

						<div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="item price"
								name="price" 
								type="number" 
								as={TextField}/>
						</div>
						
						<div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="item stock"
								name="stock" 
								type="number"
								as={TextField}/>
						</div>

						
						<div style={{padding:'2%'}}>
							<TextFieldWValidation
								placeholder="item capacity dozen/box"
								name="capacity" 
								type="number" 
								as={TextField}/>
									
						</div>

						<div style={{padding:'2%'}}>
							<TextAreaWValidation
								placeholder="item description"
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