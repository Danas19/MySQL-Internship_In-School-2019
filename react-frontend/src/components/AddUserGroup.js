import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

class AddUserGroup extends Component {
    constructor() {
        super();
        this.state = {
            userGroupName: '',
        }
    }

    onSubmit = (e) => {
        e.preventDefault();
        const { userGroupName } = this.state;
        axios.post('http://localhost:8080/api/internship/userGroups', {userGroupName})
        .then((result) => {
            this.props.history.push('/');
        });
    }

    onChange = (e) => {
        const state = this.state;
        state[e.target.name] = e.target.value;
        this.setState(state);
    }

    render() {
        const { userGroupName } = this.state;
        return (
            <div className='container'>
                <form onSubmit={this.onSubmit}>
                    <label>userGroupName: </label>
                    <input name='userGroupName' value={userGroupName} onChange={this.onChange}></input>

                    <button>Add New User Group</button>
                </form>
            </div>
        );
    }
}

export default AddUserGroup;