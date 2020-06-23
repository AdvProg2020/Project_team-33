package Model;

import Model.Users.Person;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {
    Person person1 = new Person("Ross", "Geller", "ross1967",
            "12345678", "981070", "ross1967@gmail.com");
    Person person2 = new Person("Leonard","Cohen","cohen22",
            "1234leonard56","9108256775","blueraincoat@yahoo.com");


    @Test
    public void changeName() {
        person1.changeName("Monica");
        assertEquals("Monica", person1.getName());
    }

    @Test
    public void changeFamily() {
        person1.changeFamily("Green");
        assertEquals("Green", person1.getFamily());
    }

    @Test
    public void changePassword() {
        person1.changePassword("87654321");
        assertEquals("87654321", person1.getPassword());
    }

    @Test
    public void changeEmail() {
        person1.changeEmail("Monica1970@yahoo.com");
        assertEquals("Monica1970@yahoo.com", person1.getEmail());
    }

    @Test
    public void changePhone() {
        person1.changePhone("09126783579");
        assertEquals("09126783579", person1.getPhone());
    }

    @Test
    public void getPersonByUsername() {
        Person resultPerson = Person.getPersonByUsername("nothing21");
        assertEquals(null, resultPerson);
    }

    @Test
    public void isAccountWithThisUsernameExist() {
        boolean result = Person.isAccountWithThisUsernameExist("mohammad21");
        assertFalse(result);
    }

    @Test
    public void getPeople() {
        assertEquals(Person.getPeople(), Person.people);
    }

    @Test
    public void deleteUser() {
        Person.deleteUser("ross1967");
        assertEquals(false, Person.isAccountWithThisUsernameExist("ross1967"));
    }

    @Test
    public void testToString() {
        String actualResult = person2.toString();
        String expectedResult = "Person{" +
                "name='" + "Leonard" + '\'' +
                ", username='" + "cohen22" + '\'' +
                ", email='" + "blueraincoat@yahoo.com" + '\'' +
                ", phone='" + "9108256775" + '\'' +
                ", family='" + "Cohen" +
                '}';
        assertEquals(expectedResult,actualResult);
    }
}