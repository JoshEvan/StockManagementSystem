import React from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';

export function AlertDialog(props:any) {
	const [open, setOpen] = React.useState(false);

	const handleClickOpen = () => {
		setOpen(true);
		console.log(open+"_"+props.parentAllowance)
		props.parentCallbackOpen();
	};

	const handleClose = (isYes:boolean) => {
		if(props.usingAction) props.parentCallback(isYes, props.param);
		setOpen(false);
	};

	return (
		<div>
				<Button variant="outlined" color={props.color} onClick={handleClickOpen}>
					{props.buttonTitle}
				</Button>
				<Dialog
					open={open && props.parentAllowance}
					onClose={() => handleClose(false)}
					aria-labelledby="alert-dialog-title"
					aria-describedby="alert-dialog-description"
				>
				<DialogTitle id="alert-dialog-title">{props.dialogTitle}</DialogTitle>
				<DialogContent>
						<DialogContentText id="alert-dialog-description">
								{props.dialogContent}
						</DialogContentText>
				</DialogContent>
				{
				(props.usingAction) &&	
				(<DialogActions>
						<Button onClick={() => handleClose(false)} color="primary">
						{props.dialogNo}
						</Button>
						<Button onClick={() => handleClose(true)} color="primary" autoFocus>
						{props.dialogYes}
				</Button>
				</DialogActions>)}
		</Dialog>
		</div>
	);
}