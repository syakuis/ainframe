import React from 'react';
import PropTypes from 'prop-types';

const propTypes = {
  className: PropTypes.string,
  id: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
  children: PropTypes.string.isRequired,
  value: PropTypes.string.isRequired,
  checked: PropTypes.bool,
};
const defaultProps = {
  className: 'form-check-input',
  checked: false,
};

class CheckBox extends React.Component {
  constructor(props) {
    super(props);

    this.handleChange = this.handleChange.bind(this);

    this.state = {
      checked: props.checked,
    };
  }

  handleChange(e) {
    const { onChange, value } = this.props;
    const { target } = e;
    const { id, checked } = target;
    this.setState(
      () => ({
        checked,
      }),
      () => onChange(checked ? value : '', id, target),
    );
  }

  render() {
    const { checked } = this.state;
    const { id, children, ...props } = this.props;
    return (
      <React.Fragment>
        <input
          {...props}
          type="checkbox"
          id={id}
          aria-describedby={`${id}Help`}
          checked={checked}
          onChange={this.handleChange}
        />
        <label className="form-check-label" htmlFor={id}>
          {children}
        </label>
      </React.Fragment>
    );
  }
}

CheckBox.propTypes = propTypes;
CheckBox.defaultProps = defaultProps;

export default CheckBox;
