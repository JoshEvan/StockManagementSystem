import React from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import useMediaQuery from '@material-ui/core/useMediaQuery';
import { useTheme } from '@material-ui/core/styles';

export function ResponsiveDialog(props:any) {
	const [open, setOpen] = React.useState(true);
	const theme = useTheme();

	const fullScreen = useMediaQuery(theme.breakpoints.down('sm'));

		const handleClose = (isYes:boolean = false) => {
			// setOpen(false);
			if(!props.usingAction) props.parentCallbackClose();
			else props.parentCallbackClose(isYes);
		};

		return (
			<div>
				<Dialog
				fullScreen={fullScreen}
				open={true}
				onClose={handleClose}
				aria-labelledby="responsive-dialog-title"
				>
					<DialogTitle id="responsive-dialog-title">{props.dialogTitle}</DialogTitle>
					<DialogContent>
						{props.dialogContent}
					</DialogContent>

					{(props.usingAction && 
					<DialogActions>
						<Button autoFocus onClick={() => handleClose(false)} color="primary">
							{props.dialogNo}
						</Button>
						<Button onClick={() =>handleClose(true)} color="primary" autoFocus>
							{props.dialogYes}
						</Button>
					</DialogActions>)}
				</Dialog>
			</div>
	);
}