package com.ratepay.bugtracker.model;

import com.ratepay.bugtracker.constants.BugSeverity;
import com.ratepay.bugtracker.constants.BugStatus;
import com.ratepay.bugtracker.utils.StringPrefixedSequenceIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BUGS")
public class Bug extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bug_seq")
    @GenericGenerator(
            name = "bug_seq",
            strategy = "com.ratepay.bugtracker.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "BUG_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d") })
    @Column(name = "bug_id", nullable = false)
    private String bugId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BugStatus status = BugStatus.NEW;

    @Enumerated(EnumType.STRING)
    @Column(name = "severity", nullable = false)
    private BugSeverity severity = BugSeverity.LOW;

    @Column(name = "assignee")
    private String assignee;

    @Column(name = "reporter", nullable = false)
    private String reporter;

    @Column(name = "closed_at")
    private Date closedAt;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;
}
