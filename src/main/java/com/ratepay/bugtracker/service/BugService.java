package com.ratepay.bugtracker.service;

import com.ratepay.bugtracker.dto.BugDTO;
import com.ratepay.bugtracker.dto.GlobalResponseDTO;
import com.ratepay.bugtracker.dto.UpdateBugRequest;
import com.ratepay.bugtracker.model.Bug;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BugService {
    GlobalResponseDTO<BugDTO> getBugById(String bugId);

    GlobalResponseDTO<BugDTO> createBug(BugDTO bugDTO);

    GlobalResponseDTO<BugDTO> updateBug(String bugId, UpdateBugRequest request);

    GlobalResponseDTO<List<BugDTO>> findBySearchCriteria(Specification<Bug> spec, Pageable page);

    GlobalResponseDTO<BugDTO> deleteBugById(String bugId);
}
