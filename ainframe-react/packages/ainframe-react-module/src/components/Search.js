import React from 'react';

class Search extends React.Component {
  constructor(props) {
    super(props);

    this.state = {};
  }

  render() {
    return (
      <div className="search">
        <form
          className="form-inline"
          onSubmit={evt => {
            evt.preventDefault();
            onSearch();
          }}
        >
          <div className="well well-sm">
            <ul className="list-inline">
              <li>
                
              </li>
            </ul>
          </div>
        </form>
      </div>
    );
  }
}

export default Search;
