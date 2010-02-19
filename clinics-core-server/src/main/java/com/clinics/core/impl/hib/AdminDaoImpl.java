package com.clinics.core.impl.hib;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.clinics.core.api.dao.AdminDao;
import com.clinics.core.api.entities.Admin;

@Repository
@Transactional
public class AdminDaoImpl extends GeneralDaoImpl<Admin> implements AdminDao {
}
