import React from 'react';
import Button from '@material-ui/core/Button';
import ClickAwayListener from '@material-ui/core/ClickAwayListener';
import Grow from '@material-ui/core/Grow';
import Paper from '@material-ui/core/Paper';
import Popper from '@material-ui/core/Popper';
import MenuItem from '@material-ui/core/MenuItem';
import MenuList from '@material-ui/core/MenuList';
import { makeStyles, createStyles, Theme } from '@material-ui/core/styles';
import { Typography } from '@material-ui/core';
import AccountCircleSharpIcon from '@material-ui/icons/AccountCircleSharp';
import jwt_decode from 'jwt-decode';
import { Redirect, Link } from 'react-router-dom';

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    root: {
      display: 'flex',
      width:'fit-content'
    },
    paper: {
      marginRight: theme.spacing(2),
    },
  }),
);

export function ToggleMenu() {
  const classes = useStyles();
  const [open, setOpen] = React.useState(false);
  const anchorRef = React.useRef<HTMLButtonElement>(null);

  const handleToggle = () => {
    setOpen((prevOpen) => !prevOpen);
  };

  const logOut = () => {
    localStorage.removeItem("JWT")
  }

  const toLogin = () => {
    console.log("tologin")
    return <Redirect push to="/login"/>
  }

  const handleClose = (event: React.MouseEvent<EventTarget>) => {
    if (anchorRef.current && anchorRef.current.contains(event.target as HTMLElement)) {
      return;
    }
    logOut()
    setOpen(false);
    location.reload(); // refresh for read new empty localStorage of JWT
  };

  function handleListKeyDown(event: React.KeyboardEvent) {
    if (event.key === 'Tab') {
      event.preventDefault();
      setOpen(false);
    }
  }

  // return focus to the button when we transitioned from !open -> open
  const prevOpen = React.useRef(open);
  React.useEffect(() => {
    if (prevOpen.current === true && open === false) {
      anchorRef.current!.focus();
    }

    prevOpen.current = open;
  }, [open]);

  return (
    <div className={classes.root}>
      
      <div>
        <Button
          ref={anchorRef}
          aria-controls={open ? 'menu-list-grow' : undefined}
          aria-haspopup="true"
          onClick={handleToggle}
        >
            <span style={{color:"white",padding:"1%"}}>
                <AccountCircleSharpIcon style={{color:"white",padding:"1%"}}/>
                {/* {console.log(jwt_decode(localStorage.getItem("JWT")))} */}
                
                {localStorage.getItem("JWT") !== null && jwt_decode(localStorage.getItem("JWT")).sub}
                {localStorage.getItem("JWT") === null && "please sign in"}
                 
            </span>

        </Button>
        <Popper open={open} anchorEl={anchorRef.current} role={undefined} transition disablePortal style={{width:'fit-content'}}>
          {({ TransitionProps, placement }) => (
            <Grow
              {...TransitionProps}
              style={{ transformOrigin: placement === 'bottom' ? 'center top' : 'center bottom' }}
            >
              <Paper>
                <ClickAwayListener onClickAway={handleClose}>
                  <MenuList autoFocusItem={open} id="menu-list-grow" onKeyDown={handleListKeyDown}>
                    { localStorage.getItem("JWT") !== null && <MenuItem onClick={handleClose}>Logout Account</MenuItem>}
                    { localStorage.getItem("JWT") === null && 
                      <Link to="/login" style={{textDecoration: "none",
                      color:'#000'}}><MenuItem>Sign in</MenuItem></Link>}
                  </MenuList>
                </ClickAwayListener>
              </Paper>
            </Grow>
          )}
        </Popper>
      </div>
    </div>
  );
}