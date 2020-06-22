import React from 'react'
import { Link, RouteComponentProps } from 'react-router-dom';
import { SpecialButton } from '../components/molecules/SpecialButton';
import { Dashboard } from '../components/template/Dashboard';
import { Typography } from '@material-ui/core';

interface Props extends RouteComponentProps{};

// history itu didapet dari props
// kalo pake Link nya react-router ga perlu props history
// history di pass dari Route nya react-router-dom
export const Home: React.FC<Props> = ({ history,location,match }) => {

	console.log(location);
	// location.search bisa kaya buat ambil pathvariable
	console.log(match);
		return (
			<div>
					<Dashboard 
						titlePage="Dashboard"
						content={
						<div>
							<br/>
							<Typography variant="h3" component="h2" gutterBottom>
								Hello, Welcome to Joseph
							</Typography>
							<Typography variant="overline" display="block" gutterBottom>
								Stock Management System
							</Typography>
						</div>}
						
					/>
					{/* <Link to ="/about">
						go to about
					</Link>
					<button
						onClick={()=> {
							history.push('/about')
						}}
					>button to about</button>

					<SpecialButton/> */}
			</div>
    )
};
