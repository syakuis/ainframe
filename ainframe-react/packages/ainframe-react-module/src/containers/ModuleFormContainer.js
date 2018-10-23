import React from 'react';
import {
  Input,
  SelectBox,
  CheckBox,
  AddButton,
  RemoveButton,
  SaveButton,
  DeleteButton,
  SettingButton,
} from 'ainframe-react-component';

class ModuleFormContainer extends React.Component {
  constructor(props) {
    super(props);

    this.handleChange = this.handleChange.bind(this);

    this.state = {
      moduleName: '',
      moduleId: '',
      browserTitle: '',
      layoutIdx: '',
      skin: '',
      onlyUseTheme: '',
      optionName: '',
      optionValue: '',
      optionTitle: '',
    };
  }

  handleChange(value, id) {
    this.setState({ [id]: value });
  }

  render() {
    const {
      moduleName,
      moduleId,
      browserTitle,
      layoutIdx,
      skin,
      onlyUseTheme,
      optionName,
      optionValue,
      optionTitle,
    } = this.state;
    return (
      <form>
        <div className="form-group">
          <label htmlFor="moduleName">모듈명</label>
          <Input
            onChange={this.handleChange}
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
            onChange={this.handleChange}
            value={moduleId}
            id="moduleId"
            placeholder="모듈ID를 입력하세요."
          />
        </div>
        <div className="form-group">
          <label htmlFor="browserTitle">브라우저 타이틀</label>
          <Input
            onChange={this.handleChange}
            value={browserTitle}
            id="browserTitle"
            placeholder="모듈ID를 입력하세요."
          />
        </div>
        <div className="form-group">
          <label htmlFor="layoutIdx">레이아웃 선택</label>
          <SelectBox id="layoutIdx" onChange={this.handleChange} value={layoutIdx}>
            <option value="1">선택</option>
            <option value="2">선택2</option>
          </SelectBox>
        </div>
        <div className="form-group">
          <label htmlFor="skin">스킨 선택</label>
          <SelectBox id="skin" onChange={this.handleChange} value={skin}>
            <option value="1">선택</option>
          </SelectBox>
        </div>
        <div className="form-group form-check">
          <CheckBox
            id="onlyUseTheme"
            onChange={this.handleChange}
            value="Y"
            checked={onlyUseTheme === 'Y'}
          >
            테마 사용여부
          </CheckBox>
        </div>

        <h4 className="mt-5">
          <i className="fas fa-cog" />
          &nbsp;옵션 관리
        </h4>
        <ul className="list-group">
          <li className="list-group-item">
            <div className="row">
              <div className="col-2">
                <AddButton onlyUseIcon btnSize="sm" />
                &nbsp;
                <strong>기능</strong>
              </div>
              <div className="col-3">
                <strong>이름</strong>
              </div>
              <div className="col-3">
                <strong>값</strong>
              </div>
              <div className="col-4">
                <strong>타이틀</strong>
              </div>
            </div>
          </li>

          <li className="list-group-item">
            <div className="row">
              <div className="col-2">
                <div className="btn-group btn-group-sm" role="group">
                  <SettingButton onlyUseIcon />
                  <RemoveButton onlyUseIcon />
                </div>
              </div>
              <div className="col-3">
                <Input size="sm" onChange={this.handleChange} value={optionName} id="optionName" />
              </div>
              <div className="col-3">
                <Input
                  size="sm"
                  onChange={this.handleChange}
                  value={optionValue}
                  id="optionValue"
                />
              </div>
              <div className="col-4">
                <Input
                  size="sm"
                  onChange={this.handleChange}
                  value={optionTitle}
                  id="optionTitle"
                />
              </div>
            </div>
          </li>
        </ul>

        <div className="mt-3 text-right">
          <SaveButton />
        </div>
      </form>
    );
  }
}

export default ModuleFormContainer;
