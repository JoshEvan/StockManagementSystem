import React from 'react'
import { Link, RouteComponentProps } from 'react-router-dom';
import { SpecialButton } from '../components/molecules/SpecialButton';
import { Dashboard } from '../components/template/Dashboard';
import CustomTable from '../components/organism/CustomTable';

interface Props extends RouteComponentProps{};

// history itu didapet dari props
// kalo pake Link nya react-router ga perlu props history
// history di pass dari Route nya react-router-dom
export const ItemPage: React.FC<Props> = ({ history,location,match}) => {

	console.log(location);
	// location.search bisa kaya buat ambil pathvariable
	console.log(match);
		return (
			<Dashboard 
			titlePage = {"Items"}			
			content={
				<div>
					test
					<CustomTable test={"test"}/>
				</div>
			}/>
    )
};
