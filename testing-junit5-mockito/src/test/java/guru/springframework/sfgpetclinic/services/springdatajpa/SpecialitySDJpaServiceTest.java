package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService specialitySDJpaService;

    @Test
    void findByIdTest(){
        Speciality speciality = new Speciality();

        // when repository.findbyId is called, return optional type
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

        // actually method call
        Speciality findSpecialty = specialitySDJpaService.findById(1L);

        // the result is not null, have data.
        assertThat(findSpecialty).isNotNull();

        // verify that findbyid method is called one time.
        // verify: to check methods were called with given arguments
        verify(specialtyRepository).findById(1L);
    }

    @Test
    void deleteById() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        verify(specialtyRepository, times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast(){
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost(){
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        verify(specialtyRepository, atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever(){
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        verify(specialtyRepository, atLeastOnce()).deleteById(1L);

        verify(specialtyRepository, never()).deleteById(5L);
    }

    @Test
    void testDelete(){
        specialitySDJpaService.delete(new Speciality());
    }
}