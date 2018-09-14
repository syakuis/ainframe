# Cache

```
compile 'org.ainframe:ainframe-cache'
```

application.properties

```
ainframe.cache.ehcache.config = ehcache.xml
ainframe.cache.ehcache.cacheConfig = cache.xml
ainframe.cache.ehcache.charset = UTF-8
```

cache.xml

```xml
<caches>
    <cache name="test"
           maxEntriesLocalHeap="10000"
           timeToIdleSeconds="0"
           timeToLiveSeconds="9"
           eternal="false"
           memoryStoreEvictionPolicy="LFU">
        <persistence strategy="localTempSwap" />
    </cache>
</caches>
```