package com.example.coach;
import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.example.coach.model.User;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class WebAppTestEnvironement {

    @Resource
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    @Qualifier("customUserDetailsService")
    protected UserDetailsService userDetailsService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    protected DataSource dataSource;

    protected MockMvc mockMvc;

    protected MockHttpSession mockHttpSession;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());



    protected UsernamePasswordAuthenticationToken getPrincipal(String username) {

        UserDetails user = this.userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        user,
                        user.getPassword(),
                        user.getAuthorities());
        return authentication;
    }

    @Before
    public void setupMockMvcAndMockHttpSession() throws NamingException {

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .addFilters(this.springSecurityFilterChain)
                .build();
        this.mockHttpSession = getMockHttpSessionForUser("test_user");
    }

    public MockHttpSession getMockHttpSessionForUser(String login){
        UsernamePasswordAuthenticationToken principal =
                this.getPrincipal(login);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new MockSecurityContext(principal));
        return session;
    }

    public static class MockSecurityContext implements SecurityContext {

        private static final long serialVersionUID = -1386535243513362694L;

        private Authentication authentication;

        public MockSecurityContext(Authentication authentication) {
            this.authentication = authentication;
        }

        @Override
        public Authentication getAuthentication() {
            return this.authentication;
        }

        @Override
        public void setAuthentication(Authentication authentication) {
            this.authentication = authentication;
        }
    }

}


