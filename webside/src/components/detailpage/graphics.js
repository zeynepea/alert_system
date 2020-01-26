import React, { Component } from 'react';
import axios from 'axios';
import './graphics.css'
import { CanvasJSChart, CanvasJS} from '../../canvasjs.react';
class Graphics extends Component {
    _isMouted = false;
    constructor() {
        super();
        this.state = {name: '',
                      situations: [],
                      mounted: true,
                      period: 0,
                      requestName: '',
                      url: ''
                    };
    }
    setSituation(){
        axios.get('http://localhost:8080/alerts/'+
                    localStorage.getItem("username") + '/' +
                    this.props.match.params.name,
                    { headers: { Authorization: localStorage.getItem("token") } })
        .then(response =>{
            if(this._isMouted){
                this.setState({name: response.data[0].name,
                               period: response.data[0].period,
                               requestName: response.data[0].requestName,
                               situations:[...response.data[0].situations],
                               url: response.data[0].url});
            }
        }).catch(err =>{
            console.log(err);
        });
    }
	componentDidMount() {
        this._isMouted=true;
        setInterval(() => {
            if(this._isMouted)
                this.setSituation();
        }, 1000);
    }

    componentWillUnmount(){
        this._isMouted=false;
    }

	render() {
        let dps = this.state.situations.map(situation=>{
            let date = new Date(situation.date);
            return {x: date, y: situation.downorup, toolTipContent: situation.response}
        });
        let last_25_dps =( (dps.length < 20) ? [...dps] : [...dps.slice(dps.length-20, dps.length)]);
        let options = {
			title :{
                text: this.state.name,
                fontColor: "lightslategray",
                fontFamily: "helvetica",
                fontSize: 30,
                padding: 10,
                margin: 30,
                fontWeight: "bold"
            },
            axisX: {
                labelFormatter: function (e) {
                    return CanvasJS.formatDate( e.value, "YYYY-MM-DD HH:mm:ss");
                },
                labelAngle: -50
            },
			data: [{
                type: "line",
				showInLegend: true,
				name: "sending " + this.state.requestName + " request to: " + this.state.url + ", with periods of " + this.state.period + " seconds",
				dataPoints : last_25_dps
			}]
		}
		return (
            <div className = "graph"> 
                <div>
                    <CanvasJSChart options = {options}
                        onRef={ref => this.chart = ref}
                    />
                </div>
            </div>
		);
	}    
}

export default Graphics;