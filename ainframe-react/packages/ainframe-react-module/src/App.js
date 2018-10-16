import React from 'react';
import { HashRouter as Router, Route, Switch } from 'react-router-dom';

import ModuleListContainer from './containers/ModuleListContainer';

class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = {};
  }

  render() {
    return (
      <Router basename="/module">
        <Switch>
          <Route path="/" component={ModuleListContainer} />
          <Route path="/:moduleIdx" component={ModuleListContainer} />
        </Switch>
      </Router>
    );
  }
}

export default App;
