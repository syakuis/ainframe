import React from 'react';
import PropTypes from 'prop-types';
import withRouterResolver from './withRouterResolver';

const propTypes = {
  component: PropTypes.shape({}).isRequired,
};

const Controller = props => {
  const { component, ...attr } = props;
  const Component = component.default;
  return <Component {...attr} />;
};

Controller.propTypes = propTypes;

export default withRouterResolver(Controller);
