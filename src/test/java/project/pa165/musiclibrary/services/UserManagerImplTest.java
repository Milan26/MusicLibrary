package project.pa165.musiclibrary.services;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import project.pa165.musiclibrary.entities.User;

/**
 *
 * @author Milan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserManagerImplTest {

    @Autowired
    private UserManager userManager;

    /**
     * Test of createUser method, of class UserServiceImpl.
     */
    @Test
    public void testCreateUser() {

        User user = createUser("John", "Doe", "john@doe.com", "p4ssword");

        assertNull(user.getId());
        userManager.createUser(user);
        assertNotNull(user.getId());
    }

    /**
     *
     */
    @Test
    public void testCreatedUserProperties() {

        User user = createUser("John", "Doe", "john@doe.com", "p4ssword");

        assertNull(user.getId());
        userManager.createUser(user);
        Long id = user.getId();
        assertNotNull(id);

        User actual = userManager.findUser(id);
        assertEquals(user.getFirstName(), actual.getFirstName());
        assertEquals(user.getLastName(), actual.getLastName());
        assertEquals(user.getEmail(), actual.getEmail());
        assertEquals(user.getPassword(), actual.getPassword());
    }

    /**
     * Test of deleteUser method, of class UserServiceImpl.
     */
    @Test
    public void testDeleteUser() {

        User user = createUser("John", "Doe", "john@doe.com", "p4ssword");

        assertNull(user.getId());
        userManager.createUser(user);
        userManager.deleteUser(user.getId());
        assertNull(userManager.findUser(user.getId()));
    }

    /**
     * Test of updateUser method, of class UserServiceImpl.
     */
    @Test
    public void testUpdateUser() {

        User user = createUser("John", "Doe", "john@doe.com", "p4ssword");

        assertNull(user.getId());
        userManager.createUser(user);
        assertNotNull(user.getId());

        Long id = user.getId();
        User actual = userManager.findUser(id);

        user.setEmail("john@doel.com");
        user.setPassword("p4sswordddd");

        userManager.updateUser(user);
        actual = userManager.findUser(id);

        assertNotNull(actual.getEmail());
        assertNotNull(actual.getPassword());

        assertEquals(user, actual);
    }

    /**
     *
     */
    @Test
    public void testFindUserOnEmptyDb() {
        assertNull(userManager.findUser(1l));
    }

    /**
     * Test of findUser method, of class UserServiceImpl.
     */
    @Test
    public void testFindUser() {

        User user = createUser("John", "Doe", "john@doe.com", "p4ssword");

        assertNull(user.getId());
        userManager.createUser(user);

        assertNotNull(user.getId());
        assertNotNull(userManager.findUser(1l));

        assertEquals(user, userManager.findUser(1l));
    }

    /**
     * Test of getAllUsers method, of class UserManagerImpl.
     */
    @Test
    public void testGetAllUsers() {

        User user1 = createUser("John", "Doe", "john@doe.com", "p4ssword");
        User user2 = createUser("Jane", "Doe", "jane@doe.com", "passw0rd");

        assertNull(user1.getId());
        assertNull(user2.getId());
        userManager.createUser(user1);
        userManager.createUser(user2);
        assertEquals(userManager.getAllUsers().size(), 2);

        List<User> expected = new ArrayList<>();
        expected.add(user1);
        expected.add(user2);
        List<User> actual = userManager.getAllUsers();

        assertArrayEquals(expected.toArray(), actual.toArray());
        deepAssert(expected.toArray(), actual.toArray());
    }
    
    private User createUser(String firstName, String secondName, String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(firstName);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    private void deepAssert(Object[] arr1, Object[] arr2) {
        assertEquals(arr1.length, arr2.length);

        for (int i = 0; i < arr1.length; i++) {
            User user1 = (User) arr1[i];
            User user2 = (User) arr2[i];
            assertEquals(user1.getId(), user2.getId());
            assertEquals(user1.getFirstName(), user2.getFirstName());
            assertEquals(user1.getLastName(), user2.getLastName());
            assertEquals(user1.getEmail(), user2.getEmail());
            assertEquals(user1.getPassword(), user2.getPassword());
        }
    }

}
