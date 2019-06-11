import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { thisTypeAnnotation } from '@babel/types';

class AddUser extends Component {
    constructor() {
        super();
        this.state = {
            firstName: '',
            lastName: '',
            userGroups: []
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

    render() {
        const { firstName, lastName } = this.state;
        return (
            <div className='container'>
                <form onSubmit={this.onSubmit}>
                    <label>Vardas: </label>
                    <input name='firstName' value={firstName} onChange={this.onChange}></input>

                    <label>Pavardė: </label>
                    <input name='lastName' value={lastName} onChange={this.onChange}></input>

                    <label>Pasirinkti vartotojų grupę</label>
                    <select>
                        {this.state.userGroups.map(u => 
                        <option id={u.id}>{u.userGroupName}</option>
                            )}
                    </select>

                    <button>Pridėti naują asmenį, kuris bus vartotojas</button>
                </form>
            </div>
        );
    }
}

export default AddUser;