import React from 'react';

class ModuleList extends React.Component {
  constructor(props) {
    super(props);

    this.state = {};
  }

  render() {
    return (
      <table className="table">
        <colgroup>
          <col width="100" />
          <col width="100" />
          <col width="100" />
          <col />
          <col width="100" />
        </colgroup>
        <thead>
          <tr>
            <th scope="col">기능</th>
            <th scope="col">모듈</th>
            <th scope="col">모듈ID</th>
            <th scope="col">브라우저 타이틀</th>
            <th scope="col">기본디자인</th>
            <th scope="col">레이아웃</th>
            <th scope="col">스킨</th>
            <th scope="col">등록일</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th scope="row">1</th>
            <td>moduleName</td>
            <td>moduleId</td>
            <td>browserTitle</td>
            <td>s</td>
            <td>layoutIdx?if_exists</td>
            <td>skin?if_exists</td>
            <td>regDate?string</td>
          </tr>
        </tbody>
      </table>
    );
  }
}

export default ModuleList;
