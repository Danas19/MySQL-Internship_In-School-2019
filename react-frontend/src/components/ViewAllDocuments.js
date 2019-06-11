import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Document, Page } from 'react-pdf';

class ViewAllDocuments extends Component {
    constructor() {
        super();
        this.state = {
            documents: [] 
        }
    }

    componentDidMount() {
        axios.get('http://localhost:8080/api/internship/documents')
        .then((result) => {
            this.setState({documents: result.data});
        });
    }

    render() {
        return (
            <div className='container'>
                <table>
              <thead>
              <tr>
                <th>Unikalus numeris</th>
                <th>Autorius</th>
                <th>Dokumento rūšis</th>
                <th>Pavadinimas</th>
                <th>Aprašas</th>
                <th>Išsiuntimo data</th>
                <th>Priėmimo data</th>
                <th>atemtimo data</th>
                <th>priėmėjas/atmetėjas</th>
                <th>atemtimo priežastis</th>
                <th>bylos</th>
              </tr>
              </thead>
              <tbody>
                {this.state.documents.map(d =>
                  <tr key={d.uniqueNumber}>
                      <td>{d.uniqueNumber}</td>
                  <td>{d.author}</td>
                  <td>{d.documentTypeName}</td>
                  <td>{d.title}</td>
                  <td>{d.description}</td>
                  <td>{d.sendedAtDate}</td>
                  <td>{d.acceptanceDate}</td>
                  <td>{d.declinationDate}</td>
                  <td>{d.accepter}</td>
                  <td>{d.declinationReason}</td>
                  <td>{d.pdfFileIds !== null ? 'Pdf bylų skaičius: '+ d.pdfFileIds.length : 'Nera bylų'}</td>
                  </tr>
                  )}
                  
              </tbody>
            </table>
            </div>
        );
    }
}

export default ViewAllDocuments;