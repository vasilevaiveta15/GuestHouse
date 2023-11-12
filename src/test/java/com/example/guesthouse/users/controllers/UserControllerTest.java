package com.example.guesthouse.users.controllers;

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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
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
public class UserControllerTest
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


  @Test
  public void register_user_successfully() throws Exception
  {
    mockMvc.perform(post("/api/v1/register/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                     "
                    +"    \"username\": \"test22\",\n         "
                    +"    \"password\": \"Pass!123\",\n       "
                    +"    \"repeatPassword\": \"Pass!123\",\n "
                    +"    \"role\": \"OWNER\"\n               "
                    +"}                                       "
            ))
        .andExpect(jsonPath("$.username").value("test22"))
        .andExpect(jsonPath("$.role").value("OWNER"))
        .andDo(print())
        .andDo(document("users/add/register_address_successfully"))
        .andExpect(status().isOk());
  }

  @Test
  public void register_user_with_already_exist_username() throws Exception
  {
    mockMvc.perform(post("/api/v1/register/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                     "
                    +"    \"username\": \"test\",\n           "
                    +"    \"password\": \"Pass!123\",\n       "
                    +"    \"repeatPassword\": \"Pass!123\",\n "
                    +"    \"role\": \"OWNER\"\n               "
                    +"}                                       "
            ))
        .andDo(print())
        .andDo(document("users/add/register_user_with_already_exist_username"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void register_user_return_password_dont_match() throws Exception
  {
    mockMvc.perform(post("/api/v1/register/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                     "
                    +"    \"username\": \"test33\",\n         "
                    +"    \"password\": \"Pass123\",\n        "
                    +"    \"repeatPassword\": \"Pass!123\",\n "
                    +"    \"role\": \"OWNER\"\n               "
                    +"}                                       "
            ))
        .andDo(print())
        .andDo(document("users/add/register_user_return_password_dont_match"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void register_user_return_password_must_contain_special_symbol() throws Exception
  {
    mockMvc.perform(post("/api/v1/register/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                     "
                    +"    \"username\": \"test33\",\n         "
                    +"    \"password\": \"Pass123\",\n        "
                    +"    \"repeatPassword\": \"Pass123\",\n  "
                    +"    \"role\": \"OWNER\"\n               "
                    +"}                                       "
            ))
        .andDo(print())
        .andDo(document("users/add/register_user_return_password_must_contain_special_symbol"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void register_user_return_password_must_contain_upper_case() throws Exception
  {
    mockMvc.perform(post("/api/v1/register/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                     "
                    +"    \"username\": \"test33\",\n         "
                    +"    \"password\": \"ass!123\",\n        "
                    +"    \"repeatPassword\": \"ass!123\",\n  "
                    +"    \"role\": \"OWNER\"\n               "
                    +"}                                       "
            ))
        .andDo(print())
        .andDo(document("users/add/register_user_return_password_must_contain_upper_case"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void register_user_return_password_must_contain_minimum_seven_symbols() throws Exception
  {
    mockMvc.perform(post("/api/v1/register/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                     "
                    +"    \"username\": \"test33\",\n         "
                    +"    \"password\": \"Pass!1\",\n         "
                    +"    \"repeatPassword\": \"Pass!1\",\n   "
                    +"    \"role\": \"OWNER\"\n               "
                    +"}                                       "
            ))
        .andDo(print())
        .andDo(document("users/add/register_user_return_password_must_contain_minimum_seven_symbols"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void register_user_return_password_must_contain_digit() throws Exception
  {
    mockMvc.perform(post("/api/v1/register/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                     "
                    +"    \"username\": \"test33\",\n         "
                    +"    \"password\": \"Pass!\",\n          "
                    +"    \"repeatPassword\": \"Pass!\",\n    "
                    +"    \"role\": \"OWNER\"\n               "
                    +"}                                       "
            ))
        .andDo(print())
        .andDo(document("users/add/register_user_return_password_must_contain_digit"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void register_user_return_password_must_not_contain_white_space() throws Exception
  {
    mockMvc.perform(post("/api/v1/register/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                     "
                +"    \"username\": \"test33\",\n             "
                +"    \"password\": \"Pass! 123\",\n          "
                +"    \"repeatPassword\": \"Pass! 123\",\n    "
                +"    \"role\": \"OWNER\"\n                   "
                +"}                                           "
            ))
        .andDo(print())
        .andDo(document("users/add/register_user_return_password_must_not_contain_white_space"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void register_user_return_password_must_contain_lower_case() throws Exception
  {
    mockMvc.perform(post("/api/v1/register/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                     "
                +"    \"username\": \"test33\",\n             "
                +"    \"password\": \"P!12123\",\n            "
                +"    \"repeatPassword\": \"P!12123\",\n      "
                +"    \"role\": \"OWNER\"\n                   "
                +"}                                           "
            ))
        .andDo(print())
        .andDo(document("users/add/register_user_return_password_must_contain_lower_case"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void register_user_return_invalid_role() throws Exception
  {
    mockMvc.perform(post("/api/v1/register/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                     "
                    +"    \"username\": \"test33\",\n         "
                    +"    \"password\": \"Pass!\",\n          "
                    +"    \"repeatPassword\": \"Pass!\",\n    "
                    +"    \"role\": \"ADMIN\"\n               "
                    +"}                                       "
            ))
        .andDo(print())
        .andDo(document("users/add/register_user_return_invalid_role"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void register_user_return_username_must_not_be_empty() throws Exception
  {
    mockMvc.perform(post("/api/v1/register/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                     "
                    +"    \"password\": \"Pass!123\",\n       "
                    +"    \"repeatPassword\": \"Pass!123\",\n "
                    +"    \"role\": \"OWNER\"\n               "
                    +"}                                       "
            ))
        .andDo(print())
        .andDo(document("users/add/register_user_return_username_must_not_be_empty"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void register_user_return_password_must_not_be_empty() throws Exception
  {
    mockMvc.perform(post("/api/v1/register/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                      "
                    +"    \"username\": \"test33\",\n          "
                    +"    \"repeatPassword\": \"Pass!123\",\n  "
                    +"    \"role\": \"OWNER\"\n                "
                    +"}                                        "
            ))
        .andDo(print())
        .andDo(document("users/add/register_user_return_password_must_not_be_empty"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void register_user_return_repeat_password_must_not_be_empty() throws Exception
  {
    mockMvc.perform(post("/api/v1/register/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                     "
                    +"    \"username\": \"test33\",\n         "
                    +"    \"password\": \"Pass!123\",\n       "
                    +"    \"role\": \"OWNER\"\n               "
                    +"}                                       "
            ))
        .andDo(print())
        .andDo(document("users/add/register_user_return_repeat_password_must_not_be_empty"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void register_user_return_role_must_not_be_empty() throws Exception
  {
    mockMvc.perform(post("/api/v1/register/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                    "
                    +"    \"username\": \"test\",\n          "
                    +"    \"password\": \"Pass!123\",\n      "
                    +"    \"repeatPassword\": \"Pass!123\"\n "
                    +"}                                      "
            ))
        .andDo(print())
        .andDo(document("users/add/register_user_return_role_must_not_be_empty"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void register_user_return_invalid_username() throws Exception
  {
    mockMvc.perform(post("/api/v1/register/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                     "
                    +"    \"username\": \"??\",\n             "
                    +"    \"password\": \"Pass!123\",\n       "
                    +"    \"repeatPassword\": \"Pass!123\",\n "
                    +"    \"role\": \"OWNER\"\n               "
                    +"}                                       "
            ))
        .andDo(print())
        .andDo(document("users/add/register_user_return_invalid_username"))
        .andExpect(status().isBadRequest());
  }
}