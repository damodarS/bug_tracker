package com.ratepay.bugtracker.controller;

import com.ratepay.bugtracker.dto.BugDTO;
import com.ratepay.bugtracker.dto.BugSearchDTO;
import com.ratepay.bugtracker.dto.CreateBugRequest;
import com.ratepay.bugtracker.dto.GlobalResponseDTO;
import com.ratepay.bugtracker.dto.UpdateBugRequest;
import com.ratepay.bugtracker.mapper.BugMapper;
import com.ratepay.bugtracker.service.BugService;
import com.ratepay.bugtracker.utils.BugSpecificationBuilder;
import com.ratepay.bugtracker.utils.SearchCriteria;
import com.ratepay.bugtracker.utils.StringConverter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bug")
public class BugController {

    @Autowired
    private BugService bugService;

    @Autowired
    private BugMapper bugMapper;

    @Operation(summary = "Get bugs by filter criteria")
    @PostMapping("/search")
    public ResponseEntity<GlobalResponseDTO<List<BugDTO>>> fetchBugs(@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
                                                                     @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                                     @RequestBody BugSearchDTO bugSearchDTO) {
        BugSpecificationBuilder builder = new BugSpecificationBuilder();
        List<SearchCriteria> criteriaList = bugSearchDTO.getSearchCriteriaList();

        if (criteriaList != null) {
            criteriaList.forEach(x -> {
                x.setDataOption(bugSearchDTO.getDataOption());
                builder.with(x);
            });
        }

        Pageable page = PageRequest.of(pageNum, pageSize, Sort.by("createdAt").ascending());

        return ResponseEntity.ok(bugService.findBySearchCriteria(builder.build(), page));
    }

    @Operation(summary = "Get bug by ID")
    @GetMapping("/{bugId}")
    public ResponseEntity<GlobalResponseDTO<BugDTO>> getBugId(@PathVariable String bugId) {
        return ResponseEntity.ok(bugService.getBugById(StringConverter.trimAndConvertToUpperCase(bugId)));
    }

    @Operation(summary = "create bug")
    @PostMapping
    public ResponseEntity<GlobalResponseDTO<BugDTO>> createBug(@Valid @RequestBody CreateBugRequest request) {
        return ResponseEntity.ok(bugService.createBug(bugMapper.toBug(request)));
    }

    @Operation(summary = "update bug")
    @PutMapping("/{bugId}")
    public ResponseEntity<GlobalResponseDTO<BugDTO>> updateBug(@PathVariable String bugId,
                                                               UpdateBugRequest request) {
        return ResponseEntity.ok(bugService.updateBug(bugId, request));
    }

    @Operation(summary = "delete bug")
    @DeleteMapping("/{bugId}")
    public ResponseEntity<GlobalResponseDTO<BugDTO>> deleteBugById(@PathVariable String bugId) {
        return ResponseEntity.ok(bugService.deleteBugById(bugId));
    }
}
