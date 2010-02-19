package com.clinics.core.impl.hib;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.clinics.core.api.dao.PersonDao;
import com.clinics.core.api.entities.Person;

@Repository
@Transactional
public class PersonDaoImpl extends GeneralDaoImpl<Person> implements PersonDao {
}
