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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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
        given(visitRepository.findAll()).willReturn(visits);

        // when
//        when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> findVisits = visitSDJpaService.findAll();

        // then
        then(visitRepository).should().findAll();
        // verify(visitRepository).findAll();
        // assertThat(findVisits).isEqualTo(visits);
        // assertThat(findVisits).isNotNull();
    }

    @Test
    void findById() {
        // given
        Visit visit = new Visit();
        // when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));
        given(visitRepository.findById(anyLong())).willReturn(Optional.of(visit));

        // when
        Visit findVisit = visitSDJpaService.findById(1L);

        // then
        then(visitRepository).should().findById(anyLong());
        // verify(visitRepository).findById(anyLong());
        // assertThat(findVisit).isNotNull();
    }

    @Test
    void save() {
        // given
        Visit visit = new Visit();
        // when(visitRepository.save(any(Visit.class))).thenReturn(visit);
        given(visitRepository.save(any(Visit.class))).willReturn(visit);

        // when
        Visit saveVisit = visitSDJpaService.save(visit);

        // then
        then(visitRepository).should().save(any(Visit.class));
        // verify(visitRepository).save(any(Visit.class));
        // assertThat(saveVisit).isNotNull();
    }

    @Test
    void delete() {
        // given
        Visit visit = new Visit();
        // when
        visitSDJpaService.delete(visit);
        // then
        then(visitRepository).should().delete(any(Visit.class));
        // verify(visitRepository).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        // given
        Visit visit = new Visit();
        // when
        visitSDJpaService.deleteById(1L);
        // then
        then(visitRepository).should().deleteById(anyLong());
        // verify(visitRepository).deleteById(anyLong());
    }
}