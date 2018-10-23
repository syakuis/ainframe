import React from 'react';
import PropTypes from 'prop-types';

const propTypes = {
  className: PropTypes.string,
  id: PropTypes.string.isRequired,
  value: PropTypes.string,
  onChange: PropTypes.func.isRequired,
  size: PropTypes.string,
};
const defaultProps = {
  className: 'form-control',
  value: '',
  size: '',
};

class Input extends React.Component {
  constructor(props) {
    super(props);

    this.onChange = this.onChange.bind(this);
  }

  onChange(e) {
    const { onChange } = this.props;
    const { target } = e;
    const { value, id } = target;
    onChange(value, id, target);
  }

  render() {
    const { id, value, size, className, ...props } = this.props;
    return (
      <input
        type="text"
        {...props}
        id={id}
        aria-describedby={`${id}Help`}
        className={`${className}${size ? ` form-control-${size}` : ''}`}
        value={value}
        onChange={this.onChange}
      />
    );
  }
}

Input.propTypes = propTypes;
Input.defaultProps = defaultProps;

export default Input;
