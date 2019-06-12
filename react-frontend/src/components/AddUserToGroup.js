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

        let currentUserGroupName = document.querySelector('select').value;
        let currentUserGroupId = this.state.userGroups.filter(u => u.userGroupName == currentUserGroupName)[0].id;
        let usersSelected = this.state.usersSelected;
        let usersSelectedWrapper = {'users': usersSelected};

        axios.post('http://localhost:8080/api/internship/addMultipleUsersToGroup/'+ currentUserGroupId, {usersSelectedWrapper})
        .then((result) => {
            console.log(result.data);
            this.props.history.push('/');
        })
    }

    showUsersNotInGroup() {
        if (this.state.userGroups.length != 0) {
            var userGroupName = document.getElementById('select-user-group').value;
            var userGroupId = this.state.userGroups.filter(u => u.userGroupName === userGroupName)[0].id;
            axios.get('http://localhost:8080/api/internship/userGroups/'+ userGroupId+ '/not/users')
            .then((response) => {
            this.setState({users: response.data});
            this.setState({usersShown: response.data});
            });
        }
        
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
        let userIndex = this.state.usersShown.slice(0, 15).findIndex(u => u.id == e.target.id);

        let usersSelected = this.state.usersSelected;
        usersSelected.push(this.state.usersShown[userIndex]);

        let usersShown = [...this.state.usersShown.slice(0, userIndex),
             ...this.state.usersShown.slice(userIndex + 1)];

        this.setState({usersSelected: usersSelected});
        this.setState({usersShown: usersShown});
        
    }

    onClickSelectedUser = (e) => {
        let userIndex = this.state.usersSelected.findIndex(u => u.id == e.target.id);
        console.log(userIndex);

        let usersShown = this.state.usersShown;
        usersShown.push(this.state.usersSelected[userIndex]);

        let usersSelected = [...this.state.usersSelected.slice(0, userIndex), 
            ...this.state.usersSelected.slice(userIndex + 1)];

        this.setState({usersSelected: usersSelected});
        this.setState({usersShown: usersShown});
    }

    render() {
        const { userGroups, usersShown, searchUsersInputValue, usersSelected } = this.state;
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
                    <thead>
                            <tr>
                                <th>Vartotojų, iš kurių galima pasirinkti, sąrašas</th>
                            </tr>
                        </thead>
                        <tbody>
                            {usersShown.slice(0, 15).map(u => 
                                <tr>
                                    <td id={u.id} onClick={this.onClickShownUser}>{u.personFirstAndLastName}</td>
                                </tr>
                                )}
                        </tbody>
                    </table>

                    <table>
                        <thead>
                            <tr>
                                <th>Pasirinkti vartotojai</th>
                            </tr>
                        </thead>
                        <tbody>
                            {usersSelected.map(u => 
                                <tr>
                                    <td id={u.id} onClick={this.onClickSelectedUser}>{u.personFirstAndLastName}</td>
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