import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { throwStatement } from '@babel/types';

class AddUserToGroup extends Component {
    constructor() {
        super();
        this.state = {
            userGroups: [],
            users: [],
            usersShown: [],
            searchUsersInputValue: ''
        }
    }

    componentDidMount() {
        axios.get('http://localhost:8080/api/internship/userGroups')
        .then((result) => {
            this.setState({userGroups: result.data}, function() {this.showUsersNotInGroup()})
        });
    }

    onSubmit = (e) => {
        e.preventDefault();
        
    }

    showUsersNotInGroup() {
        var userGroupName = document.getElementById('select-user-group').value;
        var userGroupId = this.state.userGroups.filter(u => u.userGroupName === userGroupName)[0].id;
        axios.get('http://localhost:8080/api/internship/userGroups/'+ userGroupId+ '/not/users')
        .then((response) => {
            this.setState({users: response.data});
            this.setState({usersShown: response.data});
        });
    }

    onGroupChange = (e) => {
        this.showUsersNotInGroup();
    }

    onChangeSearchUsersInput = (e) => {
        let inputedValueLowerCase = e.target.value.toLowerCase();
        let usersShown = this.state.users.filter(u => u.personFirstAndLastName.toLowerCase().startsWith(inputedValueLowerCase));
        this.setState({usersShown: usersShown});
        this.setState({searchUsersInputValue: inputedValueLowerCase.toUpperCase()});
    }

    render() {
        const { userGroups, usersShown, searchUsersInputValue } = this.state;
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
                    <label>Vartotojo pasirinkimas(vedant pavardę ir vardą, turėtų prafiltruot): </label>
                    <input name='search-users-input' value={searchUsersInputValue} onChange={this.onChangeSearchUsersInput}></input>
                    <table>
                        <tbody>
                            {usersShown.map(u => 
                                <tr>
                                    <td id={u.id}>{u.personFirstAndLastName}</td>
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