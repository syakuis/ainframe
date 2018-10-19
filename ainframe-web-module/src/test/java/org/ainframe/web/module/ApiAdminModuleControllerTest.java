package org.ainframe.web.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ainframe.web.module.domain.ModuleEntity;
import org.ainframe.web.module.service.ViewModuleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ApiAdminModuleControllerTest {
  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  @Autowired
  private WebApplicationContext wac;

  @Autowired
  private ViewModuleService viewModuleService;

  @Before
  public void before() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    this.objectMapper = new ObjectMapper();
  }

  @Test
  public void crud() throws Exception {
    String api = "/api/v1/admin/module";

    this.mockMvc.perform(
      post(api + "")
      .content(objectMapper.writeValueAsString(
        ModuleEntity.builder().moduleId("demo").moduleName("demo").browserTitle("demo").build()))
      .contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    )
      .andExpect(status().isOk())
      .andDo(print())
      .andExpect(content().contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)));

    ModuleEntity moduleEntity = viewModuleService.getModuleByModuleId("demo");
    assertNotNull(moduleEntity);

    this.mockMvc.perform(
      get(api + "/{id}", moduleEntity.getModuleIdx())
      .contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    )
      .andExpect(status().isOk())
      .andDo(print())
      .andExpect(content().contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE)))
      .andExpect(content().json(objectMapper.writeValueAsString(moduleEntity)));
  }
}
