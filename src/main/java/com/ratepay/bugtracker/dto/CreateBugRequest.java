package com.ratepay.bugtracker.dto;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
public class CreateBugRequest {
    String title;
    String description;
    String reporter;
    String severity;
}
