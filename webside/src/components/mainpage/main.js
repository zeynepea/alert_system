import React, { Component } from 'react';
import Alertform from './alertform';
import './main.css';
import AlertLinks from './alertLinks';
import { Redirect } from 'react-router';
import UserDetails from './userDetails';

class Main extends Component {


  constructor(props) {
    super(props);
    this.state = {username: this.props.match.params.username};
  }
  
    render(){
        if(this.state.username !== localStorage.getItem("username")){
            return <Redirect push to={"/"+localStorage.getItem("username")} />;
        }
        return (   
        <div>         
            <div className="head">
              <UserDetails username={this.state.username}/>
            </div>
            <div className="column side">
              <Alertform/>
            </div>
            <div className="column middle">
              <AlertLinks/>
            </div>
        </div>           
          );
    }
  
}

export default Main;