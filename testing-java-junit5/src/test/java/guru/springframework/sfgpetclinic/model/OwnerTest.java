package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {
    @Test
    @DisplayName("Dependent Assertions")
    void dependentAssertions() {
        Owner owner = new Owner(1l, "Joe", "Buck");
        owner.setCity("key west");
        owner.setTelephone("12345678");

        assertAll("Properties Test",
                ()->assertAll("Person Properties",
                        ()->assertEquals("Joe", owner.getFirstName()),
                        ()->assertEquals("Buck", owner.getLastName())),
                ()->assertAll(
                        ()->assertEquals("key west", owner.getCity()),
                        ()->assertEquals("12345678", owner.getTelephone())
                )
        );
    }
}