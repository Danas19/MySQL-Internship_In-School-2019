import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

class AddDocument extends Component {
    constructor() {
        super();
        this.state = {
            authorId: '',
            documentTypeId: '',
            title: '',
            description: '',
            file: ''
        }
    }

    onSubmit = (e) => {
        e.preventDefault();
        const { authorId, documentTypeId, title, description } = this.state;
        axios.post('http://localhost:8080/api/internship/documents/'+documentTypeId+'/'+authorId, {title, description})
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
        const { authorId, documentTypeId, title, description } = this.state;
        return (
            <div className='component'>
                <form onSubmit={this.onSubmit}>
                    <label>Author Id: </label>
                    <input name='authorId' value={authorId} onChange={this.onChange}></input>

                    <label>Document Type Id: </label>
                    <input name='documentTypeId' value={documentTypeId} onChange={this.onChange}></input>

                    <label>Title: </label>
                    <input name='title' value={title} onChange={this.onChange}></input>

                    <label>Description: </label>
                    <input name='description' value={description} onChange={this.onChange}></input>
                    <button>Add Document</button>
                </form>
            </div>
        );
    }
}

export default AddDocument;