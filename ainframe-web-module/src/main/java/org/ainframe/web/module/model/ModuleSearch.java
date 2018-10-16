package org.ainframe.web.module.view.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 27.
 */
@NoArgsConstructor
@AllArgsConstructor
public class ModuleSearch {
  @Getter
  private SearchType searchType = SearchType.NONE;
  @Getter
  private String searchValue;

  public enum SearchType {
    NONE, MODULE_NAME, MODULE_ID, BROWSER_TITLE
  }
}
