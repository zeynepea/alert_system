import React from 'react';
import './App.css';
import Main from './components/mainpage/main';
import { BrowserRouter, Route, Switch, Link } from 'react-router-dom';
import Graphics from './components/detailpage/graphics';
import Login_signup from './components/login_signup_page/login_sigup';
import { NOTFOUND } from 'dns';

function App() {  
  let header = (localStorage.getItem("username")!==null) ? ('/'+localStorage.getItem("username")) : '/';
  return (
    <BrowserRouter >
    <div >
      <div className="header"  >
        <Link to={{pathname: header} } className="xx" >Alert System</Link>
      </div>
      <Switch>
        <Route path="/" component= {Login_signup} exact/>
        <Route path="/null" component= {Login_signup} exact/>
        <Route path="/:username" component={Main} exact />
        <Route path="/:username/:name" component = {Graphics}  exact/>
        <Route component={NOTFOUND} />
      </Switch>
      <div className="footer">made by Zeynep Erdoğan</div>
    </div>
  </BrowserRouter>
  );
}

export default App;
