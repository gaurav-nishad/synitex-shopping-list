package com.clinics.core.api.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SequenceGenerator(name = "seq", sequenceName = "EM_EMPLOYEE_SEQ", allocationSize = 1)
@Entity(name = "Employee")
@Table(name = "EM_EMPLOYEE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Employee extends Person implements java.io.Serializable {

    private static final long serialVersionUID = 4960041525303686562L;

    // @Basic(optional = true)
    // @Temporal(TemporalType.DATE)
    // @Column(name = "DATE_OF_HIRE", insertable = true, updatable = true)
    // private Date dateOfHire;

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