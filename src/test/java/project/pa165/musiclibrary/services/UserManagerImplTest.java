package project.pa165.musiclibrary.services;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.joda.time.LocalDate;
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
 * @author Martin
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

        User user = new User();
        
        user.setFirstName("John");
        user.setLastName("Doe");

        assertNull(user.getId());
        userManager.createUser(user);
        assertNotNull(user.getId());
    }

    /**
     *
     */
    @Test
    public void testCreatedUserProperties() {

        User user = new User();
        
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@doe.com");
        user.setPassword("p4ssword");

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
        
        User user = new User();
        
        user.setFirstName("John");
        user.setLastName("Doe");

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
        
        User user = new User();
        
        user.setFirstName("John");
        user.setLastName("Doe");
        
        assertNull(user.getId());
        userManager.createUser(user);
        assertNotNull(user.getId());

        Long id = user.getId();
        User actual = userManager.findUser(id);
        assertNull(actual.getEmail());
        assertNull(actual.getPassword());

        user.setEmail("john@doe.com");
        user.setPassword("p4ssword");

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
        
        User user = new User();
        
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@doe.com");
        user.setPassword("p4ssword");
        
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
        
        User user1 = new User();
        
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john@doe.com");
        user1.setPassword("p4ssword");
        
        User user2 = new User();
        
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setEmail("jane@doe.com");
        user2.setPassword("passw0rd");
        
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
