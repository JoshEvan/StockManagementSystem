import React from 'react'
import ReactDOM from 'react-dom'
import { BrowserRouter,Route, Switch } from "react-router-dom";
import { Home } from './pages/Home';
import { About } from './pages/About';
import { Post } from './pages/Post';
import { ItemPage } from './pages/ItemPage';
import { CustomerPage } from './pages/CustomerPage';
import { ProductionPage } from './pages/ProductionPage';

export default function App(): JSX.Element {
    
    return (
        <BrowserRouter>
            {/* Route ini akan coba cocokin path jadi /about karena ada / maka dianggap Home juga mau dipanggil
            ,
            
            <Route
                path = "/about" exact component={About}
            />
            untuk atasi nya di kasih exact spy cocokin nya sama persis aja 
            dengan exact /about/me ga mau, tanpa exact mau*/}

            {/* Switch make sure cuman 1 route yg di display once, pilih salah 1 aja 
                kalo pake Switch ga kasih exact, dia lgsg bakal render first route path yg macth, jadi misal kalo /about lgsg ke render yg home (karena / dluan match)
            */}
            <Switch>
                <Route
                    path = "/" exact component={Home}
                />
                <Route
                    path = "/items" exact component={ItemPage}
                />
                <Route
                    path = "/customers" exact component={CustomerPage}
                />
                <Route
                    path = "/productions" exact component={ProductionPage}
                />
                <Route
                    path = "/posts/:id" exact component={Post}
                />
                <Route
                    path = "/" component={() => <div>404 - page not found</div>}
                />
            </Switch>

        </BrowserRouter>            
    )
}

const root = document.getElementById('app-root')

ReactDOM.render(<App/>, root)