import React, { Component } /*,{ CSSProperties }*/ from 'react';
import EditIcon from '@material-ui/icons/Edit';
import { IconButton } from '@material-ui/core';
import AddBoxIcon from '@material-ui/icons/AddBox';
import Button from '@material-ui/core/Button';
// import { Table } from '../components/organisms';
import { Table } from 'reactstrap';
import { IItem } from '../../data/interfaces';
import { ResponsiveDialog } from './dialog';
import { DataGrid } from '@mui/x-data-grid';
import { camelize } from '../../util/string';
import { GridColumnVisibilityModel } from '@mui/x-data-grid/models/gridColumnVisibilityModel';

type Props = {
  header:string[],
  colShow:Map<string,boolean>,
  nonGrid: boolean,
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
          <div>
            {!this.props.notShowAddButton && !this.props.nonGrid && 
            // <IconButton color="primary" 
            //   // style={{ float: 'right' }}
            //   onClick={
            //     () => { this.openAddDialog(); }
            //   }>
            //   <AddBoxIcon />
              <Button variant="outlined" color={"primary"} onClick={
                () => { this.openAddDialog(); }
              }>
                {"Add new"}
              </Button>
            // </IconButton>
          }
          </div>
          {(() => {
            // Note: add `import { DataGrid } from '@mui/x-data-grid';` at top of the file
            const columns = (this.props.header || []).map((h, i) => ({
              field: `col${i}`,
              headerName: h,
              flex: 1,
              sortable: false,
              // keep whatever is in the cell (string, number or React node)
              renderCell: (params: any) => params.value,
            }));

            const rows = (this.props.body || []).map((row: any, idx: number) => {
              // React node row (was likely a <tr> or fragment previously)
              if (React.isValidElement(row)) {
                return { id: idx, col0: row };
              }

              // array of cell values
              if (Array.isArray(row)) {
                const obj: any = { id: idx };
                row.forEach((cell, i) => (obj[`col${i}`] = cell));
                return obj;
              }

              // object row: try to map header names to object properties, fallback to copying keys
              if (row && typeof row === 'object') {
                const obj: any = { id: row.id ?? idx };
                let keys = Object.keys(row);
                this.props.header.forEach((h: string, i: number) => {
                  // prefer exact header key, then lowercase, then fallback empty
                  obj[`col${i}`] = row[h] ?? row[camelize(h.toLowerCase())] ?? row[`col${i}`] ?? '';
                });
                // var index = 0;
                // row.forEach((value: any, _:any) => {
                //   if (obj[`col${index}`] === undefined) {
                //     obj[`col${index}`] = value;
                //   }
                //   index++;
                // });
                // copy other props if any (optional)
                console.log(obj);
                return obj;
              }

              // primitive value row
              return { id: idx, col0: row };
            });


            
            var colVisibilityModel: GridColumnVisibilityModel = {};
            if (this.props.colShow !== undefined) {
              this.props.header.forEach((colName: string) => {
                console.log(colName, "COLNAME");
                const colIndex = this.props.header.indexOf(colName);
                if (colIndex >= 0) {
                  colVisibilityModel[`col${colIndex}`] = this.props.colShow.get(colName) ?? false;
                }
              });
            }
            console.log(columns, "COLUMNS", this.props.colShow, colVisibilityModel, this.props.header);
            
            if (this.props.nonGrid == true) {
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
              )
            }

            return (
              <div style={{ width: '100%' }}>
                <div style={{ height: 400, width: '100%' }}>
                  <DataGrid
                    autoHeight
                    rows={rows}
                    columns={columns}
                    getRowHeight={() => 'auto'}
                    initialState={{
                      columns: {
                        columnVisibilityModel: colVisibilityModel,
                      },
                    }}
                    showToolbar
                  />
                </div>
              </div>
            );
          })()}
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