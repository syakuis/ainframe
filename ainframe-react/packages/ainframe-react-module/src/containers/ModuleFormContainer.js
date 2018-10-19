import React from 'react';
import Input from './components/Input';

class ModuleFormContainer extends React.Component {
  constructor(props) {
    super(props);

    this.onChangeInput = this.onChangeInput.bind(this);

    this.state = {
      moduleName: '',
      moduleId: '',
    };
  }

  onChangeInput(value, id) {
    this.setState({ [id]: value });
  }

  render() {
    const { moduleName, moduleId, browserTitle } = this.state;
    return (
      <form>
        <div className="form-group">
          <label htmlFor="moduleName">모듈명</label>
          <Input
            onChange={this.onChangeInput}
            value={moduleName}
            id="moduleName"
            placeholder="모듈명을 입력하세요."
          />
          <small id="moduleNameHelp" className="form-text text-muted">
            입력하세요!
          </small>
        </div>
        <div className="form-group">
          <label htmlFor="moduleId">모듈ID</label>
          <Input
            onChange={this.onChangeInput}
            value={moduleId}
            id="moduleId"
            placeholder="모듈ID를 입력하세요."
          />
        </div>
        <div className="form-group">
          <label htmlFor="browserTitle">브라우저 타이틀</label>
          <Input
            onChange={this.onChangeInput}
            value={browserTitle}
            id="browserTitle"
            placeholder="모듈ID를 입력하세요."
          />
        </div>
        <div className="form-group">
          <label htmlFor="layoutIdx">레이아웃 선택</label>
          <select className="form-control" id="layoutIdx">
            <option>선택</option>
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="skin">스킨 선택</label>
          <select className="form-control" id="skin">
            <option>선택</option>
          </select>
        </div>
        <div className="form-group form-check">
          <input type="checkbox" className="form-check-input" id="onlyUseTheme" />
          <label className="form-check-label" htmlFor="onlyUseTheme">
            테마 사용여부
          </label>
        </div>

        <h4 className="mt-5">
          <i className="fas fa-cog" />
          &nbsp;옵션 관리
        </h4>
        <ul className="list-group">
          <li className="list-group-item">
            <div className="row">
              <div className="col-2">
                <button type="button" className="btn btn-secondary btn-sm">
                  <i className="fas fa-plus" />
                </button>
                &nbsp;
                <strong>기능</strong>
              </div>
              <div className="col-2">
                <strong>이름</strong>
              </div>
              <div className="col-2">
                <strong>값</strong>
              </div>
              <div className="col-6">
                <strong>타이틀</strong>
              </div>
            </div>
          </li>

          <li className="list-group-item">
            <div className="row">
              <div className="col-2">
                <div className="btn-group btn-group-sm" role="group">
                  <button type="button" className="btn btn-secondary">
                    <i className="fas fa-cog" />
                  </button>
                  <button type="button" className="btn btn-danger">
                    <i className="fas fa-times" />
                  </button>
                </div>
              </div>
              <div className="col-2">
                <input type="text" className="form-control form-control-sm" id="browserTitle" />
              </div>
              <div className="col-6">
                <input type="text" className="form-control form-control-sm" id="browserTitle" />
              </div>
              <div className="col-2">
                <input type="text" className="form-control form-control-sm" id="browserTitle" />
              </div>
            </div>
          </li>
        </ul>

        <div className="mt-3 text-right">
          <button type="button" className="btn btn-success">
            <i className="fas fa-check" />
            &nbsp;저장
          </button>
        </div>
      </form>
    );
  }
}

export default ModuleFormContainer;
