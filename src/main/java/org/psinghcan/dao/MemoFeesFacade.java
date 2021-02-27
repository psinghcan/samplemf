package org.psinghcan.dao;

import org.psinghcan.domain.MemoFeesEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MemoFeesFacade extends AbstractFacade<MemoFeesEntity> {

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MemoFeesFacade() {
        super(MemoFeesEntity.class);
    }

}

