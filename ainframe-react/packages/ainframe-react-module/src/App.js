import React from 'react';
import { HashRouter as Router, Route, Switch } from 'react-router-dom';

import ModuleListContainer from './containers/ModuleListContainer';
import ModuleFormContainer from './containers/ModuleFormContainer';

class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = {};
  }

  render() {
    return (
      <Router basename="/module">
        <Switch>
          <Route exact path="/" component={ModuleListContainer} />
          <Route exact path="/form" component={ModuleFormContainer} />
          <Route path="/:moduleIdx" component={ModuleListContainer} />
        </Switch>
      </Router>
    );
  }
}

export default App;
