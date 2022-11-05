package com.ratepay.bugtracker.mapper;

import com.ratepay.bugtracker.dto.BugDTO;
import com.ratepay.bugtracker.dto.CreateBugRequest;
import com.ratepay.bugtracker.dto.UpdateBugRequest;
import com.ratepay.bugtracker.model.Bug;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface BugMapper {
    BugDTO toBugDto(Bug bug);

    List<BugDTO> toBugs(List<Bug> bugs);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    Bug toBug(BugDTO bugDto);

    BugDTO toBug(CreateBugRequest createBugRequestDTO);

    BugDTO toBug(UpdateBugRequest updateBugRequestDTO);
}
