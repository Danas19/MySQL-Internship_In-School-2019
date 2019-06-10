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
            files: []
        }
    }

    onSubmit = (e) => {
        e.preventDefault();
        const { authorId, documentTypeId, title, description, files } = this.state;
        axios.post('http://localhost:8080/api/internship/documents/'+documentTypeId+'/'+authorId, {title, description, files})
        .then((result) => {
            this.props.history.push('/');
        });
    }

    onChangeInput = (e) => {
        const state = this.state;
        state[e.target.name] = e.target.value;
        this.setState(state);
    }

    onChangeFiles = (e) => {
        let files = [];
        for (var i = 0; i < e.target.files.length; i++) {
            files.push(e.target.files[i]);
        }
        console.log(fileURLS);
        this.setState({files: fileURLS});
    }

    render() {
        const { authorId, documentTypeId, title, description } = this.state;
        return (
            <div className='container'>
                <form onSubmit={this.onSubmit}>
                    <label>Author Id: </label>
                    <input name='authorId' value={authorId} onChange={this.onChangeInput}></input>

                    <label>Document Type Id: </label>
                    <input name='documentTypeId' value={documentTypeId} onChange={this.onChangeInput}></input>

                    <label>Title: </label>
                    <input name='title' value={title} onChange={this.onChangeInput}></input>

                    <label>Description: </label>
                    <input name='description' value={description} onChange={this.onChangeInput}></input>

                    <input type='file' accept=".pdf" multiple onChange={this.onChangeFiles}></input>

                    <button>Add Document</button>
                </form>
            </div>
        );
    }
}

export default AddDocument;