/**
 * 기본 페이지를 설정하고 메뉴에 따른 Route 를 생성한다.
 */

import React from 'react';
import PropTypes from 'prop-types';
import { HashRouter as Router, Route, Switch, Redirect } from 'react-router-dom';
import Controller from './Controller';

const propTypes = {
  children: PropTypes.node,
  index: PropTypes.string,
  path: PropTypes.string,
  data: PropTypes.shape({}),
};

const defaultProps = {
  children: null,
  index: '/',
  path: '',
  data: {},
};

class RouterFragment extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    const { children, index, path, data } = this.props;
    return (
      <Router>
        <React.Fragment>
          <Switch>
            <Route
              path={`${path}/:moduleName`}
              render={attr => <Controller {...attr} data={data} />}
            />
            {children}
            <Route
              exact
              path={`${path}/`}
              render={attr => (
                <Redirect
                  to={{
                    pathname: path + index,
                    state: { from: attr.location },
                  }}
                />
              )}
            />
          </Switch>
        </React.Fragment>
      </Router>
    );
  }
}

RouterFragment.propTypes = propTypes;
RouterFragment.defaultProps = defaultProps;

export default RouterFragment;
