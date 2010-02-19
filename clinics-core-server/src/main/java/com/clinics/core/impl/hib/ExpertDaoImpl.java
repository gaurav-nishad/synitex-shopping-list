package com.clinics.core.impl.hib;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.clinics.core.api.dao.ExpertDao;
import com.clinics.core.api.entities.Expert;

@Repository
@Transactional
public class ExpertDaoImpl extends GeneralDaoImpl<Expert> implements ExpertDao {
}
