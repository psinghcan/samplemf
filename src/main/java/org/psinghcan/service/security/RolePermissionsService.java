package org.psinghcan.service.security;

import java.io.Serializable;

import javax.inject.Named;
import javax.transaction.Transactional;

import org.psinghcan.domain.security.RolePermission;
import org.psinghcan.service.BaseService;

@Named
public class RolePermissionsService extends BaseService<RolePermission> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public RolePermissionsService(){
        super(RolePermission.class);
    }
    
    @Override
    @Transactional
    public long countAllEntries() {
        return entityManager.createQuery("SELECT COUNT(o) FROM RolePermission o", Long.class).getSingleResult();
    }

}
