package com.ratepay.bugtracker.dto;

import com.ratepay.bugtracker.utils.SearchCriteria;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
public class BugSearchDTO {
    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;
}
