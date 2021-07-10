package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class VetControllerTest {

    VetService vetService;
    SpecialtyService specialtyService;
    VetController vetController;

    @BeforeEach
    void setUp() {
        specialtyService = new SpecialityMapService();
        vetService = new VetMapService(specialtyService);
        vetController = new VetController(vetService);
    }

    @Test
    void listVets() {
        // given
        Vet vet1 = new Vet(1L, "test1", "a", null);
        Vet vet2 = new Vet(2L, "test2", "b", null);

        // when
        vetService.save(vet1);
        vetService.save(vet2);

        // then
        assertThat(vetService.findById(1L).getId()).isEqualTo(vet1.getId());
        assertThat(vetService.findById(2L).getId()).isEqualTo(vet2.getId());

        assertThat(vetService.findById(1L)).isEqualTo(vet1);
        assertThat(vetService.findById(2L)).isEqualTo(vet2);

        assertThat(vetService.findAll().size()).isEqualTo(2);
    }
}