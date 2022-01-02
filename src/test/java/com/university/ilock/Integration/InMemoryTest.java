package com.university.ilock.Integration;

import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.classic.methods.*;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.*;
import com.university.ilock.*;
import com.university.ilock.Repository.*;
import com.university.ilock.model.*;
import com.university.ilock.service.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.web.client.*;
import org.springframework.boot.web.server.*;
import org.springframework.http.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.web.servlet.*;
import org.springframework.web.client.*;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.sql.*;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
@SpringBootTest
@ActiveProfiles("test")
public class InMemoryTest {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        Class.forName("org.h2.Driver");

        Connection conn = DriverManager.
                getConnection("jdbc:h2:mem:test;MODE=MySQL;IGNORECASE=TRUE;INIT=RUNSCRIPT FROM 'src/test/resources/test.sql'","sa","");

    }

    @Test
    public void test() throws Exception {
        String body = mvc.perform(get("http://localhost:8080/api/pin/validate?deviceId=1&pin=141414")).andDo(print()).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assert(body.equals("true"));
    }
}
