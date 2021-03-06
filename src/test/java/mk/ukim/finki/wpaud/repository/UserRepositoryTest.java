package mk.ukim.finki.wpaud.repository;

import mk.ukim.finki.wpaud.model.Role;
import mk.ukim.finki.wpaud.model.User;
import mk.ukim.finki.wpaud.model.projections.UserProjection;
import mk.ukim.finki.wpaud.repository.jpa.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAll() {
        List<User> userList = this.userRepository.findAll();
        Assert.assertEquals(2L, userList.size());
    }

    @Test
    public void testFetch() {
        List<User> userList = this.userRepository.fetchAll();
        Assert.assertEquals(2L, userList.size());
    }

    @Test
    public void testLoad() {
        List<User> userList = this.userRepository.loadAll();
        Assert.assertEquals(2L, userList.size());
    }

    @Test
    public void testProjectUsernameAndNameAndSurname() {
        UserProjection userProjection = this.userRepository.findByRole(Role.ROLE_USER);
        Assert.assertEquals("user", userProjection.getUsername());
        Assert.assertEquals("user", userProjection.getName());
        Assert.assertEquals("user", userProjection.getSurname());
    }
}
