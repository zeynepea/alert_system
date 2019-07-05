import React from 'react';
import './App.css';
import Main from './components/mainpage/main';
import { BrowserRouter, Route, Switch, Link } from 'react-router-dom';
import Graphics from './components/detailpage/graphics';
import Login_signup from './components/login_signup_page/login_sigup';

function App() {
  return (
    <BrowserRouter >
    <div >
      <div className="header"  >
        <Link to={{pathname: '/alerts'} } className="xx" >Alert System</Link>
      </div>
      <Switch>
        <Route path="/login" component= {Login_signup} />
        <Route path="/alerts" component={Main} exact />
        <Route path="/alerts/:name" component = {Graphics}/>
        <Route component={Error} />
      </Switch>
      <div className="footer">made by Zeynep Erdoğan</div>
    </div>
  </BrowserRouter>
  );
}

export default App;
