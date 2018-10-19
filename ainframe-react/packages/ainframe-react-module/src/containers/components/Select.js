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

class Select extends React.Component {
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
      <select className="form-control" id="skin">
        <option>선택</option>
      </select>
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

Select.propTypes = propTypes;
Select.defaultProps = defaultProps;

export default Select;
