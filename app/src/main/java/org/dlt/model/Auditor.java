package org.dlt.model;

class Auditor {
    private int id;
    private String staffId;
    private String firstName;
    private String lastName;
    private Boolean gender;
    private String position;
    private String bureauName;

    public int getId() {
        return id;
    }

    public Auditor setId(int id) {
        this.id = id;
        return this;
    }

    public String getStaffId() {
        return staffId;
    }

    public Auditor setStaffId(String staffId) {
        this.staffId = staffId;
        return this;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Auditor setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Auditor setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getGender() {
        if (gender == 0) {
            return "female";
        } else if (gender == 1) {
            return "male";
        }
    }

    public Boolean getGenderValue() {
        return gender;
    }

    public Auditor setGender(Boolean gender) {
        this.gender = gender;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public Auditor setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getBureauName() {
        return bureauName;
    }

    public Auditor setBureauName(String bureauName) {
        this.bureauName = bureauName;
        return this;
    }
}