import React from 'react';
import PropTypes from 'prop-types';

const propTypes = {
  className: PropTypes.string,
  id: PropTypes.string.isRequired,
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

    this.onChange = this.onChange.bind(this);
  }

  onChange(e) {
    const { onChange } = this.props;
    onChange(e.target.value, e.target.id, e);
  }

  render() {
    const { id, value, className, ...props } = this.props;
    return (
      <input
        type="text"
        {...props}
        id={id}
        aria-describedby={`${id}Help`}
        className={className}
        value={value}
        onChange={this.onChange}
      />
    );
  }
}

Input.propTypes = propTypes;
Input.defaultProps = defaultProps;

export default Input;
