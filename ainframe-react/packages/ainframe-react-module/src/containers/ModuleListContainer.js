import React from 'react';
import ModuleSearch from '_components/ModuleSearch';
import ModuleList from '_components/ModuleList';
import * as ModuleService from '../services/ModuleService';

class ModuleListContainer extends React.Component {
  constructor(props) {
    super(props);

    this.state = {};

    ModuleService.getModuleList();
  }

  render() {
    return (
      <div>
        <ModuleSearch />
        <p />
        <ModuleList />
      </div>
    );
  }
}

export default ModuleListContainer;
