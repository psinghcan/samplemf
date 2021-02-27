package org.psinghcan.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.psinghcan.domain.MemoFeesEntity;

@Named
public class MemoFeesService implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    protected EntityManager entityManager;
    

    @Transactional
    public List<MemoFeesEntity> findAllMemoFeesEntities() {
        return entityManager.createQuery("SELECT o FROM memofees o ", MemoFeesEntity.class).getResultList();
    }

    @Transactional
    public List<MemoFeesEntity> findAllMemoFeesEntitiesYearMonth(int year, int month) {
        String query = "SELECT o FROM memofees o where o.invoiceYear = :year and o.invoiceMonth = :month";
        return entityManager.createQuery(query)
                .setParameter("year", year)
                .setParameter("month", month)
                .getResultList();
    }


    
    @Transactional
    public long countAllEntries() {
        return entityManager.createQuery("SELECT COUNT(o) FROM memofees o", Long.class).getSingleResult();
    }

    @Transactional
    public MemoFeesEntity save(MemoFeesEntity entity) {
        this.entityManager.persist(entity);
        this.entityManager.flush();
        this.entityManager.refresh(entity);
        return entity;
    }

    @Transactional
    public MemoFeesEntity update(MemoFeesEntity entity) {
        return this.entityManager.merge(entity);
    }

    @Transactional
    public MemoFeesEntity find(Long id) {
        if (id == null) return null;
        return this.entityManager.find(MemoFeesEntity.class, id);
    }

    @Transactional
    public MemoFeesEntity find(MemoFeesEntity id) {
        if (id == null) return null;
        return this.entityManager.find(MemoFeesEntity.class, id);
    }

    @Transactional
    public void delete(MemoFeesEntity entity) {

        handleDependenciesBeforeDelete(entity);

        if (this.entityManager.contains(entity)) {
            this.entityManager.remove(entity);
        } else {
            MemoFeesEntity attached = find(entity.getId());
            this.entityManager.remove(attached);
        }

        this.entityManager.flush();
    }
    
    protected void handleDependenciesBeforeDelete(MemoFeesEntity memoFees) {

        /* This is called before a MemoFees is deleted. Place here all the
           steps to cut dependencies to other entities */
        
    }

}
