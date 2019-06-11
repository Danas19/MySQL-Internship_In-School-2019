import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

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
            this.setState({userGroups: result.data});
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

    onGroupChange = (e) => {
        var userGroupName = document.getElementById('select-user-group').value;
        var userGroupId = this.state.userGroups.filter(u => u.userGroupName === userGroupName)[0].id;
        axios.get('http://localhost:8080/api/internship/userGroups/'+ userGroupId+ '/not/users')
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
                    <select id="select-user-group" onChange={this.onGroupChange}>
                        {userGroups.map(u => 
                        <option id={u.id}>{u.userGroupName}</option>
                            )}
                    </select>

                    <br></br>
                    <label>Vartotojo pasirinkimas: </label>
                    <table>
                        <tbody>
                            {users.map(u => 
                                <tr>
                                    <td>{u.personFirstAndLastName}</td>
                                </tr>
                                )}
                        </tbody>
                    </table>

                    <button>Pridėti šiuos vartotojus į grupę</button>
                </form>
            </div>
        );
    }
}

export default AddUserToGroup;