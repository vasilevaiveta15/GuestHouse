package com.example.guesthouse.guesthouses;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GuestHouseApplication.class)
@AutoConfigureMockMvc
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@Sql(scripts = {"/insert.sql"})
@Transactional
public class GuestHouseControllerTest
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
  public void add_guest_house_successfully() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/guest/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                       "
                    +"    \"nameOfHouse\": \"Serbia House\",\n  "
                    +"    \"kmToTheCapital\": 60,\n             "
                    +"    \"nearestCity\": \"Sofia\",\n         "
                    +"    \"quadrature\": 400,\n                "
                    +"    \"numOfFloors\": 6,\n                 "
                    +"    \"paymentPerDay\": 300\n              "
                    +"}                                         "
            ))
        .andExpect(jsonPath("$.nameOfHouse").value("Serbia House"))
        .andExpect(jsonPath("$.kmToTheCapital").value("60"))
        .andExpect(jsonPath("$.nearestCity").value("Sofia"))
        .andExpect(jsonPath("$.quadrature").value("400"))
        .andExpect(jsonPath("$.numOfFloors").value("6"))
        .andDo(print())
        .andDo(document("guesthouses/add/add_guest_house_successfully"))
        .andExpect(status().isOk());
  }

  @WithUserDetails(value = "test01")
  @Test
  public void add_guest_house_with_forbidden_username() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/guest/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                       "
                    +"    \"nameOfHouse\": \"Serbia House\",\n  "
                    +"    \"kmToTheCapital\": 60,\n             "
                    +"    \"nearestCity\": \"Sofia\",\n         "
                    +"    \"quadrature\": 400,\n                "
                    +"    \"numOfFloors\": 6,\n                 "
                    +"    \"paymentPerDay\": 300\n              "
                    +"}                                         "
            ))
        .andDo(print())
        .andDo(document("guesthouses/add/add_guest_house_with_forbidden_username"))
        .andExpect(status().isForbidden());
  }

  @WithUserDetails(value = "test")
  @Test
  public void add_guest_house_return_name_of_house_must_not_be_empty() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/guest/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                       "
                    +"    \"kmToTheCapital\": 60,\n             "
                    +"    \"nearestCity\": \"Sofia\",\n         "
                    +"    \"quadrature\": 400,\n                "
                    +"    \"numOfFloors\": 6,\n                 "
                    +"    \"paymentPerDay\": 300\n              "
                    +"}                                         "
            ))
        .andDo(print())
        .andDo(document("guesthouses/add/add_guest_house_return_name_of_house_must_not_be_empty"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void add_house_return_km_to_the_capital_less_than_five_digits_before_dot_and_less_than_two_after_dot()
      throws Exception
  {
    mockMvc.perform(post("/api/v1/add/guest/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                         "
                    +"    \"nameOfHouse\": \"Serbia House\",\n    "
                    +"    \"kmToTheCapital\": 98765462.8764445,\n "
                    +"    \"nearestCity\": \"Sofia\",\n           "
                    +"    \"quadrature\": 400,\n                  "
                    +"    \"numOfFloors\": 6,\n                   "
                    +"    \"paymentPerDay\": 300\n                "
                    +"}                                           "
            ))
        .andDo(print())
        .andDo(document("guesthouses/add/add_house_return_km_to_the_capital_less_than_five_digits_before_dot_and_less_than_two_after_dot"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void add_house_return_km_to_the_capital_must_be_greater_than_zero()
      throws Exception
  {
    mockMvc.perform(post("/api/v1/add/guest/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                         "
                    +"    \"nameOfHouse\": \"Serbia House\",\n    "
                    +"    \"kmToTheCapital\": -1,\n               "
                    +"    \"nearestCity\": \"Sofia\",\n           "
                    +"    \"quadrature\": 400,\n                  "
                    +"    \"numOfFloors\": 6,\n                   "
                    +"    \"paymentPerDay\": 300\n                "
                    +"}                                           "
            ))
        .andDo(print())
        .andDo(document("guesthouses/add/add_house_return_km_to_the_capital_must_be_greater_than_zero"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void add_guest_house_return_nearest_city_must_not_be_empty()
      throws Exception
  {
    mockMvc.perform(post("/api/v1/add/guest/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                         "
                    +"    \"nameOfHouse\": \"Serbia House\",\n    "
                    +"    \"kmToTheCapital\": 9,\n                "
                    +"    \"quadrature\": 400,\n                  "
                    +"    \"numOfFloors\": 6,\n                   "
                    +"    \"paymentPerDay\": 300\n                "
                    +"}                                           "
            ))
        .andDo(print())
        .andDo(document("guesthouses/add/add_guest_house_return_nearest_city_must_not_be_empty"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void add_house_return_quadrature_less_than_five_digits_before_dot_and_less_than_two_after_dot()
      throws Exception
  {
    mockMvc.perform(post("/api/v1/add/guest/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                         "
                    +"    \"nameOfHouse\": \"Serbia House\",\n    "
                    +"    \"kmToTheCapital\": 9,\n                "
                    +"    \"nearestCity\": \"Sofia\",\n           "
                    +"    \"quadrature\": 4000987.774363,\n       "
                    +"    \"numOfFloors\": 6,\n                   "
                    +"    \"paymentPerDay\": 300\n                "
                    +"}                                           "
            ))
        .andDo(print())
        .andDo(document("guesthouses/add/add_house_return_quadrature_less_than_five_digits_before_dot_and_less_than_two_after_dot"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void add_house_return_quadrature_must_not_be_less_than_zero()
      throws Exception
  {
    mockMvc.perform(post("/api/v1/add/guest/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                         "
                    +"    \"nameOfHouse\": \"Serbia House\",\n    "
                    +"    \"kmToTheCapital\": 9,\n                "
                    +"    \"nearestCity\": \"Sofia\",\n           "
                    +"    \"quadrature\": -40,\n                  "
                    +"    \"numOfFloors\": 6,\n                   "
                    +"    \"paymentPerDay\": 300\n                "
                    +"}                                           "
            ))
        .andDo(print())
        .andDo(document("guesthouses/add/add_house_return_quadrature_must_not_be_less_than_zero"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void add_house_num_of_floors_return_invalid_input()
      throws Exception
  {
    mockMvc.perform(post("/api/v1/add/guest/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                         "
                    +"    \"nameOfHouse\": \"Serbia House\",\n    "
                    +"    \"kmToTheCapital\": 9,\n                "
                    +"    \"nearestCity\": \"Sofia\",\n           "
                    +"    \"quadrature\": 4,\n                    "
                    +"    \"numOfFloors\": -6,\n                  "
                    +"    \"paymentPerDay\": 300\n                "
                    +"}                                           "
            ))
        .andDo(print())
        .andDo(document("guesthouses/add/add_house_num_of_floors_return_invalid_input"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void add_house_return_payment_per_day_less_than_five_digits_before_dot_and_less_than_two_after_dot()
      throws Exception
  {
    mockMvc.perform(post("/api/v1/add/guest/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                         "
                    +"    \"nameOfHouse\": \"Serbia House\",\n    "
                    +"    \"kmToTheCapital\": 9,\n                "
                    +"    \"nearestCity\": \"Sofia\",\n           "
                    +"    \"quadrature\": 4,\n                    "
                    +"    \"numOfFloors\": 6,\n                   "
                    +"    \"paymentPerDay\": 3004243.43242\n      "
                    +"}                                           "
            ))
        .andDo(print())
        .andDo(document("guesthouses/add/add_house_return_payment_per_day_less_than_five_digits_before_dot_and_less_than_two_after_dot"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void add_house_return_payment_per_day_must_not_be_less_than_zero()
      throws Exception
  {
    mockMvc.perform(post("/api/v1/add/guest/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                         "
                    +"    \"nameOfHouse\": \"Serbia House\",\n    "
                    +"    \"kmToTheCapital\": 9,\n                "
                    +"    \"nearestCity\": \"Sofia\",\n           "
                    +"    \"quadrature\": 4,\n                    "
                    +"    \"numOfFloors\": 6,\n                   "
                    +"    \"paymentPerDay\": -1\n                 "
                    +"}                                           "
            ))
        .andDo(print())
        .andDo(document("guesthouses/add/add_house_return_payment_per_day_must_not_be_less_than_zero"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test1")
  @Test
  public void add_guest_house_return_there_is_no_individual() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/guest/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                       "
                    +"    \"nameOfHouse\": \"Serbia House\",\n  "
                    +"    \"kmToTheCapital\": 60,\n             "
                    +"    \"nearestCity\": \"Sofia\",\n         "
                    +"    \"quadrature\": 400,\n                "
                    +"    \"numOfFloors\": 6,\n                 "
                    +"    \"paymentPerDay\": 300\n              "
                    +"}                                         "
            ))
        .andDo(print())
        .andDo(document("guesthouses/add/add_guest_house_return_there_is_no_individual"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test4")
  @Test
  public void add_guest_house_return_individual_is_not_active() throws Exception
  {
    mockMvc.perform(post("/api/v1/add/guest/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                       "
                    +"    \"nameOfHouse\": \"Serbia House\",\n  "
                    +"    \"kmToTheCapital\": 60,\n             "
                    +"    \"nearestCity\": \"Sofia\",\n         "
                    +"    \"quadrature\": 400,\n                "
                    +"    \"numOfFloors\": 6,\n                 "
                    +"    \"paymentPerDay\": 300\n              "
                    +"}                                         "
            ))
        .andDo(print())
        .andDo(document("guesthouses/add/add_guest_house_return_individual_is_not_active"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test01")
  @Test
  public void rent_house_successfully() throws Exception
  {
    mockMvc.perform(patch("/api/v1/rent/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                             "
                    +"    \"guestHouseId\": 901,\n    "
                    +"    \"numOfRentedDays\": 5\n    "
                    +"}                               "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updaterenthouses/rent_house_successfully"))
        .andExpect(status().isOk());
  }

  @WithUserDetails(value = "test")
  @Test
  public void rent_house_with_forbidden_username() throws Exception
  {
    mockMvc.perform(patch("/api/v1/rent/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                             "
                +"    \"guestHouseId\": 901,\n    "
                +"    \"numOfRentedDays\": 5\n    "
                +"}                               "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updaterenthouses/rent_house_with_forbidden_username"))
        .andExpect(status().isForbidden());
  }

  @WithUserDetails(value = "test01")
  @Test
  public void rent_house_return_invalid_number_of_house() throws Exception
  {
    mockMvc.perform(patch("/api/v1/rent/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                             "
                    +"    \"guestHouseId\": -42,\n    "
                    +"    \"numOfRentedDays\": 5\n    "
                    +"}                               "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updaterenthouses/rent_house_return_invalid_number_of_house"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test01")
  @Test
  public void rent_house_return_number_of_house_must_not_be_empty() throws Exception
  {
    mockMvc.perform(patch("/api/v1/rent/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                             "
                    +"    \"numOfRentedDays\": 5\n    "
                    +"}                               "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updaterenthouses/rent_house_return_number_of_house_must_not_be_empty"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test01")
  @Test
  public void rent_house_return_invalid_number_num_of_input_rented_days() throws Exception
  {
    mockMvc.perform(patch("/api/v1/rent/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                             "
                    +"    \"guestHouseId\":  900,\n   "
                    +"    \"numOfRentedDays\": -5\n   "
                    +"}                               "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updaterenthouses/rent_house_return_invalid_number_num_of_input_rented_days"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test01")
  @Test
  public void rent_house_return_number_num_of_input_rented_days_cannot_be_empty() throws Exception
  {
    mockMvc.perform(patch("/api/v1/rent/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                             "
                    +"    \"guestHouseId\":  42\n     "
                    +"}                               "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updaterenthouses/rent_house_return_number_num_of_input_rented_days_cannot_be_empty"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test02")
  @Test
  public void rent_house_return_there_is_no_individual() throws Exception
  {
    mockMvc.perform(patch("/api/v1/rent/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                             "
                    +"    \"guestHouseId\": 42,\n     "
                    +"    \"numOfRentedDays\": 5\n    "
                    +"}                               "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updaterenthouses/rent_house_return_there_is_no_individual"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test03")
  @Test
  public void rent_house_return_individual_is_not_active() throws Exception
  {
    mockMvc.perform(patch("/api/v1/rent/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                             "
                    +"    \"guestHouseId\": 2,\n      "
                    +"    \"numOfRentedDays\": 5\n    "
                    +"}                               "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updaterenthouses/rent_house_return_individual_is_not_active"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test01")
  @Test
  public void rent_house_return_invalid_house_number() throws Exception
  {
    mockMvc.perform(patch("/api/v1/rent/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                     "
                    +"    \"guestHouseId\": 42222222222222,\n "
                    +"    \"numOfRentedDays\": 5\n            "
                    +"}                                       "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updaterenthouses/rent_house_return_invalid_house_number"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test04")
  @Test
  public void rent_house_return_this_house_already_rented() throws Exception
  {
    mockMvc.perform(patch("/api/v1/rent/house")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                                     "
                    +"    \"guestHouseId\": 900,\n            "
                    +"    \"numOfRentedDays\": 5\n            "
                    +"}                                       "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updaterenthouses/rent_house_return_this_house_already_rented"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void change_payment_per_day_return_successfully() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/payment/per/day")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                          "
                    +"    \"guestHouseId\": 901,\n "
                    +"    \"paymentPerDay\": 44\n  "
                    +"}                            "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updatepayment/change_payment_per_day_return_successfully"))
        .andExpect(status().isOk());
  }

  @WithUserDetails(value = "test01")
  @Test
  public void change_payment_per_day_return_with_forbidden_username() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/payment/per/day")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                          "
                +"    \"guestHouseId\": 901,\n "
                +"    \"paymentPerDay\": 44\n  "
                +"}                            "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updatepayment/change_payment_per_day_return_with_forbidden_username"))
        .andExpect(status().isForbidden());
  }

  @WithUserDetails(value = "test3")
  @Test
  public void change_payment_per_day_return_this_house_is_not_yours() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/payment/per/day")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                           "
                    +"    \"guestHouseId\": 900,\n  "
                    +"    \"paymentPerDay\": 44\n   "
                    +"}                             "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updatepayment/change_payment_per_day_return_this_house_is_not_yours"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test3")
  @Test
  public void change_payment_per_day_return_house_number_cannot_be_empty() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/payment/per/day")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                           "
                    +"    \"paymentPerDay\": 44\n   "
                    +"}                             "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updatepayment/change_payment_per_day_return_house_number_cannot_be_empty"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test3")
  @Test
  public void change_payment_per_day_return_invalid_input_for_payment_per_day() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/payment/per/day")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                           "
                    +"    \"guestHouseId\": 1,\n    "
                    +"    \"paymentPerDay\": -44\n  "
                    +"}                             "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updatepayment/change_payment_per_day_return_invalid_input_for_payment_per_day"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test3")
  @Test
  public void change_payment_per_day_return_payment_cannot_be_empty() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/payment/per/day")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                         "
                    +"    \"guestHouseId\": 41\n  "
                    +"}                           "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updatepayment/change_payment_per_day_return_payment_cannot_be_empty"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void change_payment_per_day_return_invalid_input_for_guest_house_id() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/payment/per/day")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                             "
                    +"    \"guestHouseId\": 41111,\n  "
                    +"    \"paymentPerDay\": 144\n    "
                    +"}                               "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updatepayment/change_payment_per_day_return_invalid_input_for_guest_house_id"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test1")
  @Test
  public void change_payment_per_day_return_there_is_no_individual() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/payment/per/day")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                             "
                    +"    \"guestHouseId\": 41111,\n  "
                    +"    \"paymentPerDay\": 144\n    "
                    +"}                               "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updatepayment/change_payment_per_day_return_there_is_no_individual"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test4")
  @Test
  public void change_payment_per_day_return_individual_is_not_active() throws Exception
  {
    mockMvc.perform(patch("/api/v1/change/payment/per/day")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n                             "
                +"    \"guestHouseId\": 41111,\n  "
                +"    \"paymentPerDay\": 144\n    "
                +"}                               "
            ))
        .andDo(print())
        .andDo(document("guesthouses/updatepayment/change_payment_per_day_return_individual_is_not_active"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void release_house_successfully() throws Exception
  {
    mockMvc.perform(patch("/api/v1/release/house/900"))
        .andDo(print())
        .andDo(document("guesthouses/updatereleasehouse/release_house_successfully"))
        .andExpect(status().isOk());
  }

  @WithUserDetails(value = "test")
  @Test
  public void release_house_with_invalid_house_number() throws Exception
  {
    mockMvc.perform(patch("/api/v1/release/house/0"))
        .andDo(print())
        .andDo(document("guesthouses/updatereleasehouse/release_house_with_invalid_house_number"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test1")
  @Test
  public void release_house_return_there_is_no_individual() throws Exception
  {
    mockMvc.perform(patch("/api/v1/release/house/900"))
        .andDo(print())
        .andDo(document("guesthouses/updatereleasehouse/release_house_return_there_is_no_individual"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test4")
  @Test
  public void release_house_individual_is_not_active() throws Exception
  {
    mockMvc.perform(patch("/api/v1/release/house/900"))
        .andDo(print())
        .andDo(document("guesthouses/updatereleasehouse/release_house_individual_is_not_active"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test4")
  @Test
  public void release_with_string() throws Exception
  {
    mockMvc.perform(patch("/api/v1/release/house/{houseNumber}", "hello"))
        .andDo(print())
        .andDo(document("guesthouses/updatereleasehouse/release_with_string"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void release_house_invalid_house_number() throws Exception
  {
    mockMvc.perform(patch("/api/v1/release/house/800"))
        .andDo(print())
        .andDo(document("guesthouses/updatereleasehouse/release_house_invalid_house_number"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test3")
  @Test
  public void release_house_return_house_is_not_yours() throws Exception
  {
    mockMvc.perform(patch("/api/v1/release/house/900"))
        .andDo(print())
        .andDo(document("guesthouses/updatereleasehouse/release_house_invalid_house_number"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void release_house_return_house_is_not_rented_yet() throws Exception
  {
    mockMvc.perform(patch("/api/v1/release/house/901"))
        .andDo(print())
        .andDo(document("guesthouses/updatereleasehouse/release_house_return_house_is_not_rented_yet"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test01")
  @Test
  public void release_house_return_is_forbidden() throws Exception
  {
    mockMvc.perform(patch("/api/v1/release/house/900"))
        .andDo(print())
        .andDo(document("guesthouses/updatereleasehouse/release_house_return_is_forbidden"))
        .andExpect(status().isForbidden());
  }

  @WithUserDetails(value = "test")
  @Test
  public void delete_guest_house_with_string_house_number() throws Exception
  {
    mockMvc.perform(delete("/api/v1/remove/house/{houseNumber}","hello"))
        .andDo(print())
        .andDo(document("guesthouses/deleteguesthouse/delete_guest_house_with_string_house_number"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void delete_guest_house_return_successfully() throws Exception
  {
    mockMvc.perform(delete("/api/v1/remove/house/900"))
        .andDo(print())
        .andDo(document("guesthouses/deleteguesthouse/delete_guest_house_return_successfully"))
        .andExpect(status().isOk());
  }

  @WithUserDetails(value = "test")
  @Test
  public void delete_guest_house_return_invalid_house_number() throws Exception
  {
    mockMvc.perform(delete("/api/v1/remove/house/0"))
        .andDo(print())
        .andDo(document("guesthouses/deleteguesthouse/delete_guest_house_return_invalid_house_number"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test1")
  @Test
  public void delete_guest_house_return_there_is_no_individual() throws Exception
  {
    mockMvc.perform(delete("/api/v1/remove/house/900"))
        .andDo(print())
        .andDo(document("guesthouses/deleteguesthouse/delete_guest_house_return_there_is_no_individual"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test4")
  @Test
  public void delete_guest_house_return_individual_is_not_active() throws Exception
  {
    mockMvc.perform(delete("/api/v1/remove/house/900"))
        .andDo(print())
        .andDo(document("guesthouses/deleteguesthouse/delete_guest_house_return_individual_is_not_active"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test4")
  @Test
  public void delete_guest_house_invalid_house_number() throws Exception
  {
    mockMvc.perform(delete("/api/v1/remove/house/800"))
        .andDo(print())
        .andDo(document("guesthouses/deleteguesthouse/delete_guest_house_invalid_house_number"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test3")
  @Test
  public void delete_guest_house_return_house_is_not_yours() throws Exception
  {
    mockMvc.perform(delete("/api/v1/remove/house/900"))
        .andDo(print())
        .andDo(document("guesthouses/deleteguesthouse/delete_guest_house_return_house_is_not_yours"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test01")
  @Test
  public void delete_guest_house_return_isForbidden() throws Exception
  {
    mockMvc.perform(delete("/api/v1/remove/house/900"))
        .andDo(print())
        .andDo(document("guesthouses/deleteguesthouse/delete_guest_house_return_isForbidden"))
        .andExpect(status().isForbidden());
  }

  @WithUserDetails(value = "test")
  @Test
  public void search_by_criteria_return_successfully() throws Exception
  {
    mockMvc.perform(get("/api/v1/search/by/criteria")
            .queryParam("pageNumber","0")
            .queryParam("pageCapacity","10")
        )
        .andDo(print())
        .andDo(document("guesthouses/searchbycriteria/search_by_criteria_return_successfully"))
        .andExpect(status().isOk());
  }

  @WithUserDetails(value = "test")
  @Test
  public void search_by_criteria_return_page_number_invalid_input() throws Exception
  {
    mockMvc.perform(get("/api/v1/search/by/criteria")
        .queryParam("pageNumber","-1")
        .queryParam("pageCapacity","10")
    )
        .andDo(print())
        .andDo(document("guesthouses/searchbycriteria/search_by_criteria_return_page_number_invalid_input"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void search_by_criteria_return_page_capacity_invalid_input_min() throws Exception
  {
    mockMvc.perform(get("/api/v1/search/by/criteria")
                .queryParam("pageNumber","1")
                .queryParam("pageCapacity","-10")
            )
        .andDo(print())
        .andDo(document("guesthouses/searchbycriteria/search_by_criteria_return_page_capacity_invalid_input_min"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void search_by_criteria_return_page_capacity_invalid_input_max() throws Exception
  {
    mockMvc.perform(get("/api/v1/search/by/criteria")
            .queryParam("pageNumber","1")
            .queryParam("pageCapacity","102")
        )
        .andDo(print())
        .andDo(document("guesthouses/searchbycriteria/search_by_criteria_return_page_capacity_invalid_input_max"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void search_by_criteria_return_quadrature_must_has_before_dot_five_numbers_and_after_two_number() throws Exception
  {
    mockMvc.perform(get("/api/v1/search/by/criteria")
            .queryParam("pageNumber","1")
            .queryParam("pageCapacity","10")
            .queryParam("quadrature","1232133.343344")
        )
        .andDo(print())
        .andDo(document("guesthouses/searchbycriteria/search_by_criteria_return_quadrature_must_has_before_dot_five_numbers_and_after_two_number"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void search_by_criteria_return_invalid_input_page_capacity_must_not_be_negative() throws Exception
  {
    mockMvc.perform(get("/api/v1/search/by/criteria")
            .queryParam("pageNumber","1")
            .queryParam("pageCapacity","0"))
        .andDo(print())
        .andDo(document("guesthouses/searchbycriteria/search_by_criteria_return_invalid_input_page_capacity_must_not_be_negative"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void search_by_criteria_return_invalid_num_of_floors() throws Exception
  {
    mockMvc.perform(get("/api/v1/search/by/criteria")
            .queryParam("pageNumber","1")
            .queryParam("pageCapacity","10")
            .queryParam("numOfFloors","-1")
        )
        .andDo(print())
        .andDo(document("guesthouses/searchbycriteria/search_by_criteria_return_invalid_num_of_floors"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void search_by_criteria_return_minPayment_must_has_before_dot_five_numbers_and_after_two_number() throws Exception
  {
    mockMvc.perform(get("/api/v1/search/by/criteria")
            .queryParam("pageNumber","1")
            .queryParam("pageCapacity","10")
            .queryParam("minPayment","1232133.343344")
        )
        .andDo(print())
        .andDo(document("guesthouses/searchbycriteria/search_by_criteria_return_minPayment_must_has_before_dot_five_numbers_and_after_two_number"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void search_by_criteria_return_invalid_input_minPayment_must_not_be_negative_number() throws Exception
  {
    mockMvc.perform(get("/api/v1/search/by/criteria")
            .queryParam("pageNumber","1")
            .queryParam("pageCapacity","10")
            .queryParam("minPayment","0"))
        .andDo(print())
        .andDo(document("guesthouses/searchbycriteria/search_by_criteria_return_invalid_input_minPayment_must_not_be_negative_number"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void search_by_criteria_return_maxPayment_must_has_before_dot_five_numbers_and_after_two_number() throws Exception
  {
    mockMvc.perform(get("/api/v1/search/by/criteria")
            .queryParam("pageNumber","1")
            .queryParam("pageCapacity","10")
            .queryParam("maxPayment","1232133.343344"))
        .andDo(print())
        .andDo(document("guesthouses/searchbycriteria/search_by_criteria_return_maxPayment_must_has_before_dot_five_numbers_and_after_two_number"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void search_by_criteria_return_invalid_input_maxPayment_must_not_be_negative_number() throws Exception
  {
    mockMvc.perform(get("/api/v1/search/by/criteria")
            .queryParam("pageNumber","1")
            .queryParam("pageCapacity","10")
            .queryParam("maxPayment","0"))
        .andDo(print())
        .andDo(document("guesthouses/searchbycriteria/search_by_criteria_return_invalid_input_maxPayment_must_not_be_negative_number"))
        .andExpect(status().isBadRequest());
  }

  @WithUserDetails(value = "test")
  @Test
  public void search_by_criteria_return_is_ok() throws Exception
  {
    mockMvc.perform(get("/api/v1/search/by/criteria")
            .queryParam("pageNumber","1")
            .queryParam("pageCapacity","10")
            .queryParam("nearestCity","Brussels")
        )
        .andDo(print())
        .andDo(document("guesthouses/searchbycriteria/search_by_criteria_return_is_ok"))
        .andExpect(status().isOk());
  }
}