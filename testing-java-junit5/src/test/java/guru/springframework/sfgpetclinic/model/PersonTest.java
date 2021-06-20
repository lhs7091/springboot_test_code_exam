package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    void groupedAssertions() {
        // given
        Person person = new Person(1l, "Joe", "Buck");
        // then
        assertAll("Test Props Set",
                ()->assertEquals(person.getFirstName(), "Joe"),
                ()->assertEquals(person.getLastName(), "Buck"));
    }

    @Test
    void groupedAssertionsMsg() {
        // given
        Person person = new Person(1l, "Joe", "Buck");
        // then
        assertAll("Test Props Set",
                ()->assertEquals(person.getFirstName(), "Joe", "First name Failed"),
                ()->assertEquals(person.getLastName(), "Buck", "Last name Failed"));
    }
}