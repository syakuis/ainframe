<#list demos as demo>
    ${demo.subject}
</#list>

<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">제목</th>
      <th scope="col">등록일</th>
    </tr>
  </thead>
  <tbody>
    <#list demos as demo>
    <tr>
      <th scope="row">1</th>
      <td>${demo.subject}</td>
      <td>${demo.creationDate}</td>
    </tr>
    </#list>
  </tbody>
</table>