import React, { Component } from 'react';

class Signup extends Component {
  
    constructor(props) {
        super(props);
        this.state = {username: '',
                      password: '',
                      email: '',
                      error: '',                     //error text if invalid url or already exists name 
                                                     //or period 0. 
                      ifInvalid: "",                 //to make url input box red
                                                     //if invalid url  
                      ifNameExists: "",              //to make name input box red
                                                     //if name already exists
                      ifPeriodZero: "",              //to make period input box red
                                                     //if period is 0
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
        
      }
      render() {
        
        return (
        <div className = "Form">
          <form onSubmit={this.handleSubmit} >
            <div className= "custom"
                 style = {{border: this.state.ifNameExists}}>
              <label>
                Username: 
                <input type="text"
                name = "username"
                value={this.state.username} 
                onChange={this.handleChange}
                required/>
              </label>
            </div>
            <div className= "custom" 
                   style = {{border: this.state.ifInvalid}}>
              <label  >
                Password: 
                <input type="text"              
                    name = "password"
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