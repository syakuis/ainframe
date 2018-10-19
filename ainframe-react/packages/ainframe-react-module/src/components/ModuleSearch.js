import React from 'react';
import { InputSearch } from 'ainframe-react-component';

class ModuleSearch extends React.Component {
  constructor(props) {
    super(props);

    this.onKeywordSearchSubmit = this.onKeywordSearchSubmit.bind(this);

    this.state = {
      searchType: '',
      searchValue: '',
    };
  }

  onKeywordSearchSubmit(searchType, searchValue) {
    this.setState({ searchType, searchValue });
  }

  render() {
    const { searchType, searchValue } = this.state;
    return (
      <div className="card">
        <div className="card-body">
          <InputSearch onSubmit={this.onKeywordSearchSubmit} />
          {searchType}
          {searchValue}
        </div>
      </div>
    );
  }
}

export default ModuleSearch;
