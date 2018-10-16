import React from 'react';
import InputSearch from '_components/InputSearch';

const propTypes = {};

class InputSearchContainer extends React.Component {
  constructor(props) {
    super(props);

    this.onSubmit = this.onSubmit.bind(this);

    this.state = {
      searchType: null,
      searchValue: null,
    };
  }

  onSubmit(searchType, searchValue) {
    this.setState({ searchType, searchValue });
  }

  render() {
    const { searchType, searchValue } = this.state;
    return (
      <div>
        <InputSearch onSubmit={this.onSubmit} />
        <br />
        <div className="alert alert-primary" role="alert">
          <p>
            <span>searchType: </span>
            {searchType}
          </p>
          <p>
            <span>searchValue: </span>
            {searchValue}
          </p>
        </div>
      </div>
    );
  }
}

InputSearchContainer.propTypes = propTypes;

export default InputSearchContainer;
