package org.ainframe.core.io;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * PathMatchingResourcePatternResolver 를 이용하여 여러개의 패턴을 처리한다.
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2015. 10. 1.
 * @see PathMatchingResourcePatternResolver
 */
@Slf4j
public class PathsMatchingResourceResolver implements ResourcePatternResolver {
  private final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

  @Override
  public ClassLoader getClassLoader() {
    return resolver.getClassLoader();
  }

  @Override
  public Resource getResource(String location) {
    return resolver.getResource(location);
  }

  @Override
  public Resource[] getResources(String locationPattern) throws IOException {
    return getResources(new String[]{locationPattern});
  }

  public Resource[] getResources(String[] locationPattern) throws IOException {
    List<Resource> listResource = new ArrayList<>();

    for (String path : locationPattern) {
      Resource[] resources = resolver.getResources(path);
      for (Resource resource : resources) {
        if (resource.exists()) {
          listResource.add(resource);
          log.debug("The resource can find the {} and can add it. {}",
              resource.getFilename(), resource.getURL());
        } else {
          log.debug("The resource can not find the {} and can not add it. {}",
              resource.getFilename(), resource);
        }
      }
    }

    return listResource.toArray(new Resource[listResource.size()]);
  }
}