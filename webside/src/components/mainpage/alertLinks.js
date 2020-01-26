import React, { Component } from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';
import './alertLinks.css';

class AlertLinks extends Component {

    _isMouted = false;
    constructor(props) {
        super(props);
        this.state = {names: [],
                      start: false,
        };
        this.delete = this.delete.bind(this);
    }
    
    getNames(){
        axios.get('http://localhost:8080/alerts/getNames/' + 
                    localStorage.getItem("username"),
                    { headers: { Authorization: localStorage.getItem("token") } })
            .then(response=>{
            if(this._isMouted){
                this.setState({names: [...response.data],
                               start: true});
            }
        }).catch(err =>{
            console.log(err);
        });
    }

    componentDidMount(){
        this._isMouted = true;
        setInterval(() => {
            if(this._isMouted)
                this.getNames();
        }, 1000);
    }

    componentWillUnmount(){
        this._isMouted = false;
    }

    delete(alertName){
        axios.delete('http://localhost:8080/delete/'+
                    localStorage.getItem("username") + '/' +
                    alertName
                    ,{ headers: { Authorization: localStorage.getItem("token") } })
             .then(response =>{
            console.log(response);
        })
    }
  
    render() {
    let namesMap = this.state.names.map((nam, index) => {
        return (
          <tr className="table-row" key={index} >
            <td >
                <h1  >
                   <Link to={{pathname: '/'+localStorage.getItem("username")+'/'+nam}}  className="table-data"  >{nam}</Link>
                </h1>
            </td>
            <td className="forbutton" ><button className="Button" onClick={() => this.delete(nam)} >X</button></td>
          </tr>
        );
    });
    let namesTable =  (
        <table className="table">
            <thead className="table-header"> 
                <tr >
                    <th className="header__item">Alert Names</th>
                </tr>        
            </thead>
            <tbody className="table-content" >
                {namesMap}
            </tbody>
        </table>
    );
    let whatToReturn = null;
    if(this.state.start){
        whatToReturn = 
            <div>{namesTable}</div>
    }
    return (
        <div className = "container">
            {whatToReturn}				
        </div>
    );
  }
  
}

export default AlertLinks;