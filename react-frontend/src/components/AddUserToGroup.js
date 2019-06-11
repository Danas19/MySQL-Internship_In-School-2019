import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { thisTypeAnnotation } from '@babel/types';

class AddUserToGroup extends Component {
    constructor() {
        super();
        this.state = {
            userGroups: [],
            users: []
        }
    }

    componentDidMount() {
        axios.get('http://localhost:8080/api/internship/userGroups')
        .then((result) => {
            this.setState({userGroups: result.data})
        })
    }

    onSubmit = (e) => {
        e.preventDefault();
        const { firstName, lastName } = this.state;
        const userGroupName = document.querySelector('select').value;
        const userGroupId = this.state.userGroups.filter(u => u.userGroupName === userGroupName)[0].id;
        axios.post('http://localhost:8080/api/internship/users/'+ userGroupId, {firstName, lastName})
        .then((result) => {
            this.props.history.push('/');
        });
    }

    onChange = (e) => {
        const state = this.state;
        state[e.target.name] = e.target.value;
        this.setState(state);
    }

    onGroupChange = (e) => {
        axios.get('http://localhost:8080/api/internship/userGroups/{userGroupId}/not/users')
        .then((response) => {
            this.setState({users: response.data});
        });
    }

    render() {
        const { userGroups, users } = this.state;
        return (
            <div className='container'>
                <form onSubmit={this.onSubmit}>
                    <label>Vartotojų grupės pasirinkimas: </label>
                    <select onChange={this.onGroupChange}>
                        {userGroups.map(u => 
                        <option id={u.id}>{u.userGroupName}</option>
                            )}
                    </select>

                    <label>Vartotojo pasirinkimas: </label>
                    <table>
                        <tbody>
                            {users.map(u => 
                                <tr>
                                    <td>{u.firstName + u.lastName}</td>
                                </tr>
                                )}
                        </tbody>
                    </table>

                    <button>Pridėti šį vartotoją į grupę</button>
                </form>
            </div>
        );
    }
}

export default AddUserToGroup;