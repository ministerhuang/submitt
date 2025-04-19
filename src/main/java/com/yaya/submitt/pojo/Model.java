package com.yaya.submitt.pojo;

import jakarta.persistence.*;

@Table(name = "MODEL")
@Entity
public class Model {
    @Id
    @Column(name = "mod_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer modId;

    @Column(name = "mod_name", nullable = false)
    private String modName;

    @Column(name = "mod_version")
    private String modVersion;

    @Column(name = "mod_type")
    private String modType;

    @Column(name = "mod_purpose", columnDefinition = "TEXT")
    private String modPurpose;

    @Column(name = "mod_framework")
    private String modFramework;

    @Column(name = "mod_creation_date")
    private java.sql.Date modCreationDate;

    @Column(name = "mod_active", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean modActive = true;

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
        return "Model{" +
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