package com.example.coach.repository;

import com.example.coach.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    UserRepository userRepository;
    @Test
    public void whenFindByLogin_thenReturnUser(){
        //given
        User user = new User("name", "surname", "password", "usersLogin");
        user.setId(1001L);
        entityManager.persist(user);
        entityManager.flush();

        //when
        User found =  userRepository.findByLogin(user.getLogin()).get();

        //then

        assertThat(found.getLogin())
                .isEqualTo(user.getLogin());
    }
}
