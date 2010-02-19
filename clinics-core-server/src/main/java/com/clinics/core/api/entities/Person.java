package com.clinics.core.api.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class Person extends OcEntity implements java.io.Serializable {

    public static enum Gender {
        M, F
    }

    private static final long serialVersionUID = 3816738738370356678L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Basic(optional = true)
    @Column(name = "FIRST_NAME", insertable = true, updatable = true, length = 255)
    private String firstName;

    @Basic(optional = true)
    @Column(name = "LAST_NAME", insertable = true, updatable = true, length = 255)
    private String lastName;

    @Basic(optional = true)
    @Column(name = "MIDDLE_NAME", insertable = true, updatable = true, length = 255)
    private String middleName;

    @Basic(optional = true)
    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE", insertable = true, updatable = true)
    private Date birthDate;

    @Basic(optional = true)
    @Column(name = "GENDER", insertable = true, updatable = true, length = 10)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Basic(optional = true)
    @Column(name = "HOME_PHONE", insertable = true, updatable = true, length = 30)
    private String homeTelephone;

    @Basic(optional = true)
    @Column(name = "MOBILE_PHONE", insertable = true, updatable = true, length = 30)
    private String mobileTelephone;

    @Basic(optional = true)
    @Column(name = "BUSINESS_PHONE", insertable = true, updatable = true, length = 30)
    private String businessTelephone;

    /**
     * 
     */
    public Person() {
        super();
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getBusinessTelephone() {
        return businessTelephone;
    }

    public String getFirstName() {
        return firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getHomeTelephone() {
        return homeTelephone;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getMobileTelephone() {
        return mobileTelephone;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setBusinessTelephone(String businessTelephone) {
        this.businessTelephone = businessTelephone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setHomeTelephone(String homeTelephone) {
        this.homeTelephone = homeTelephone;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setMobileTelephone(String mobileTelephone) {
        this.mobileTelephone = mobileTelephone;
    }

}