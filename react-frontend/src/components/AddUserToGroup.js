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
            usersSelected: [],
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
        this.setState({usersSelected: []});
        this.showUsersNotInGroup();
    }

    onChangeSearchUsersInput = (e) => {
        let inputedValueLowerCase = e.target.value.toLowerCase();
        let usersShown = this.state.users.filter(u => u.personFirstAndLastName.toLowerCase().startsWith(inputedValueLowerCase) && !this.isUserSelected(u));
        this.setState({usersShown: usersShown});
        this.setState({searchUsersInputValue: inputedValueLowerCase.toUpperCase()});
    }

    isUserSelected(user) {
        let userIsSelected = false;
        this.state.usersSelected.forEach(u => u === user ? userIsSelected = true : userIsSelected = userIsSelected);
        return userIsSelected;
    }

    onClickShownUser = (e) => {
        let userIndex = this.state.usersShown.findIndex(u => u.id == e.target.id);
        
        let usersSelected = this.state.usersSelected;
        usersSelected.push(this.state.usersShown[userIndex]);

        let usersShown = [...this.state.usersShown.slice(0, userIndex),
             ...this.state.usersShown.slice(userIndex + 1)];

        this.setState({usersSelected: usersSelected});
        this.setState({usersShown: usersShown});
        
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
                                    <td id={u.id} onClick={this.onClickShownUser}>{u.personFirstAndLastName}</td>
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