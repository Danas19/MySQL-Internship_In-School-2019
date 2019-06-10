import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import AddDocument from './components/AddDocument';
import App from './App.js';
import ViewAllDocuments from './components/ViewAllDocuments';
import AddAdmin from './components/AddAdmin';
import ViewAllPersons from './components/ViewAllPersons';
import AddUserGroup from './components/AddUserGroup';
import ViewAllUserGroups from './components/ViewAllUserGroups';

ReactDOM.render(
  <Router>
      <div>
        <Route exact path='/' component={App} />
        <Route exact path = "/documents" component={ViewAllDocuments} />
        <Route exact path = "/documents/add" component={AddDocument} />
        <Route exact path = "/persons" component={ViewAllPersons} />
        <Route exact path = "/admins/add" component={AddAdmin} />
        <Route exact path = "/userGroups" component={ViewAllUserGroups} />
        <Route exact path = "/userGroups/add" component={AddUserGroup} />
      </div>
  </Router>,
  document.getElementById('root')
);
