import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import AddDocument from './components/AddDocument';
import App from './App.js';

ReactDOM.render(
  <Router>
      <div>
        <Route exact path='/' component={App} />
        <Route exact path = "/documents/add" component={AddDocument} />
      </div>
  </Router>,
  document.getElementById('root')
);
