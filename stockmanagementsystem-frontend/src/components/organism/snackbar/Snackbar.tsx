import React from "react";
import { makeStyles, Theme, createStyles } from "@material-ui/core/styles";
import Alert from "@material-ui/lab/Alert";
import IconButton from "@material-ui/core/IconButton";
import Collapse from "@material-ui/core/Collapse";
import CloseIcon from "@material-ui/icons/Close";

const useStyles = makeStyles((theme: Theme) =>
createStyles({
	root: {
			width: "100%",
					"& > * + *": {
					marginTop: theme.spacing(2)
					}
			}
	})
);

export function CustomizedSnackbars(props:any) {
	const classes = useStyles();
	const [open, setOpen] = React.useState(true);

	// {{console.log("snackbar")
	// console.log(props.msg)
	// console.log(typeof props.msg)}}

	return (
		<div className={classes.root}>
			<Collapse in={open}>
				<Alert
				severity={props.severity}
				action={
					<IconButton
						aria-label="close"
						color="inherit"
						size="small"
						onClick={() => {
						setOpen(false);
						props.parentCallback();
						}}
					>
						<CloseIcon fontSize="inherit" />
					</IconButton>
					}>
						<ul>
							{
								props.msg.map(
								(e:string) => {
									return (<li>{e}</li>);
								})
							}
						</ul>
						
				</Alert>
			</Collapse>
		</div>
	);
}