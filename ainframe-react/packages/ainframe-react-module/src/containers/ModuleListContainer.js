import React from 'react';
import { Link } from 'react-router-dom';
import ModuleSearch from '_components/ModuleSearch';
import ModuleList from '_components/ModuleList';

class ModuleListContainer extends React.Component {
  constructor(props) {
    super(props);

    this.state = {};
  }

  render() {
    return (
      <div>
        <ModuleSearch />
        <p />
        <Link className="btn btn-primary" to="/form" role="button">
          추가
        </Link>
        <ModuleList />
      </div>
    );
  }
}

export default ModuleListContainer;
