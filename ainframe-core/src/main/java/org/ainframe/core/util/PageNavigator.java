package org.ainframe.core.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2009. 9. 22.
 */
@Slf4j
public class PageNavigator {
    /**
     * 총 게시물 수
     */
    @Getter
    private long totalCount;
    /**
     * 현재 페이지 번호
     */
    @Getter
    private int page = 1;
    /**
     * 노출될 목록 수
     */
    @Getter
    private int pageRow = 10;
    /**
     * 노출될 페이지 링크 수
     */
    @Getter
    private int pageLink = 10;
    /**
     * 총 페이지 수
     */
    @Getter
    private int totalPage;
    /**
     * 현재 페이지 기준 시작 페이지 번호
     */
    @Getter
    private int startPage;
    /**
     * 현재 페이지 기준 마지막 페이지 번호
     */
    @Getter
    private int endPage;
    /**
     * 데이터 조회를 위한 시작 row num
     */
    @Getter
    private long startRowNum;
    /**
     * 데이터 조회를 위한 마지막 row num
     */
    @Getter
    private long endRowNum;
    /**
     * 목록에 순차적으로 번호를 누출하기 위한 가상 번호
     */
    @Getter
    private long virtualIdx;

    public PageNavigator() {
    }

    public PageNavigator(int pageRow, int pageLink) {
        this.pageRow = pageRow;
        this.pageLink = pageLink;
    }

    public static PageNavigator computed(int page, long totalCount) {
        return computed(page, totalCount, 10, 10);
    }

    public static PageNavigator computed(int page, long totalCount, int pageRow, int pageLink) {
        PageNavigator navigator = new PageNavigator();
        navigator.totalPage = (int) Math.ceil((double) totalCount / (double) pageRow);
        navigator.startPage = (int) Math.ceil(((page - 1) / pageLink) * pageLink + 1);
        navigator.endPage = navigator.startPage + pageLink - 1;

        navigator.virtualIdx = navigator.totalCount - (long) (pageRow * (page - 1));
        // oracle start rowNum = 0
        //virtualIdx = virtualIdx + 1;

        /*
         * databases select row
         * oracle endRow <= row, startRow > row
         * mysql limit startRow, pageRow
         */
        navigator.startRowNum = (page - 1) * pageRow;
        navigator.endRowNum = navigator.startRowNum + pageRow; // oracle only

        navigator.page = page;
        navigator.totalCount = totalCount;
        navigator.pageRow = pageRow;
        navigator.pageLink = pageLink;

        return navigator;
    }
}
