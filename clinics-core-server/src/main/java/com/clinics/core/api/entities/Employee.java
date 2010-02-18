package com.clinics.core.api.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SequenceGenerator(name = "seq", sequenceName = "EM_EMPLOYEE_SEQ", allocationSize = 1)
@Entity(name = "Employee")
@Table(name = "EM_EMPLOYEE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Employee extends Person implements java.io.Serializable {

    private static final long serialVersionUID = 4960041525303686562L;

    @Basic(optional = true)
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_HIRE", insertable = true, updatable = true)
    private Date dateOfHire;

    @Basic(optional = true)
    @Column(name = "DIVISION", insertable = true, updatable = true, length = 254)
    private String division;

    @Basic(optional = true)
    @Column(name = "AREA", insertable = true, updatable = true, length = 254)
    private String area;

    @Basic(optional = true)
    @Column(name = "POSITION", insertable = true, updatable = true, length = 255)
    private String position;

    @Basic(optional = true)
    @Column(name = "BUSINESS_TITLE", insertable = true, updatable = true, length = 255)
    private String businessTitle;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "EMPLOYEES_DEPARTMENT_ID", referencedColumnName = "ID", nullable = true)
    // public Department employeesDepartment;
    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "PRIMARYCONTACT_DEPARTMENT_ID", referencedColumnName = "ID", nullable = true)
    // public Department primaryContactDepartment;
    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "MANAGER_DEPARTMENT_ID", referencedColumnName = "ID", nullable = true)
    // public Department managerDepartment;
}