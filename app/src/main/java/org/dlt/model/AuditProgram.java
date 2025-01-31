package org.dlt.model;

import java.time.LocalDate;

class AuditProgram {
    private int id;
    private String programCode;
    private String programNumber;
    private String programType;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate issueDate;
    private LocalDate createDate;
    private Auditor[] auditors;

    public int getId() {
        return id;
    }

    public AuditProgram setId(int id) {
        this.id = id;
        return this;
    }

    public String getProgramCode() {
        return programCode;
    }

    public AuditProgram setProgramCode(String programCode) {
        this.programCode = programCode;
        return this;
    }

    public String getProgramNumber() {
        return programNumber;
    }

    public AuditProgram setProgramNumber(String programNumber) {
        this.programNumber = programNumber;
        return this;
    }

    public String getProgramType() {
        return programType;
    }

    public AuditProgram setProgramType(String programType) {
        this.programType = programType;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public AuditProgram setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public AuditProgram setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public AuditProgram setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public Auditor[] getAuditors() {
        return auditors;
    }

    public AuditProgram addAuditor(Auditor auditor) {
        this.auditors[this.auditors.length - 1] = auditor;
        return this;
    }

    public AuditProgram setAuditors(Auditor[] auditors) {
        this.auditors = auditors;
        return this;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public AuditProgram setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }
}