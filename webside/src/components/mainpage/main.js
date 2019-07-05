import React, { Component } from 'react';
import Alertform from './alertform';
import './main.css';
import AlertLinks from './alertLinks';


class Main extends Component {
  
    render(){
        return (   
        <div>         
            <div className="column side">
              <Alertform></Alertform>
            </div>
            <div className="column middle">
              <AlertLinks></AlertLinks>
            </div>
        </div>           
          );
    }
  
}

export default Main;