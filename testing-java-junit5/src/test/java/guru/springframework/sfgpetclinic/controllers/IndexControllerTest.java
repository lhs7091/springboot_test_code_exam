package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @Test
    void index() {
        assertEquals("index", controller.index());
        assertNotEquals("indexed", controller.index());
    }

    @Test
    void oupsHandler() {
//        assertTrue("notimplemented".equals(controller.oupsHandler()));
//        assertFalse("notimplemente".equals(controller.oupsHandler()));
        assertThrows(ValueNotFoundException.class, ()->{
            controller.oupsHandler();
        });
    }
}