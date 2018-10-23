<h4 className="mt-5">
          <i className="fas fa-cog" />
          &nbsp;옵션 관리
        </h4>
        <div className="form-row">
          <div className="form-group col-1">
            <label htmlFor="inputEmail4">기능</label>
          </div>
          <div className="form-group col-3">
            <label htmlFor="optionName">이름</label>
          </div>
          <div className="form-group col-3">
            <label htmlFor="optionValue">값</label>
          </div>
          <div className="form-group col">
            <label htmlFor="optionTitle">타이틀</label>
          </div>
        </div>
        <div className="form-row">
          <div className="col-1">
            <div className="btn-group btn-group-sm" role="group">
              <SettingButton onlyUseIcon />
              <RemoveButton onlyUseIcon />
            </div>
          </div>
          <div className="col-3">
            <Input size="sm" onChange={this.handleChange} value={optionName} id="optionName" />
          </div>
          <div className="col-3">
            <Input size="sm" onChange={this.handleChange} value={optionValue} id="optionValue" />
          </div>
          <div className="col">
            <Input size="sm" onChange={this.handleChange} value={optionTitle} id="optionTitle" />
          </div>
        </div>