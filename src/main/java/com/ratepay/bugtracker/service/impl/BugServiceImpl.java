package com.ratepay.bugtracker.service.impl;

import com.ratepay.bugtracker.constants.Constants;
import com.ratepay.bugtracker.dto.BugDTO;
import com.ratepay.bugtracker.dto.GlobalResponseDTO;
import com.ratepay.bugtracker.dto.UpdateBugRequest;
import com.ratepay.bugtracker.mapper.BugMapper;
import com.ratepay.bugtracker.model.Bug;
import com.ratepay.bugtracker.repository.BugRepository;
import com.ratepay.bugtracker.service.BugService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BugServiceImpl implements BugService {

    @Autowired
    private BugRepository bugRepository;

    @Autowired
    private BugMapper bugMapper;

    @Override
    @Transactional(readOnly = true)
    public GlobalResponseDTO<BugDTO> getBugById(String bugId) {
        Optional<Bug> mayBeBug = bugRepository.findById(bugId);
        if (!mayBeBug.isPresent()) {
            return new GlobalResponseDTO<>(null, Constants.INVALID_BUG);
        }

        return new GlobalResponseDTO<>(bugMapper.toBugDto(mayBeBug.get()));
    }

    @Override
    public GlobalResponseDTO<BugDTO> createBug(BugDTO bugDTO) {
        Bug bug = bugRepository.save(bugMapper.toBug(bugDTO));
        return new GlobalResponseDTO<>(bugMapper.toBugDto(bug));
    }

    @Override
    public GlobalResponseDTO<BugDTO> updateBug(String bugId,
                                               UpdateBugRequest request) {
        Optional<Bug> mayBeBug = bugRepository.findById(bugId);
        if (!mayBeBug.isPresent()) {
            return new GlobalResponseDTO<>(null, Constants.INVALID_BUG);
        }

        Bug bug = mayBeBug.get();
        if(StringUtils.isNotEmpty(request.getTitle())) {
            bug.setTitle(request.getTitle());
        }
        if(StringUtils.isNotEmpty(request.getDescription())) {
            bug.setDescription(request.getDescription());
        }
        if(request.getStatus() != null) {
            bug.setStatus(request.getStatus());
        }
        if(request.getSeverity() != null) {
            bug.setSeverity(request.getSeverity());
        }
        if(StringUtils.isNotEmpty(request.getAssignee())) {
            bug.setAssignee(request.getAssignee());
        }
        if(request.getClosedAt() != null) {
            bug.setClosedAt(request.getClosedAt());
        }

        bug = bugRepository.save(bug);
        return new GlobalResponseDTO<>(bugMapper.toBugDto(bug));
    }

    @Override
    @Transactional(readOnly = true)
    public GlobalResponseDTO<List<BugDTO>> findBySearchCriteria(Specification<Bug> spec, Pageable page){
        Page<Bug> searchResult = bugRepository.findAll(spec, page);
        List<BugDTO> bugs = bugMapper.toBugs(searchResult.toList());
        return new GlobalResponseDTO<>(bugs);
    }

    @Override
    public GlobalResponseDTO<BugDTO> deleteBugById(String bugId) {
        Optional<Bug> mayBeBug = bugRepository.findById(bugId);
        if (!mayBeBug.isPresent()) {
            return new GlobalResponseDTO<>(null, Constants.INVALID_BUG);
        }

        Bug bug = mayBeBug.get();
        bug.setDeleted(true);
        bug = bugRepository.save(bug);

        return new GlobalResponseDTO<>(bugMapper.toBugDto(bug));
    }
}
