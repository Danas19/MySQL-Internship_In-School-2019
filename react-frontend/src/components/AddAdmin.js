import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

class AddAdmin extends Component {
    constructor() {
        super();
        this.state = {
            firstName: '',
            lastName: ''
        }
    }

    onSubmit = (e) => {
        e.preventDefault();
        const { firstName, lastName } = this.state;
        axios.post('http://localhost:8080/api/internship/admins/', {firstName, lastName})
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
        const { firstName, lastName } = this.state;
        return (
            <div className='container'>
                <form onSubmit={this.onSubmit}>
                    <label>firstName: </label>
                    <input name='firstName' value={firstName} onChange={this.onChange}></input>

                    <label>lastName: </label>
                    <input name='lastName' value={lastName} onChange={this.onChange}></input>

                    <button>Add New Person Who is Admin</button>
                </form>
            </div>
        );
    }
}

export default AddAdmin;