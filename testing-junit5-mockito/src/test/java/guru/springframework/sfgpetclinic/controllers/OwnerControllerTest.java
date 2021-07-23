package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS = "redirect:/owners/";

    @Mock(lenient = true)
    OwnerService ownerService;

    @Mock
    Model model;

    @InjectMocks
    OwnerController ownerController;

    @Mock
    BindingResult bindingResult;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp(){
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willAnswer(invocation->{
            // find last name of wildcard
            String lastName = invocation.getArgument(0);
            // owner list
            List<Owner> owners = new ArrayList<>();

            if (lastName.equals("%A%")){
                owners.add(new Owner(1L, "testA", "A"));
                return owners;
            } else if (lastName.equals("%NoOwner%")){
                return owners;
            } else if (lastName.equals("%MultipleOwners%")){
                owners.add(new Owner(1L, "testA", "A"));
                owners.add(new Owner(2L, "testB", "B"));
                return owners;
            }
            throw new RuntimeException("invalid Argument");
        });
    }

    @Test
    void processFindFormWildcardString(){
        // given
        Owner owner = new Owner(1L, "test", "A");
        List<Owner> owners = new ArrayList<>();
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(owners);

        // when
        String viewName = ownerController.processFindForm(owner, bindingResult, null);

        // then
        assertThat("%A%").isEqualToIgnoringCase(captor.getValue());
    }

    @Test
    void processFindFormWildcardStringAnnotation(){
        // given
        Owner owner = new Owner(1L, "test", "A");
        List<Owner> owners = new ArrayList<>();
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(owners);

        // when
        String viewName = ownerController.processFindForm(owner, bindingResult, null);

        // then
        assertThat("%A%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
    }

    @Test
    void processFindFormWildcardNoOwner(){
        // given
        Owner owner = new Owner(1L, "testA", "NoOwner");
        // when
        String viewName = ownerController.processFindForm(owner, bindingResult, null);
        // then
        assertThat("%NoOwner%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
    }

    @Test
    void processFindFormWildcardOneOwner(){
        // given
        Owner owner = new Owner(1L, "testA", "A");
        // when
        String viewName = ownerController.processFindForm(owner, bindingResult, null);
        // then
        assertThat("%A%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);
    }

    @Test
    void processFindFormWildcardMultiOwners(){
        // given
        Owner owner = new Owner(1L, "testA", "MultipleOwners");
        InOrder inOrder = inOrder(ownerService, model);
        // when
        String viewName = ownerController.processFindForm(owner, bindingResult, model);
        // then
        assertThat("%MultipleOwners%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);
        // inorder asserts
        inOrder.verify(ownerService).findAllByLastNameLike(anyString());
        inOrder.verify(model).addAttribute(anyString(), anyList());

    }

    @Test
    void processCreationFormHasErrors(){
        // given
        Owner owner = new Owner(1L, "Test","Mock");
        given(bindingResult.hasErrors()).willReturn(true);

        // when
        String viewName = ownerController.processCreationForm(owner, bindingResult);

        // then
        assertThat(viewName).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);
    }

    @Test
    void processCreationFormNoErrors(){
        // given
        Owner owner = new Owner(1L, "Test","Mock");
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any())).willReturn(owner);

        // when
        String viewName = ownerController.processCreationForm(owner, bindingResult);

        // then
        assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNERS+"1");

    }
}