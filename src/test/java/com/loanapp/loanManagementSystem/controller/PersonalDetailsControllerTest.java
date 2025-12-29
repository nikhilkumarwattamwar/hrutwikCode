package com.loanapp.loanManagementSystem.controller;

import com.loanapp.loanManagementSystem.contollers.user.PersonalDetailController;
import com.loanapp.loanManagementSystem.dto.user.PersonalDto;
import com.loanapp.loanManagementSystem.service.user.PersonalDetailsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PersonalDetailsControllerTest {
@Mock
 private    PersonalDetailsService service;

@InjectMocks
  private   PersonalDetailController controller;


@Test
@DisplayName("saveAllDetails:Save personal details for valid user ID")
    void testSaveAllDetails(){
    UUID id= UUID.randomUUID();

    PersonalDto dto= new PersonalDto();

    when(service.saveDetailsById(dto,id)).thenReturn(dto);

    PersonalDto result=controller.saveAllDetails(dto,id);

    assertNotNull(result);
    assertEquals(result,dto);
    verify(service,times(1)).saveDetailsById(dto,id);


}

@Test
@DisplayName("getDetails: Fetch personal details using user id")
void testGetDetails(){
    UUID id= UUID.randomUUID();
    PersonalDto dto= new PersonalDto();

    when(service.getDetailsByID(id)).thenReturn(dto);

    PersonalDto result=controller.getDetails(id);

    assertEquals(result,dto);
    verify(service,times(1)).getDetailsByID(id);

}

    @Test
    @DisplayName("updateDetails: Update personal details for existing user id")
    void testUpdateDetails(){
        UUID id= UUID.randomUUID();
        PersonalDto dto= new PersonalDto();

        when(service.updateDetails(dto,id)).thenReturn(dto);

        PersonalDto result=controller.updateDetails(dto,id);
        assertEquals(result,dto);
        verify(service,times(1)).updateDetails(dto,id);
    }


}
