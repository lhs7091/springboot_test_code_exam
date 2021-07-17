package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService visitSDJpaService;

    @Test
    void findAll() {
        // given
        Visit visit = new Visit();

        Set<Visit> visits = new HashSet<>();
        visits.add(visit);

        // when
        when(visitRepository.findAll()).thenReturn(visits);

        Set<Visit> findVisits = visitSDJpaService.findAll();

        // then
        verify(visitRepository).findAll();
        assertThat(findVisits).isEqualTo(visits);
        assertThat(findVisits).isNotNull();
    }

    @Test
    void findById() {
        // given
        Visit visit = new Visit();

        // when
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));

        Visit findVisit = visitSDJpaService.findById(1L);

        // then
        verify(visitRepository).findById(anyLong());
        assertThat(findVisit).isNotNull();
    }

    @Test
    void save() {
        // given
        Visit visit = new Visit();

        // when
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);

        Visit saveVisit = visitSDJpaService.save(visit);

        // then
        verify(visitRepository).save(any(Visit.class));
        assertThat(saveVisit).isNotNull();
    }

    @Test
    void delete() {
        // given
        Visit visit = new Visit();
        // when
        visitSDJpaService.delete(visit);
        // then
        verify(visitRepository).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        // given
        Visit visit = new Visit();
        // when
        visitSDJpaService.deleteById(1L);
        // then
        verify(visitRepository).deleteById(anyLong());
    }
}