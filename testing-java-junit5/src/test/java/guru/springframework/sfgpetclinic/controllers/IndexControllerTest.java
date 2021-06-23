package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

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

    @Test
    void testTimeOut() {
        assertTimeout(Duration.ofMillis(100), ()->{
            Thread.sleep(10);
        });
    }

    @Test
    void testTimeOutPrempt() {
        assertThrows(AssertionFailedError.class, ()->{
            assertTimeout(Duration.ofMillis(100), ()->{
                Thread.sleep(1000);
            });
        });
    }

    /**
     * 가정(assumption)API를 활용하여 의도한 결과가 아닌 경우 해당 테스트를 중단
     */
    @Test
    void testAssumptionTrue1(){
        assumeTrue("test".equalsIgnoreCase(System.getenv("test")));
    }

    @Test
    void testAssumptionTrue2(){
        assumeTrue("test".equalsIgnoreCase("test"));
    }
}