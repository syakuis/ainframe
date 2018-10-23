import React from 'react';
import PropTypes from 'prop-types';

const propTypes = {
  onSubmit: PropTypes.func.isRequired,
  template: PropTypes.shape({
    placeholder: PropTypes.string,
  }),
};

const defaultProps = {
  template: {
    placeholder: '검색어를 입력하세요.',
  },
};

/**
 * 키워드 검색 컴포넌트
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @class InputSearch
 * @extends {React.Component}
 */
class InputSearch extends React.Component {
  constructor(props) {
    super(props);

    this.onSearchType = this.onSearchType.bind(this);
    this.onSearchValue = this.onSearchValue.bind(this);
    this.onEnter = this.onEnter.bind(this);
    this.onSubmit = this.onSubmit.bind(this);

    this.state = {
      searchType: '',
      searchValue: '',
    };
  }

  onSearchType(e) {
    this.setState({ searchType: e.target.value });
  }

  onSearchValue(e) {
    this.setState({ searchValue: e.target.value });
  }

  onSubmit() {
    const { onSubmit } = this.props;
    const { searchType, searchValue } = this.state;
    onSubmit(searchType, searchValue);
  }

  onEnter(e) {
    if (e.key === 'Enter') {
      this.onSubmit();
    }
  }

  render() {
    const { template } = this.props;
    const { searchType, searchValue } = this.state;
    return (
      <div className="input-group">
        <div className="input-group-prepend">
          <select className="form-control" value={searchType} onChange={this.onSearchType}>
            <option value="">선택</option>
            <option value="subject">제목</option>
            <option value="content">내용</option>
            <option value="username">작성자</option>
          </select>
          <span>&nbsp;</span>
        </div>
        <input
          type="text"
          className="form-control"
          placeholder={template.placeholder}
          value={searchValue}
          onKeyPress={this.onEnter}
          onChange={this.onSearchValue}
        />
        <div className="input-group-append">
          <button className="btn btn-outline-secondary" type="button" onClick={this.onSubmit}>
            <i className="fa fa-search" />
            &nbsp;검색
          </button>
        </div>
      </div>
    );
  }
}

InputSearch.propTypes = propTypes;
InputSearch.defaultProps = defaultProps;

export default InputSearch;
