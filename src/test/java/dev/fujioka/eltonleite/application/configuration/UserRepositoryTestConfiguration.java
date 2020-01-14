//package dev.fujioka.eltonleite.application.configuration;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.mockito.Mockito;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
//
//import dev.fujioka.eltonleite.domain.model.user.User;
//import dev.fujioka.eltonleite.infrastructure.persistence.hibernate.repository.UserRepository;
//
//@Profile("test")
//@Configuration
//public class UserRepositoryTestConfiguration {
//    
//    @BeforeEach
//    void setMockOutput() {
//        User user = new User("teste", "teste", LocalDate.of(1991, 6, 18));
//        when(userRepository().save(any(User.class))).thenReturn(user);
//    }
//
//    @Bean
//    @Primary
//    public UserRepository userRepository() {
//        return Mockito.mock(UserRepository.class);
//    }    
//    
//}
