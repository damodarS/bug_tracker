package com.ratepay.bugtracker.test;

import com.ratepay.bugtracker.constants.BugSeverity;
import com.ratepay.bugtracker.constants.BugStatus;
import com.ratepay.bugtracker.constants.Constants;
import com.ratepay.bugtracker.dto.BugDTO;
import com.ratepay.bugtracker.dto.GlobalResponseDTO;
import com.ratepay.bugtracker.dto.UpdateBugRequest;
import com.ratepay.bugtracker.model.Bug;
import com.ratepay.bugtracker.repository.BugRepository;
import com.ratepay.bugtracker.service.BugService;
import com.ratepay.bugtracker.service.impl.BugServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BugServiceTest {
    private BugRepository bugRepository;
    private BugService bugService;

    @BeforeEach
    void setup() {
        this.bugRepository = mock(BugRepository.class);
        bugService = new BugServiceImpl(bugRepository);
    }

    // Get By Id test cases
    @Test
    void shouldNotAllowEmptyBugId() {
        String bugId = "";

        GlobalResponseDTO<BugDTO> responseDTO = bugService.getBugById(bugId);
        assertEquals(Constants.INVALID_BUG_ID, responseDTO.getMessage());
    }

    @Test
    void shouldReturnErrorWhen__InvalidBugId() {
        String bugId = "invalid";

        when(bugRepository.findById(bugId)).thenReturn(Optional.empty());

        GlobalResponseDTO<BugDTO> responseDTO = bugService.getBugById(bugId);
        assertEquals(Constants.INVALID_BUG, responseDTO.getMessage());
    }

    @Test
    void shouldReturnResultWhen__ValidBug() {
        String bugId = "valid";

        when(bugRepository.findById(bugId)).thenReturn(Optional.of(validBug("bug1")));

        GlobalResponseDTO<BugDTO> response = bugService.getBugById(bugId);

        BugDTO bugDTO = response.getData();
        assertEquals(bugDTO.getBugId(), "bug1");
        assertEquals(bugDTO.getStatus(), BugStatus.NEW);
        assertEquals(bugDTO.getSeverity(), BugSeverity.LOW);
    }

    // createBug test cases
    @Test
    void shouldCreateBug() {
        String bugId = "bugId";
        BugDTO bug = BugDTO.builder()
                .bugId(bugId)
                .status(BugStatus.NEW)
                .severity(BugSeverity.LOW)
                .reporter("user")
                .build();

        bugService.createBug(bug);

        verifyBugState(bugId, BugStatus.NEW, BugSeverity.LOW);
    }

    private void verifyBugState(String bugId,
                                BugStatus bugStatus,
                                BugSeverity bugSeverity) {
        ArgumentCaptor<Bug> argumentCaptor = ArgumentCaptor.forClass(Bug.class);
        verify(bugRepository, times(1)).save(argumentCaptor.capture());
        Bug bug = argumentCaptor.getValue();

        assertEquals(bug.getBugId(), bugId);
        assertEquals(bug.getStatus(), bugStatus);
        assertEquals(bug.getSeverity(), bugSeverity);
    }

    // Update bug test case
    @Test
    void shouldUpdateBug() {
        String bugId = "bugId";

        when(bugRepository.findById(bugId)).thenReturn(Optional.of(validBug(bugId)));

        UpdateBugRequest updateBugRequest = UpdateBugRequest.builder()
                .status(BugStatus.ASSIGNED)
                .severity(BugSeverity.MAJOR)
                .assignee("developer")
                .build();

        bugService.updateBug(bugId, updateBugRequest);

        verifyBugState(bugId, BugStatus.ASSIGNED, BugSeverity.MAJOR);
    }

    private Bug validBug(String bugId) {
        return Bug.builder()
                .bugId(bugId)
                .status(BugStatus.NEW)
                .severity(BugSeverity.LOW)
                .reporter("user")
                .build();
    }
}
