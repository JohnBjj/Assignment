import React from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import ListFileComponent from './components/ListFileComponent';
import HeaderComponent from './components/HeaderComponent';
import UpdateFileComponent from './components/UpdateFileComponent';
import LoadFileComponent from "./components/LoadFileComponent";

function App() {
  return (
    <div>
        <Router>
              <HeaderComponent />
                <div className="container">
                    <Switch> 
                          <Route path = "/" exact component = {LoadFileComponent}></Route>
                          <Route path = "/files" component = {ListFileComponent}></Route>
                          <Route path = "/load-file" component = {LoadFileComponent}></Route>
                          <Route path = "/update-file/:id" component = {UpdateFileComponent}></Route>
                    </Switch>
                </div>
        </Router>
    </div>
    
  );
}

export default App;
