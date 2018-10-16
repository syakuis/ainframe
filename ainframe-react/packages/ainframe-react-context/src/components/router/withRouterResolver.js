import React from 'react';
import PropTypes from 'prop-types';
import { withRouter } from 'react-router-dom';

const withRouterResolver = Component => {
  const propTypes = {
    match: PropTypes.shape({
      params: PropTypes.shape({
        moduleName: PropTypes.string.isRequired,
      }).isRequired,
    }).isRequired,
    data: PropTypes.shape({}).isRequired,
  };

  class Container extends React.Component {
    constructor(props) {
      super(props);

      this.state = { componentInstrance: null };
    }

    static getDerivedStateFromProps(props, state) {
      const { moduleName } = props.match.params;
      if (moduleName !== state.moduleName) {
        return { componentInstrance: null, moduleName };
      }
      return null;
    }

    componentDidMount() {
      const { match } = this.props;
      const { moduleName } = match.params;
      this.getImport(moduleName);
    }

    componentDidUpdate() {
      const { componentInstrance } = this.state;
      if (componentInstrance === null) {
        const { match } = this.props;
        const { moduleName } = match.params;
        this.getImport(moduleName);
      }
    }

    getImport(moduleName) {
      if (moduleName !== null) {
        const { data } = this.props;
        const component = data[moduleName];
        this.asyncRequest = component().then(module => {
          this.asyncRequest = null;
          this.setState({ componentInstrance: module });
        });
      }
    }

    render() {
      const { componentInstrance } = this.state;
      if (componentInstrance === null) return null;
      return <Component component={componentInstrance} {...this.props} />;
    }
  }

  Container.propTypes = propTypes;

  return withRouter(Container);
};

export default withRouterResolver;
