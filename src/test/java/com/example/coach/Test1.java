package com.example.coach;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;



public class Test1 extends WebAppTestEnvironement {



    @Test
    public void signedIn() throws Exception {

        UsernamePasswordAuthenticationToken principal =
                this.getPrincipal("arli");

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new MockSecurityContext(principal));


        super.mockMvc
                .perform(
                        get("/main")
                                .session(session))
                .andExpect(status().isOk());
    }
}

