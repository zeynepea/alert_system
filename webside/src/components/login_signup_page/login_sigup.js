import React, { Component } from 'react';
import Signup from './signup';
import './login_signup.css';
import Login from './login';


class Login_signup extends Component { 
    constructor(props){
        super(props);
        this.toggle_login = this.toggle_login.bind(this);
        this.toggle_signup = this.toggle_signup.bind(this);
        this.state = {
            isOpen: false
        };
    }
    toggle_login() {
        this.setState({
            isOpen: false
        });
    }
    toggle_signup() {
        this.setState({
            isOpen: true
        });
    }
    render(){
        let login = (
            <div className="login_signup">
              <Login></Login>
            </div>
        )
        let signup = (
            <div className = "login_signup">
              <Signup></Signup>
            </div>
        )
        let whichone = (this.state.isOpen ? signup : login );
        return (   
        <div> 
            <div className = "head"> 
              <button onClick = {this.toggle_login}>Login </button>
              <button onClick = {this.toggle_signup}>Signup </button>
            </div>  
            <div>{whichone}  </div>
        </div>           
        );
    }  
}

export default Login_signup;