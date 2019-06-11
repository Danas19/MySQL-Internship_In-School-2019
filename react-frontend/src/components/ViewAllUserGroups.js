import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

class ViewAllUserGroups extends Component {
    constructor() {
        super();
        this.state = {
            userGroups: [] 
        }
    }

    componentDidMount() {
        axios.get('http://localhost:8080/api/internship/userGroups')
        .then((result) => {
            this.setState({userGroups: result.data});
        });
    }

    render() {
        return (
            <div className='container'>
                <table>
              <thead>
              <tr>
                <th>Ud</th>
                <th>Vartotojo grupės pavadinimas</th>
              </tr>
              </thead>
              <tbody>
                {this.state.userGroups.map(u =>
                  <tr key={u.id}>
                <td>{u.id}</td>
                  <td>{u.userGroupName}</td>
                  </tr>
                  )}
                  
              </tbody>
            </table>
            </div>
        );
    }
}

export default ViewAllUserGroups;