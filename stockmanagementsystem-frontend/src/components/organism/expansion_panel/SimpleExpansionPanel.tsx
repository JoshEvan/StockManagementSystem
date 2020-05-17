import React from 'react';
import { Theme, createStyles, makeStyles } from '@material-ui/core/styles';
import ExpansionPanel from '@material-ui/core/ExpansionPanel';
import ExpansionPanelSummary from '@material-ui/core/ExpansionPanelSummary';
import ExpansionPanelDetails from '@material-ui/core/ExpansionPanelDetails';
import Typography from '@material-ui/core/Typography';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    root: {
      width: '100%',
    },
    heading: {
      fontSize: theme.typography.pxToRem(15),
      fontWeight: theme.typography.fontWeightRegular,
    },
  }),
);

export function SimpleExpansionPanel(props:any) {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <ExpansionPanel elevation={4}>
        <ExpansionPanelSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel1a-content"
          id="panel1a-header"
        >
          <Typography className={classes.heading}><strong>{props.title}</strong></Typography>
        </ExpansionPanelSummary>
        <ExpansionPanelDetails>
          <Typography>
            {props.content}
          </Typography>
        </ExpansionPanelDetails>
      </ExpansionPanel>
    </div>
  );
}