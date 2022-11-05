package com.ratepay.bugtracker.dto;

import com.ratepay.bugtracker.constants.BugSeverity;
import com.ratepay.bugtracker.constants.BugStatus;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateBugRequest {
    private String title;
    private String description;
    private BugStatus status;
    private BugSeverity severity;
    private String assignee;
    private Date closedAt;
}
