package com.example.guesthouse.addresses;

import com.example.guesthouse.GuestHouseApplication;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GuestHouseApplication.class)
@AutoConfigureMockMvc
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@Sql(scripts = {"/insert.sql"})
@Transactional
public class AddressControllerTest
{
  @Autowired
  public MockMvc mockMvc;

  @Autowired
  public WebApplicationContext webApplicationContext;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders
        .webAppContextSetup(webApplicationContext)
        .apply(springSecurity())
        .build();
  }

  @WithUserDetails(value = "test")
  @Test
  public void add_address_successfully() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/address")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                  "
                    +"    \"country\": \"Belgia\",\n       "
                    +"    \"city\": \"Brussels\",\n        "
                    +"    \"district\": \"Brussels\"\n     "
                    +"}                                    "
            ))
        .andExpect(jsonPath("$.country").value("Belgia"))
        .andExpect(jsonPath("$.city").value("Brussels"))
        .andExpect(jsonPath("$.district").value("Brussels"))
        .andDo(print())
        .andDo(document("address/add/add_address_successfully"))
        .andExpect(status().isOk());
  }

  @WithUserDetails(value = "test1")
  @Test
  public void add_address_there_is_no_individual() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/address")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                  "
                    +"    \"country\": \"Belgia\",\n       "
                    +"    \"city\": \"Brussels\",\n        "
                    +"    \"district\": \"Brussels\"\n     "
                    +"}                                    "
            ))
        .andDo(print())
        .andDo(document("address/add/add_address_there_is_no_individual"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test4")
  @Test
  public void add_address_individual_is_not_active() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/address")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                  "
                    +"    \"country\": \"Belgia\",\n       "
                    +"    \"city\": \"Brussels\",\n        "
                    +"    \"district\": \"Brussels\"\n     "
                    +"}                                    "
            ))
        .andDo(print())
        .andDo(document("address/add/add_address_individual_is_not_active"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void add_address_with_empty_country() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/address")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                  "
                    +"    \"city\": \"Brussels\",\n        "
                    +"    \"district\": \"Brussels\"\n     "
                    +"}                                    "
            ))
        .andDo(print())
        .andDo(document("address/add/add_address_with_empty_country"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void add_address_with_empty_city() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/address")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                  "
                    +"    \"country\": \"Belgia\",\n       "
                    +"    \"district\": \"Brussels\"\n     "
                    +"}                                    "
            ))
        .andDo(print())
        .andDo(document("address/add/add_address_with_empty_city"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void add_address_with_empty_district() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/address")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                 "
                    +"    \"country\": \"Belgia\",\n      "
                    +"    \"city\": \"Brussels\"\n        "
                    +"}                                   "
            ))
        .andDo(print())
        .andDo(document("address/add/add_address_with_empty_district"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void update_address_successfully() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/address")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                  "
                    +"    \"country\": \"Serbia\",\n       "
                    +"    \"city\": \"Nish\",\n            "
                    +"    \"district\": \"Nish\"\n         "
                    +"}                                    "
            ))
        .andDo(print())
        .andDo(document("address/update/update_address_successfully"))
        .andExpect(status().isOk());
  }

  @WithUserDetails(value = "test1")
  @Test
  public void update_address_there_is_no_individual() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/address")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                  "
                    +"    \"country\": \"Serbia\",\n       "
                    +"    \"city\": \"Nish\",\n            "
                    +"    \"district\": \"Nish\"\n         "
                    +"}                                    "
            ))
        .andDo(print())
        .andDo(document("address/update/update_address_there_is_no_individual"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test4")
  @Test
  public void update_address_individual_no_active() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/address")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                  "
                    +"    \"country\": \"Serbia\",\n       "
                    +"    \"city\": \"Nish\",\n            "
                    +"    \"district\": \"Nish\"\n         "
                    +"}                                    "
            ))
        .andDo(print())
        .andDo(document("address/update/update_address_individual_no_active"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test3")
  @Test
  public void update_address_not_exist() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/address")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                  "
                    +"    \"country\": \"Serbia\",\n       "
                    +"    \"city\": \"Nish\",\n            "
                    +"    \"district\": \"Nish\"\n         "
                    +"}                                    "
            ))
        .andDo(print())
        .andDo(document("address/update/update_address_not_exist"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void update_address_with_empty_country() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/address")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                  "
                    +"    \"city\": \"Nish\",\n            "
                    +"    \"district\": \"Nish\"\n         "
                    +"}                                    "
            ))
        .andDo(print())
        .andDo(document("address/update/update_address_with_empty_country"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void update_address_with_empty_city() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/address")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                  "
                    +"    \"country\": \"Serbia\",\n       "
                    +"    \"district\": \"Nish\"\n         "
                    +"}                                    "
            ))
        .andDo(print())
        .andDo(document("address/update/update_address_with_empty_city"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void update_address_with_empty_district() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/address")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                  "
                    +"    \"country\": \"Serbia\",\n       "
                    +"    \"city\": \"Nish\"\n             "
                    +"}                                    "
            ))
        .andDo(print())
        .andDo(document("address/update/update_address_with_empty_district"))
        .andExpect(status().isBadRequest());
  }
}