import React from 'react';
import clsx from 'clsx';
import { makeStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import BottomNavigation from '@material-ui/core/BottomNavigation';
import Drawer from '@material-ui/core/Drawer';
import Box from '@material-ui/core/Box';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import Badge from '@material-ui/core/Badge';
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Link from '@material-ui/core/Link';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import NotificationsIcon from '@material-ui/icons/Notifications';
import { mainListItems, secondaryListItems } from '../organism';
import { ToggleMenu } from '../organism/menu/ToggleMenu'

// function Copyright() {
//   return (
//     <Typography variant="body2" color="textSecondary" align="center">
//       {'Copyright © '}
//       <Link color="inherit" href="https://material-ui.com/">
//         Your Website
//       </Link>{' '}
//       {new Date().getFullYear()}
//       {'.'}
//     </Typography>
//   );
// }

const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
  },
  toolbar: {
    paddingRight: 24, // keep right padding when drawer closed
  },
  toolbarIcon: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-end',
    padding: '0 8px',
    ...theme.mixins.toolbar,
  },
  appBar: {
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
  },
  appBarShift: {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  menuButton: {
    marginRight: 36,
  },
  menuButtonHidden: {
    display: 'none',
  },
  title: {
    flexGrow: 1,
  },
  drawerPaper: {
    position: 'relative',
    whiteSpace: 'nowrap',
    width: drawerWidth,
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  drawerPaperClose: {
    overflowX: 'hidden',
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    width: theme.spacing(7),
    [theme.breakpoints.up('sm')]: {
      width: theme.spacing(9),
    },
  },
  appBarSpacer: theme.mixins.toolbar,
  content: {
    flexGrow: 1,
    height: '100vh',
    overflow: 'auto',
  },
  container: {
    paddingTop: theme.spacing(4),
    paddingBottom: theme.spacing(4),
  },
  paper: {
    padding: theme.spacing(2),
    display: 'flex',
    overflow: 'auto',
    flexDirection: 'column',
  },
  fixedHeight: {
    height: 240,
  },
}));

export default function Dashboard({titlePage, content}){
  const classes = useStyles();
  const [open, setOpen] = React.useState(true);
  const handleDrawerOpen = () => {
    setOpen(true);
  };
  const handleDrawerClose = () => {
    setOpen(false);
  };
  const fixedHeightPaper = clsx(classes.paper, classes.fixedHeight);

  return (
    <div className={classes.root}>
      <CssBaseline />
      <AppBar position="absolute"className={clsx(classes.appBar)}>
      {/* <AppBar position="absolute" className={clsx(classes.appBar, open && classes.appBarShift)}> */}
        {/* <Toolbar className={classes.toolbar}>
          <IconButton
            edge="start"
            color="inherit"
            aria-label="open drawer"
            onClick={handleDrawerOpen}
            className={clsx(classes.menuButton, open && classes.menuButtonHidden)}
          >
            <MenuIcon />
          </IconButton> */}
            <div
            style={{ display: 'inline-flex', alignItems: 'center', cursor: 'pointer' }}
            onClick={() => (window.location.href = '/')}
            role="button"
            tabIndex={0}
            onKeyPress={(e) => {
              if (e.key === 'Enter' || e.key === ' ') window.location.href = '/';
            }}
            >
            <img
              id="loading-img"
              style={{ width: '40px', height: 'auto', display: 'inline-block' }}
              src="https://raw.githubusercontent.com/JoshEvan/StockManagementSystem/master/stockmanagementsystem-frontend/src/assets/imgs/josephlogo.png"
            />
            <Typography
              component="h1"
              variant="h6"
              color="inherit"
              noWrap
              className={classes.title}
              style={{ display: 'inline-block', marginLeft: 8 }}
            >
              Joseph
            </Typography>
            </div>

         {/* <ToggleMenu/> */}
          

          {/* <Typography variant="overline" gutterBottom>
            Stock Management System
          </Typography> */}
          
        {/* </Toolbar> */}
      </AppBar>
      {/* <Drawer
        variant="permanent"
        classes={{
          paper: clsx(classes.drawerPaper, !open && classes.drawerPaperClose),
        }}
        open={open}
      >
        <div className={classes.toolbarIcon}>
          <IconButton onClick={handleDrawerClose}>
            <ChevronLeftIcon />
          </IconButton>
        </div>
        <Divider />
        <List>{mainListItems}</List>
        <Divider />
        <List>{secondaryListItems}</List>
      </Drawer> */}

      <Box style={{ position: 'fixed', left: 0, right: 0, bottom: 0, zIndex: 1200 }}>
        <Paper elevation={6}>
          <>
            <style>{`
              /* force bottom navigation labels to always show */
              .always-show-label .MuiBottomNavigationAction-label,
              .always-show-label .MuiBottomNavigationAction-root .MuiBottomNavigationAction-label {
                opacity: 1 !important;
                transform: none !important;
                transition: none !important;
                display: block !important;
              }
            `}</style>

            <BottomNavigation
              className="always-show-label"
              showLabels={true}
              style={{ display: 'flex', width: '100%', overflowX: 'auto' }}
            >
              <div style={{ display: 'flex', flexDirection: 'row', flexWrap: 'nowrap', width: '100%' }}>
                {React.Children.map(mainListItems, (child, i) => (
                  <div key={i} style={{ flex: '0 0 auto', display: 'inline-flex', alignItems: 'center' }}>
                    {child}
                  </div>
                ))}
              </div>
            </BottomNavigation>
          </>
        </Paper>
      </Box>
      <main className={classes.content}>
        <div className={classes.appBarSpacer} />
        
        <Container maxWidth="xl"style={{paddingTop:'2%'}}>
          <Grid>
            <Paper elevation={3} style={{padding:'2%'}}>
              <Typography variant="h4" component="h2">
                {titlePage}
              </Typography>
              
              {content}
              
            </Paper>
          </Grid>
          
        </Container>
        
      {/* <footer style={{position:"absolute", bottom: '0', justifyContent:"center",width: "auto"}}>
        <div>
        <Container maxWidth="lg" className={classes.container}>
          <Box pt={4}>
            <Copyright />
          </Box>
        </Container>
        </div>
      </footer> */}
      </main>
    </div>
  );
}

export { Dashboard };

