package org.ainframe;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 24/10/2018
 */

public class EntityTest {
  private List<BasicEmploy> basicEmploys;

  @Before
  public void setup() {
    basicEmploys = new ArrayList<>();
    for (int i = 0; i < 1000000; i++) {
      basicEmploys.add(BasicEmploy.builder().name(i % 2 == 0 ? "a" : "aa").value("b").build());
    }
  }

  @Test
  public void 문제_기본목록을회손함() {
    List<BasicEmploy> employs = ImmutableList.copyOf(basicEmploys);

    BasicEmploy employ = employs.get(1);
    employ.setName("1");
    System.out.println(employ);

    assertNotNull(employ);
    assertEquals(basicEmploys.get(1).getName(), employ.getName());
  }

  @Test
  public void 문제2_기본목록을회손함() {
    List<Employ> employs = Lists.transform(basicEmploys, new Function<BasicEmploy, Employ>() {
      @Override
      public Employ apply(BasicEmploy input) {
        return input;
      }
    });

    BasicEmploy employ = (BasicEmploy) employs.get(1);
    employ.setName("1");
    System.out.println(employ);

    assertEquals(basicEmploys.get(1).getName(), employ.getName());
  }

  @Test
  public void 문제3_기본목록을회손함() {
    List<Employ> employs = Lists.newArrayList(Lists.transform(basicEmploys, new Function<BasicEmploy, Employ>() {
      @Override
      public Employ apply(BasicEmploy input) {
        return BasicEmploy.builder().name(input.getName()).value(input.getValue()).build();
      }
    }));

    BasicEmploy employ = (BasicEmploy) employs.get(1);
    employ.setName("1");
    System.out.println(employ);

    assertNotEquals(basicEmploys.get(1).getName(), employ.getName());
  }
}

interface Employ {
  String getName();
  String getValue();
}

@Builder
@Data
class BasicEmploy implements Employ {
  private String name;
  private String value;
}
