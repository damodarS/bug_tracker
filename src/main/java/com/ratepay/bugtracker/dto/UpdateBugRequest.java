package com.ratepay.bugtracker.dto;

import com.ratepay.bugtracker.constants.BugSeverity;
import com.ratepay.bugtracker.constants.BugStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBugRequest {
    private String title;
    private String description;
    private BugStatus status;
    private BugSeverity severity;
    private String assignee;
    private Date closedAt;
}
