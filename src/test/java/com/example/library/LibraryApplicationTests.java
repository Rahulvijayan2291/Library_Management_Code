package com.example.library;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class LibraryApplicationTests {

        @Autowired
        private MockMvc mvc;

        @Test
        @Order(1)
        public void getNullBooks() throws Exception {
                mvc.perform(get("/book/get")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().is(400));
        }

        @Test
        @Order(2)
        public void createBooks() throws Exception {
                mvc.perform(post("/book/add")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(getBookDetails("To Kill a Mockingbird", "Harper Lee",
                                                "A novel set in the American South during the 1930s, focusing on the trial of a black man accused of raping a white woman.",
                                                "Fiction", 1960, 3, "J. B. Lippincott & Co.",
                                                "https://via.placeholder.com/150", "9780061120084").toJSONString()))
                                .andExpect(MockMvcResultMatchers.status().is(201));
        }

        public JSONObject getBookDetails(String title, String author, String description, String genre,
                        int publicationYear, int copiesAvailable, String publisher, String coverImageUrl, String ISBN) {
                Map<String, Object> map = new HashMap<>();
                map.put("title", title);
                map.put("author", author);
                map.put("description", description);
                map.put("genre", genre);
                map.put("publicationYear", publicationYear);
                map.put("copiesAvailable", copiesAvailable);
                map.put("publisher", publisher);
                map.put("coverImageUrl", coverImageUrl);
                map.put("ISBN", ISBN);
                return new JSONObject(map);
        }

        @Test
        @Order(3)
        public void getFullBooks() throws Exception {
                MvcResult result = mvc.perform(get("/book/get")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isOk())
                                .andReturn();

                String content = result.getResponse().getContentAsString();
                System.out.println("Response JSON: " + content); // Print the response JSON for debugging

                mvc.perform(get("/book/get")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.[0].title", containsStringIgnoringCase("To Kill a Mockingbird")))
                                .andExpect(jsonPath("$.[0].author", containsStringIgnoringCase("Harper Lee")))
                                .andExpect(jsonPath("$.[0].description", containsStringIgnoringCase(
                                                "A novel set in the American South during the 1930s, focusing on the trial of a black man accused of raping a white woman.")))
                                .andExpect(jsonPath("$.[0].genre", containsStringIgnoringCase("Fiction")))
                                .andExpect(jsonPath("$.[0].publicationYear").value(1960))
                                .andExpect(jsonPath("$.[0].copiesAvailable").value(3))
                                .andExpect(jsonPath("$.[0].publisher",
                                                containsStringIgnoringCase("J. B. Lippincott & Co.")))
                                .andExpect(jsonPath("$.[0].coverImageUrl",
                                                containsStringIgnoringCase("https://via.placeholder.com/150")))
                                .andExpect(jsonPath("$.[0].ISBN").exists()); // Ensure ISBN field exists
        }

        @Test
        @Order(4)
        public void updateBookWithId() throws Exception {
                mvc.perform(put("/book/1")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(updateBook(5).toJSONString()))
                                .andExpect(MockMvcResultMatchers.status().isOk());

                mvc.perform(get("/book/get")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.[0].copiesAvailable").value(5));
        }

        public JSONObject updateBook(int copiesAvailable) {
                Map<String, Object> map = new HashMap<>();
                map.put("copiesAvailable", copiesAvailable);
                return new JSONObject(map);
        }

        @Test
        @Order(5)
        public void deleteBookById() throws Exception {
                mvc.perform(delete("/book/1")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andReturn();
        }
}
