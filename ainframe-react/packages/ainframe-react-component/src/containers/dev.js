import React from 'react';
import ReactDOM from 'react-dom';
import { HashRouter as Router, Route, Switch } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.css';
import '@fortawesome/fontawesome-free/css/all.css';

import InputSearchContainer from './InputSearchContainer';
import Menu from './Menu';

ReactDOM.render(
  <Router>
    <div className="container">
      <h1>Components</h1>
      <div className="row">
        <div className="col">
          <Menu />
        </div>
        <div className="col-10">
          <Switch>
            <Route path="/inputSearch" component={InputSearchContainer} />
          </Switch>
        </div>
      </div>
    </div>
  </Router>,
  document.getElementById('app'),
);
