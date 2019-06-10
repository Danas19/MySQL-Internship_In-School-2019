import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

class ViewAllPersons extends Component {
    constructor() {
        super();
        this.state = {
            persons: [] 
        }
    }

    componentDidMount() {
        axios.get('http://localhost:8080/api/internship/persons')
        .then((result) => {
            this.setState({persons: result.data});
        });
    }

    render() {
        return (
            <div className='container'>
                <table>
              <thead>
              <tr>
                <th>id</th>
                <th>firstName</th>
                <th>lastName</th>
                <th>admin</th>
                <th>user</th>
              </tr>
              </thead>
              <tbody>
                {this.state.persons.map(p =>
                  <tr key={p.id}>
                <td>{p.id}</td>
                  <td>{p.firstName}</td>
                  <td>{p.lastName}</td>
                  <td>{p.admin != null ? "YES" : "NO"}</td>
                  <td>{p.user != null ? "YES" : "NO"}</td>
                  </tr>
                  )}
                  
              </tbody>
            </table>
            </div>
        );
    }
}

export default ViewAllPersons;