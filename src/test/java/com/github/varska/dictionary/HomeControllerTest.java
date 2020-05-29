package com.github.varska.dictionary;

import com.github.varska.dictionary.controller.HomeController;
import com.github.varska.dictionary.entity.User;
import com.github.varska.dictionary.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin")
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HomeController homeController;

    @Autowired
    private UserService userService;

    @Test
    public void addWord() throws Exception{

//        this.mockMvc.perform(get("/add"))
//                .andDo(print())
//                .andExpect(authenticated())
//                .andExpect(xpath("//*[@id=\"navbarNav\"]/ul/li[3]/a/span").string("admin"))
//                .andExpect()
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString()))
        ;

        MockHttpServletRequestBuilder multipart = multipart("/add")
                .param("title", "someTitle")
                .param("definition", "someDefinition")
                .param("example", "someExample");

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())

        ;

    }

    @Test
    public void checkUsers() throws Exception{
        List<User> allUsers = userService.findAll();

        assertNotNull(allUsers);
    }

}
