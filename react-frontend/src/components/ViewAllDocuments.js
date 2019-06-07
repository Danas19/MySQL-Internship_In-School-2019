import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

class ViewAllDocuments extends Component {
    constructor() {
        super();
        this.state = {
            documents: [] 
        }
    }

    componentDidMount() {
        axios.get('http://localhost:8080/api/internship/documents/')
        .then((result) => {
            this.setState({documents: result.data});
        });
    }

    render() {
        return (
            <div className='component'>
                <table>
              <thead>
              <tr>
                <th>uniqueNumber</th>
                <th>author</th>
                <th>documentType</th>
                <th>title</th>
                <th>description</th>
                <th>sendedAtDate</th>
                <th>acceptanceDate</th>
                <th>declinationDate</th>
                <th>accepter</th>
                <th>declinationReason</th>
                <th>file</th>
              </tr>
              </thead>
              <tbody>
                {this.state.documents.map(d =>
                  <tr key={d.uniqueNumber}>
                  <td>{d.author}</td>
                  <td>{d.documentType}</td>
                  <td>{d.title}</td>
                  <td>{d.description}</td>
                  <td>{d.sendedAtDate}</td>
                  <td>{d.acceptanceDate}</td>
                  <td>{d.declinationDate}</td>
                  <td>{d.accepter}</td>
                  <td>{d.declinationReason}</td>
                  <td>{d.file}</td>
                  </tr>
                  )}
                  
              </tbody>
            </table>
            </div>
        );
    }
}

export default ViewAllDocuments;