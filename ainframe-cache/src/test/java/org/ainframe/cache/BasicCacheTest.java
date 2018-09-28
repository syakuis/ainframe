package org.ainframe.cache;

import lombok.extern.slf4j.Slf4j;
import org.ainframe.cache.service.BasicCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 10.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicCacheTest {
    @Resource(name = "basicCacheService")
    private BasicCacheService cacheServiceTest;

    @Test
    public void test() throws InterruptedException {
        // 현재 시간을 캐시에 저장한다.
        long date = cacheServiceTest.getDate();
        log.debug("date: {}", date);
        Thread.sleep(500);
        long date0 = cacheServiceTest.getDate();
        log.debug("date0: {}", date0);
        assertEquals(date, date0);
        Thread.sleep(500);
        // 저장된 캐시를 삭제하고 현재 시간을 캐시에 저장한다.
        cacheServiceTest.evict();
        long date2 = cacheServiceTest.getDate();
        log.debug("date2: {}", date2);
        assertNotEquals(date, date2);
        Thread.sleep(500);
        // 저장되어 있는 캐시를 얻는 다. (이미 저장되어 있는 데이터가 있기 때문에)
        assertEquals(date2, cacheServiceTest.getDate());
        Thread.sleep(500);
        // 저장되어 있는 캐시를 현재 시간으로 갱신한다.
        long date3 = cacheServiceTest.put();
        log.debug("date3: {}", date3);
        assertNotEquals(date2, date3);
        Thread.sleep(500);
        // 저장되어 있는 캐시를 얻는 다. (이미 저장되어 있는 데이터가 있기 때문에)
        assertEquals(date3, cacheServiceTest.getDate());
        Thread.sleep(500);
        // 캐시 갱신기간이 만료되어 삭제되었다. 그리고 현재 시간을 캐시에 저장한다.
        assertNotEquals(date3, cacheServiceTest.getDate());
    }
}
