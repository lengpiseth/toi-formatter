package org.dlt.model;

class AuditProgram {
    private int id;
    private String programCode;
    private String programNumber;
    private String programType;
    private Date startDate;
    private Date endDate;
    private Date issueDate;
    private Date createDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public AuditProgram setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public AuditProgram setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public AuditProgram setIssueDate(Date issueDate) {
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

    public Date getCreateDate() {
        return createDate;
    }

    public AuditProgram setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }
}