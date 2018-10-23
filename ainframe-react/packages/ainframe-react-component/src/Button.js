import React from 'react';
import PropTypes from 'prop-types';
import classnames from 'classnames';

const propTypes = {
  children: PropTypes.node.isRequired,
  tagName: PropTypes.string,
  btnSize: PropTypes.string,
  btnStyle: PropTypes.string,
};
const defaultProps = {
  tagName: 'button',
  btnSize: null,
  btnStyle: 'primary',
};

class Button extends React.Component {
  getDom() {
    const { children, tagName, btnSize, btnStyle, ...props } = this.props;
    const aClassName = classnames('btn', `btn-${btnStyle}`, btnSize ? `btn-${btnSize}` : '');

    switch (tagName) {
      case 'a':
        return (
          <a className={aClassName} role="button" {...props}>
            {children}
          </a>
        );
      case 'input':
        return <input className={aClassName} type="button" {...props} />;
      default:
        return (
          <button className={aClassName} type="button" {...props}>
            {children}
          </button>
        );
    }
  }

  render() {
    return this.getDom();
  }
}

Button.propTypes = propTypes;
Button.defaultProps = defaultProps;

export default Button;
