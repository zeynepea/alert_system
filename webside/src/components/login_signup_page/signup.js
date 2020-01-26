import React, { Component } from 'react';
import axios from 'axios';

class Signup extends Component {
  
    constructor(props) {
        super(props);
        this.state = {username: '',
                      password: '',
                      email: '',
                      error: '',                    
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
        axios.post('http://localhost:8080/users/signup',{
          username: this.state.username,
          password: this.state.password,
          email: this.state.email,
          active: 1,
          roles: "USER",
          permissions:"",
          alerts: [],
        }).then(message=>{
          this.setState({error : message.data });
          console.log(message.data);
        }).catch(err => {
          this.setState({error: err.data})
        })
      }
      render() {
        
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
            <div className= "custom">
              <label>
                Email:  
                <input type="text"              
                    name = "email"
                    value={this.state.email} 
                    onChange={this.handleChange}
                    required/>
              </label>
            </div>
            <div className= "custom2">
                <input className="Button" 
                 type="submit" 
                 value="SIGN UP" />
            </div>
          </form>
          <div style={{color: "red"}}>{this.state.error}</div>
        </div>
        );
      }
}

export default Signup;