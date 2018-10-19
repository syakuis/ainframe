import React from 'react';
import PropTypes from 'prop-types';

const propTypes = {
  className: PropTypes.string,
  value: PropTypes.string,
  onChange: PropTypes.func.isRequired,
};
const defaultProps = {
  className: 'form-control',
  value: '',
};

class Input extends React.Component {
  constructor(props) {
    super(props);

    this.onValue = this.onValue.bind(this);
  }

  onChange(e) {
    const { onChange } = this.props;
    onChange(e.target.value, e.target.name, e);
  }

  render() {
    const { value, className, ...props } = this.props;
    return (
      <input type="text" {...props} className={className} value={value} onChange={this.onChange} />
    );
  }
}

Input.propTypes = propTypes;
Input.defaultProps = defaultProps;

export default Input;
