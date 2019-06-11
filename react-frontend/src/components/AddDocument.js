import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

class AddDocument extends Component {
    constructor() {
        super();
        this.state = {
            authorId: '',
            title: '',
            description: '',
            files: [],
            documentTypes: []
        }
    }

    componentDidMount() {
        axios.get('http://localhost:8080/api/internship/documentTypes')
        .then((result) => {
            this.setState({documentTypes: result.data})
        })
    }

    onSubmit = (e) => {
        e.preventDefault();
        const { authorId, title, description, files } = this.state;
         const documentTypeName = document.querySelector('select').value;
         const documentTypeId = this.state.documentTypes.filter(d => d.typeName === documentTypeName)[0].id;
        console.log(files);
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
            let file = e.target.files[i];
            if (file.name.endsWith('.pdf')) {
                let reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = (e) => {
                    files.push(e.target.result);
                }
            }
        }
        this.setState({files: files});
    }
        

    render() {
        const { authorId, title, description, documentTypes } = this.state;
        return (
            <div className='container'>
                <form onSubmit={this.onSubmit}>
                    <label>Author Id: </label>
                    <input name='authorId' value={authorId} onChange={this.onChangeInput}></input>

                    <label>Dokumento rūšis: </label>
                    <select>
                        {documentTypes.map(d => 
                        <option id={d.id}>{d.typeName}</option>
                            )}
                    </select>

                    <label>Pavadinimas: </label>
                    <input name='title' value={title} onChange={this.onChangeInput}></input>

                    <label>Aprašas: </label>
                    <input name='description' value={description} onChange={this.onChangeInput}></input>

                    <input type='file' accept=".pdf" multiple onChange={this.onChangeFiles}></input>

                    <button>Pridėti dokumentą</button>
                </form>
            </div>
        );
    }
}

export default AddDocument;