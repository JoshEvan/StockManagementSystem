import React, { Component } /*,{ CSSProperties }*/ from 'react';
import EditIcon from '@material-ui/icons/Edit';
import { IconButton } from '@material-ui/core';
import AddBoxIcon from '@material-ui/icons/AddBox';
import Button from '@material-ui/core/Button';
// import { Table } from '../components/organisms';
import { Table } from 'reactstrap';
import { IItem } from '../../data/interfaces';
import { ResponsiveDialog } from './dialog';

type Props = {
  header:string[],
  body:any,
  isShown?:boolean,
  notShowAddButton?:boolean,
  addDialog?:{
    isShown:boolean,
    title:string,
    content:any,
    usingAction:boolean,
    dialogNo?:string,
    dialogYes?:string
  }
};

interface IState{
  isShown:boolean
}
export class CustomTable extends React.Component<Props,IState> {

  state:IState = {
    isShown:false
  }

  componentWillReceiveProps(newProps) {
      this.setState({isShown: newProps.isShown});
  }

  openAddDialog = () => {
    console.log("OPENNNN")
    // console.log(this.state.addDialog.isShown)
    // this.props.addDialog.parentCallbackClose()
    this.setState({
      isShown:true
    })
  }

  
	closeAddDialog = (isYes:Boolean) => {
		this.setState({
      isShown:false
		});
	}

  render() {
    return (
        <React.Fragment>
          {/* {console.log(this.state.isShown+"STATETABLE")} */}
          <div>
            {
              (this.state.isShown) &&
              (
                <ResponsiveDialog
                  open={this.state.isShown}
                  dialogTitle={this.props.addDialog.title}
                  dialogContent={this.props.addDialog.content}
                  usingAction={this.props.addDialog.usingAction}
                  parentCallbackClose={this.closeAddDialog}
                  parentCallbackOpen={this.openAddDialog}
                  dialogNo={this.props.addDialog.dialogNo}
                  dialogYes={this.props.addDialog.dialogYes}
                />
              )
            }
          </div>
          {!this.props.notShowAddButton && <IconButton color="primary" 
          style={{float:'right'}}
          onClick={
            () => {this.openAddDialog();}
          }>
            <AddBoxIcon/>
          </IconButton>}
          <Table hover responsive bordered>
              <thead>
                <tr>
                  {this.props.header.map(
                    (h) => {
                      return(<th>{h}</th>);
                    }
                  )}
                </tr>
              </thead>
              <tbody>
                  {this.props.body.map(
                    (h: any) => {
                      console.log("INTABLE:"+h);
                      return(
                        <React.Fragment>
                          {h}
                        </React.Fragment>
                      );
                    }
                  )}
              </tbody>
            </Table>
          </React.Fragment>
    );
  }
}



// {
//   currFeatures.map( 
//     (f) => {
//       return(
//         <tr>
//           <td>{f.key}</td> 
//           <td>{f.description}</td> 
//           <td>{f.owner} </td>
//           <td>{f.flags.length}</td> 
//           <td>{f.compiledState+""}</td> 
//           <td>
//           {/* INI HARUS (f) => {...} spy bisa panggil this.openDetail */}
//           <Button variant="outlined" color="primary" onClick = {()=> this.openDetail(f)}>
//             detail
//           </Button>
//           {(this.state.showDetail.isShown && <ResponsiveDialog 
//             parentCallback={this.closeDetail}
//             dialogTitle={this.state.showDetail.title}
//             dialogContent={this.state.showDetail.body} usingAction={false}/>)}
//           <IconButton color="primary" aria-label="edit feature">
//             <EditIcon />
//           </IconButton>  
//           {/* <IconButton color="secondary" aria-label="delete feature" onClick = { () => this.deleteFeature(f.id)}>
//             <DeleteIcon />
//           </IconButton>  */}
//           <AlertDialog color={"secondary"} param={f.key} parentCallback={this.deleteConfirm} buttonTitle={"delete"} dialogTitle={"this following features key will be deleted"} dialogYes={"yes"} dialogNo={"no"} dialogContent={"are you sure?"}/>
//           </td> 
//         </tr>
//       )
//     }
//   )
// }