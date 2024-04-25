import React from "react"
import { Switch, Route, Redirect, HashRouter } from "react-router-dom"
import Home from "../pages/home/home"
import Result_404 from "../pages/result/result_404"

class Router extends React.Component {
    render() {
        return <HashRouter>
            <Switch>
                <Route path="/" exact render={() => <Redirect to="/home" />} />
                <Route path="/home"  component={Home}/>
                <Route path="*" component={Result_404} />
            </Switch>
        </HashRouter>
    }
}

export default Router;
