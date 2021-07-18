package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService specialitySDJpaService;

    @Test
    void testDeleteByObject(){
        Speciality speciality = new Speciality();
        specialitySDJpaService.delete(speciality);

        // can use flexible argument matching, for example any expression via the any()
        verify(specialtyRepository).delete(any(Speciality.class));
    }

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
        // can use flexible argument matching, for example any expression via the any()
        verify(specialtyRepository).findById(anyLong());
    }

    @Test
    void findByIdBddTest(){
        // given
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

        // when
        Speciality findSpeciality = specialitySDJpaService.findById(1L);

        // then
        assertThat(findSpeciality).isNotNull();
        verify(specialtyRepository).findById(anyLong());

        then(specialtyRepository).should().findById(anyLong());
        then(specialtyRepository).should(times(1)).findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
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

    /**
     * basic mockito test code
     */
    @Test
    void testDoThrow(){
        doThrow(new RuntimeException("boom")).when(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class, ()->specialitySDJpaService.delete(new Speciality()));

        verify(specialtyRepository).delete(any());
    }

    /**
     * given and then type
     */
    @Test
    void testFindByIdThrow(){
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("boom"));

        assertThrows(RuntimeException.class, ()->specialitySDJpaService.findById(1L));

        then(specialtyRepository).should().findById(anyLong());
    }

    /**
     * BDD type
     */
    @Test
    void testDeleteBdd(){
        willThrow(new RuntimeException("boom")).given(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class, ()->specialitySDJpaService.delete(new Speciality()));

        then(specialtyRepository).should().delete(any(Speciality.class));
    }

}