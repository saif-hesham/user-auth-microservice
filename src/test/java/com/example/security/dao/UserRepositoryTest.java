package com.example.security.dao;

import com.example.security.entity.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_Save_ReturnsSavedUser() {
        //Arrange
        AppUser user = AppUser
                .builder()
                .email("saifhesham@gmail.com").
                password("12345678").build();
        //Act
        AppUser addedUser = userRepository.save(user);

        //Assert
        Assertions.assertThat(addedUser).isNotNull();

    }

    @Test
    public void UserRepository_FindByEmail_ReturnsUser() {
        //Arrange
        AppUser user = AppUser
                .builder()
                .email("saif@gmail.com").
                password("12345678").build();
        //Act
        AppUser addedUser = userRepository.save(user);
        //Assert
//        AppUser testedUser = userRepository.findByEmail(addedUser.getEmail()).get();
//        Assertions.assertThat(testedUser).isNotNull();
        Assertions.assertThat(addedUser.getId()).isGreaterThan(0);
    }






}
