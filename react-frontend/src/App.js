import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import './components/CSS/style.css';

class App extends Component {
    constructor() {
        super();
    }

    onClick() {
      axios.post(`http://localhost:8080/api/internship/testData/create`).
      then((result) => {
        console.log("Test data was added succesfully");
      })
    }

    render() {
        return (
            <div className='container'>
                <button onClick={this.onClick}>AddTestData</button>
            </div>
        );
    }
}

export default App;