package com.yaya.submitt.pojo.dto;

public class ModelDto {
    private Integer modId;
    private String modName;
    private String modVersion;
    private String modType;
    private String modPurpose;
    private String modFramework;
    private java.sql.Date modCreationDate;
    private Boolean modActive;

    // Getters and Setters

    public Integer getModId() {
        return modId;
    }

    public void setModId(Integer modId) {
        this.modId = modId;
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public String getModVersion() {
        return modVersion;
    }

    public void setModVersion(String modVersion) {
        this.modVersion = modVersion;
    }

    public String getModType() {
        return modType;
    }

    public void setModType(String modType) {
        this.modType = modType;
    }

    public String getModPurpose() {
        return modPurpose;
    }

    public void setModPurpose(String modPurpose) {
        this.modPurpose = modPurpose;
    }

    public String getModFramework() {
        return modFramework;
    }

    public void setModFramework(String modFramework) {
        this.modFramework = modFramework;
    }

    public java.sql.Date getModCreationDate() {
        return modCreationDate;
    }

    public void setModCreationDate(java.sql.Date modCreationDate) {
        this.modCreationDate = modCreationDate;
    }

    public Boolean getModActive() {
        return modActive;
    }

    public void setModActive(Boolean modActive) {
        this.modActive = modActive;
    }

    @Override
    public String toString() {
        return "ModelDto{" +
                "modId=" + modId +
                ", modName='" + modName + '\'' +
                ", modVersion='" + modVersion + '\'' +
                ", modType='" + modType + '\'' +
                ", modPurpose='" + modPurpose + '\'' +
                ", modFramework='" + modFramework + '\'' +
                ", modCreationDate=" + modCreationDate +
                ", modActive=" + modActive +
                '}';
    }
}