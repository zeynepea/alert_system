import React, { Component } from 'react';
import axios from 'axios';
import { Redirect } from 'react-router';


class Login extends Component {
  
    constructor(props) {
        super(props);
        this.state = {username: '',
                      password: '',
                      error: '',  
                      redirect: false
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }
    
      handleChange(event) {
        this.setState({
          [event.target.name] : event.target.value
        });
      }
    
      handleSubmit(event) {
        event.preventDefault();
        axios.post('http://localhost:8080/login',{
          username: this.state.username,
          password: this.state.password,
        }).then(message=>{
          console.log("message.headers.authorization");
          this.setState({error : message.data });
          localStorage.setItem("token", message.headers.authorization);
          localStorage.setItem("auth", true);
          localStorage.setItem("username", this.state.username);
          this.setState({redirect: true});
        }).catch(err => {
          this.setState({error:"unknown username or password doesn't match"});
        })
      }

      render() {
        if(this.state.redirect){
          return <Redirect push to={"/"+this.state.username} />;
        }
        return (
        <div className = "Form">
          <form onSubmit={this.handleSubmit} >
            <div className= "custom">
              <label>
                Username: 
                <input type="text"
                name = "username"
                value={this.state.username} 
                onChange={this.handleChange}
                required/>
              </label>
            </div>
            <div className= "custom" >
              <label  >
                Password: 
                <input type="password"              
                    name = "password"
                    autoComplete="off"
                    value={this.state.password} 
                    onChange={this.handleChange}
                    required/>
              </label>
            </div>
            <div className= "custom2">
                <input className="Button" 
                 type="submit" 
                 value="LOG IN" />
            </div>
          </form>
          <div style={{color: "red"}}>{this.state.errors}</div>
        </div>
        );
      }
}

export default Login;