import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

class AddDocumentType extends Component {
    constructor() {
        super();
        this.state = {
            typeName: '',
        }
    }

    onSubmit = (e) => {
        e.preventDefault();
        const { typeName } = this.state;
        axios.post('http://localhost:8080/api/internship/documentTypes', {typeName})
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
        const { typeName } = this.state;
        return (
            <div className='container'>
                <form onSubmit={this.onSubmit}>
                    <label>Dokumento tipo pavadinimas: </label>
                    <input name='typeName' value={typeName} onChange={this.onChange}></input>

                    <button>Pridėti naują dokumneto tipą</button>
                </form>
            </div>
        );
    }
}

export default AddDocumentType;