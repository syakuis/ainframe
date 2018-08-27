package org.ainframe.web.module.service.model;

import lombok.Getter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 27.
 */
public class ModuleSearch {
    @Getter
    private SearchType searchType;
    @Getter
    private String searchValue;

    public ModuleSearch() {
        this.searchType = SearchType.NONE;
    }

    public ModuleSearch(SearchType searchType, String searchValue) {
        this.searchType = searchType;
        this.searchValue = searchValue;
    }

    public enum SearchType {
        NONE, MODULE_NAME, MODULE_ID, BROWSER_TITLE
    }
}
