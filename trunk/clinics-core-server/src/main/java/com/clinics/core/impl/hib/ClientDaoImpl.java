package com.clinics.core.impl.hib;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.clinics.core.api.dao.ClientDao;
import com.clinics.core.api.entities.Client;

@Repository
@Transactional
public class ClientDaoImpl extends GeneralDaoImpl<Client> implements ClientDao {
}
