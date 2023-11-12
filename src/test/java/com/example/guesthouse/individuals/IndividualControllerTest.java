package com.example.guesthouse.individuals;

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
public class IndividualControllerTest
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

  @WithUserDetails(value = "test1")
  @Test
  public void add_individual_successfully() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/id/card")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                           "
                    +"    \"firstName\": \"test\",\n                "
                    +"    \"middleName\": \"test\",\n               "
                    +"    \"lastName\": \"test\",\n                 "
                    +"    \"cardNumber\": \"4044221845739170\",\n   "
                    +"    \"birthDate\": \"2000-24-04\",\n          "
                    +"    \"phoneNumber\": \"0878152289\",\n        "
                    +"    \"email\": \"test@abv.bg\"\n              "
                    +"}                                             "
            ))
        .andExpect(jsonPath("$.firstName").value("test"))
        .andExpect(jsonPath("$.middleName").value("test"))
        .andExpect(jsonPath("$.lastName").value("test"))
        .andExpect(jsonPath("$.cardNumber").value("4044221845739170"))
        .andExpect(jsonPath("$.birthDate").value("2001-12-04T00:00:00.000+00:00"))
        .andExpect(jsonPath("$.phoneNumber").value("0878152289"))
        .andExpect(jsonPath("$.email").value("test@abv.bg"))
        .andDo(print())
        .andDo(document("individuals/add/add_individual_successfully"))
        .andExpect(status().isOk());
  }

  @WithUserDetails(value = "test")
  @Test
  public void add_individual_already_have_individual() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/id/card")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                           "
                    +"    \"firstName\": \"test\",\n                "
                    +"    \"middleName\": \"test\",\n               "
                    +"    \"lastName\": \"test\",\n                 "
                    +"    \"cardNumber\": \"4044221845739170\",\n   "
                    +"    \"birthDate\": \"2000-24-04\",\n          "
                    +"    \"phoneNumber\": \"0878152289\",\n        "
                    +"    \"email\": \"test@abv.bg\"\n              "
                    +"}                                             "
            ))
        .andDo(print())
        .andDo(document("individuals/add/add_individual_already_have_individual"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test1")
  @Test
  public void add_individual_return_first_name_must_not_be_empty() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/id/card")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                           "
                    +"    \"middleName\": \"test\",\n               "
                    +"    \"lastName\": \"test\",\n                 "
                    +"    \"cardNumber\": \"4044221845739170\",\n   "
                    +"    \"birthDate\": \"2000-24-04\",\n          "
                    +"    \"phoneNumber\": \"0878152289\",\n        "
                    +"    \"email\": \"test@abv.bg\"\n              "
                    +"}                                             "
            ))
        .andDo(print())
        .andDo(document("individuals/add/add_individual_return_first_name_must_not_be_empty"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test1")
  @Test
  public void add_individual_return_middle_name_must_not_be_empty() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/id/card")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                           "
                    +"    \"firstName\": \"test\",\n                "
                    +"    \"lastName\": \"test\",\n                 "
                    +"    \"cardNumber\": \"4044221845739170\",\n   "
                    +"    \"birthDate\": \"2000-24-04\",\n          "
                    +"    \"phoneNumber\": \"0878152289\",\n        "
                    +"    \"email\": \"test@abv.bg\"\n              "
                    +"}                                             "
            ))
        .andDo(print())
        .andDo(document("individuals/add/add_individual_return_middle_name_must_not_be_empty"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test1")
  @Test
  public void add_individual_return_last_name_must_not_be_empty() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/id/card")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                           "
                    +"    \"firstName\": \"test\",\n                "
                    +"    \"middleName\": \"test\",\n               "
                    +"    \"cardNumber\": \"4044221845739170\",\n   "
                    +"    \"birthDate\": \"2000-24-04\",\n          "
                    +"    \"phoneNumber\": \"0878152289\",\n        "
                    +"    \"email\": \"test@abv.bg\"\n              "
                    +"}                                             "
            ))
        .andDo(print())
        .andDo(document("individuals/add/add_individual_return_last_name_must_not_be_empty"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test1")
  @Test
  public void add_individual_return_card_number_must_not_be_empty() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/id/card")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                           "
                    +"    \"firstName\": \"test\",\n                "
                    +"    \"middleName\": \"test\",\n               "
                    +"    \"lastName\": \"test\",\n                 "
                    +"    \"birthDate\": \"2000-24-04\",\n          "
                    +"    \"phoneNumber\": \"0878152289\",\n        "
                    +"    \"email\": \"test@abv.bg\"\n              "
                    +"}                                             "
            ))
        .andDo(print())
        .andDo(document("individuals/add/add_individual_return_card_number_must_not_be_empty"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test1")
  @Test
  public void add_individual_return_card_number_is_invalid() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/id/card")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                           "
                    +"    \"firstName\": \"test\",\n                "
                    +"    \"middleName\": \"test\",\n               "
                    +"    \"lastName\": \"test\",\n                 "
                    +"    \"cardNumber\": \"439170\",\n             "
                    +"    \"birthDate\": \"2000-24-04\",\n          "
                    +"    \"phoneNumber\": \"0878152289\",\n        "
                    +"    \"email\": \"test@abv.bg\"\n              "
                    +"}                                             "
            ))
        .andDo(print())
        .andDo(document("individuals/add/add_individual_return_card_number_is_invalid"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test1")
  @Test
  public void add_individual_return_email_must_not_be_empty() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/id/card")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                           "
                    +"    \"firstName\": \"Toni\",\n                "
                    +"    \"middleName\": \"Petkov\",\n             "
                    +"    \"lastName\": \"Vasilev\",\n              "
                    +"    \"cardNumber\": \"4280521845739170\",\n   "
                    +"    \"birthDate\": \"2000-24-04\",\n          "
                    +"    \"phoneNumber\": \"0876509089\"\n         "
                    +"}                                             "
            ))
        .andDo(print())
        .andDo(document("individuals/add/add_individual_return_email_must_not_be_empty"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test1")
  @Test
  public void add_individual_return_email_is_invalid() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/id/card")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                           "
                    +"    \"firstName\": \"test\",\n                "
                    +"    \"middleName\": \"test\",\n               "
                    +"    \"lastName\": \"test\",\n                 "
                    +"    \"cardNumber\": \"4044221845739170\",\n   "
                    +"    \"birthDate\": \"2000-24-04\",\n          "
                    +"    \"phoneNumber\": \"0878152289\",\n        "
                    +"    \"email\": \"testabv.bg\"\n               "
                    +"}                                             "
            ))
        .andDo(print())
        .andDo(document("individuals/add/add_individual_return_email_is_invalid"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test1")
  @Test
  public void add_individual_return_card_number_already_exist() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/id/card")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                           "
                    +"    \"firstName\": \"test\",\n                "
                    +"    \"middleName\": \"test\",\n               "
                    +"    \"lastName\": \"test\",\n                 "
                    +"    \"cardNumber\": \"4000000045739170\",\n   "
                    +"    \"birthDate\": \"2000-24-04\",\n          "
                    +"    \"phoneNumber\": \"0878152289\",\n        "
                    +"    \"email\": \"test@abv.bg\"\n               "
                    +"}                                             "
            ))
        .andDo(print())
        .andDo(document("individuals/add/add_individual_return_card_number_already_exist"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test1")
  @Test
  public void add_individual_return_invalid_phone_number() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/id/card")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                           "
                    +"    \"firstName\": \"test\",\n                "
                    +"    \"middleName\": \"test\",\n               "
                    +"    \"lastName\": \"test\",\n                 "
                    +"    \"cardNumber\": \"4000600045739170\",\n   "
                    +"    \"birthDate\": \"2000-24-04\",\n          "
                    +"    \"phoneNumber\": \"--878152289\",\n       "
                    +"    \"email\": \"test@abv.bg\"\n              "
                    +"}                                             "
            ))
        .andDo(print())
        .andDo(document("individuals/add/add_individual_return_invalid_phone_number"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void change_individual_successfully() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/individual")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                           "
                    +"    \"phoneNumber\": \"00359878152289\",\n    "
                    +"    \"email\": \"test.testing@abv.bg\"\n      "
                    +"}                                             "
            ))
        .andDo(print())
        .andDo(document("individuals/update/change_individual_successfully"))
        .andExpect(status().isOk());
  }

  @WithUserDetails(value = "test")
  @Test
  public void change_individual_return_phone_number_must_not_be_empty() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/individual")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                           "
                    +"    \"email\": \"test.testing@abv.bg\"\n      "
                    +"}                                             "
            ))
        .andDo(print())
        .andDo(document("individuals/update/change_individual_return_phone_number_must_not_be_empty"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void change_individual_return_email_must_not_be_empty() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/individual")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                   "
                    +"    \"phoneNumber\": \"0878152256\"\n "
                    +"}                                     "
            ))
        .andDo(print())
        .andDo(document("individuals/update/change_individual_return_email_must_not_be_empty"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void change_individual_return_email_is_invalid() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/individual")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                     "
                    +"    \"phoneNumber\": \"0878152256\",\n  "
                    +"    \"email\": \"testmail.com\"\n       "
                    +"}                                       "
            ))
        .andDo(print())
        .andDo(document("individuals/update/change_individual_return_email_is_invalid"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test1")
  @Test
  public void change_individual_return_there_is_no_individual() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/individual")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                           "
                    +"    \"phoneNumber\": \"00359878152289\",\n    "
                    +"    \"email\": \"test.testing@abv.bg\"\n      "
                    +"}                                             "
            ))
        .andDo(print())
        .andDo(document("individuals/update/change_individual_return_there_is_no_individual"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test4")
  @Test
  public void change_individual_return_individual_not_active() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/individual")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                           "
                    +"    \"phoneNumber\": \"00359878152289\",\n    "
                    +"    \"email\": \"test.testing@abv.bg\"\n      "
                    +"}                                             "
            ))
        .andDo(print())
        .andDo(document("individuals/update/change_individual_return_individual_not_active"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void change_individual_return_invalid_phone_number() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/individual")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                      "
                    +"    \"phoneNumber\": \"--0878152256\",\n "
                    +"    \"email\": \"test@mail.com\"\n       "
                    +"}                                        "
            ))
        .andDo(print())
        .andDo(document("individuals/update/change_individual_return_invalid_phone_number"))
        .andExpect(status().isBadRequest());
  }
}