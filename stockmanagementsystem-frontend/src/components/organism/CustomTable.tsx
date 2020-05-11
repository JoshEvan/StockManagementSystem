import React, { Component } /*,{ CSSProperties }*/ from 'react';
import EditIcon from '@material-ui/icons/Edit';
import { IconButton } from '@material-ui/core';
import AddBoxIcon from '@material-ui/icons/AddBox';
import Button from '@material-ui/core/Button';
// import { Table } from '../components/organisms';
import { Table } from 'reactstrap';
import { IItem } from '../../data/interfaces';
import { ResponsiveDialog } from './Dialog';

type Props = {
  header:string[],
  body:any,
  addDialog:{
    isShown:boolean,
    title:string,
    content:any,
    usingAction:boolean,
    parentCallback:any,
    dialogNo:string,
    dialogYes:string
  }
};

// interface IFeatures{
//   features:any;
//   snackbar:{
//     isShown:boolean,
//     severity:string,
//     msg:string[]
//   },
//   showDetail:{
//     isShown:boolean,
//     title:string,
//     body:any
//   },
//   showAddDialog:{
//     isShown:boolean
//   }
// };

interface IState{
  addDialog:{
    isShown:boolean
  }
}
export class CustomTable extends React.Component<Props,IState> {

  state:IState = {
    addDialog:{
      isShown:false
    }
  }

  openAddDialog = () => {
    console.log("OPENNNN")
    this.setState({
      addDialog:{
        isShown:true
      }
    })
  }

  render() {
    return (
        <React.Fragment>
          <div>
            {
              (this.state.addDialog.isShown) &&
              (
                <ResponsiveDialog
                  dialogTitle={this.props.addDialog.title}
                  dialogContent={this.props.addDialog.content}
                  usingAction={this.props.addDialog.usingAction}
                  parentCallback={this.props.addDialog.parentCallback}
                  dialogNo={this.props.addDialog.dialogNo}
                  dialogYes={this.props.addDialog.dialogYes}
                  
                />
              )
            }
          </div>
          <IconButton color="primary" 
          style={{float:'right'}}
          onClick={
            () => {this.openAddDialog()}
          }>
            <AddBoxIcon/>
          </IconButton>
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