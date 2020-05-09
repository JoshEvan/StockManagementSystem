import React, { Component } /*,{ CSSProperties }*/ from 'react';
import EditIcon from '@material-ui/icons/Edit';
import { IconButton } from '@material-ui/core';
import AddBoxIcon from '@material-ui/icons/AddBox';
import Button from '@material-ui/core/Button';
// import { Table } from '../components/organisms';
import { Table } from 'reactstrap';
import { IItem } from '../../data/interfaces';

type Props = {
  test:string,
  header:string[],
  body:any
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

export class CustomTable extends React.Component<Props,any> {
  // constructor(props:IProp) {
  //   super(props);
  // }
  render() {
    return (
        <Table hover responsive bordered>
            <thead>
              <tr>
                {this.props.header.map(
                  (h) => {
                    {console.log(h)}
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