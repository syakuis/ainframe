import React from 'react';
import PropTypes from 'prop-types';

const propTypes = {
  className: PropTypes.string,
  id: PropTypes.string.isRequired,
  value: PropTypes.string,
  onChange: PropTypes.func.isRequired,
  children: PropTypes.node.isRequired,
  useEmptyOption: PropTypes.bool,
};
const defaultProps = {
  className: 'form-control',
  value: '',
  useEmptyOption: true,
};

class SelectBox extends React.Component {
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
    const { id, value, className, children, useEmptyOption, ...props } = this.props;
    return (
      <select
        {...props}
        id={id}
        aria-describedby={`${id}Help`}
        className={className}
        value={value}
        onChange={this.onChange}
      >
        {useEmptyOption ? <option>선택</option> : null}
        {children}
      </select>
    );
  }
}

SelectBox.propTypes = propTypes;
SelectBox.defaultProps = defaultProps;

export default SelectBox;
