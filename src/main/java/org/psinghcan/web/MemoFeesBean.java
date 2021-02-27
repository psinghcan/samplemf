package org.psinghcan.web;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;

import org.psinghcan.domain.MemoFeesEntity;
import org.psinghcan.service.MemoFeesService;
import org.psinghcan.service.security.SecurityWrapper;
import org.psinghcan.web.util.MessageFactory;

@Named("memoFeesBean")
@ViewScoped
public class MemoFeesBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(MemoFeesBean.class.getName());
    
    private List<MemoFeesEntity> memoFeesList;

    private MemoFeesEntity memoFees;
    
    @Inject
    private MemoFeesService memoFeesService;
    
    public void prepareNewMemoFees() {
        reset();
        this.memoFees = new MemoFeesEntity();
        // set any default values now, if you need
        // Example: this.memoFees.setAnything("test");
    }

    public String persist() {

        if (memoFees.getId() == null && !isPermitted("memoFees:create")) {
            return "accessDenied";
        } else if (memoFees.getId() != null && !isPermitted(memoFees, "memoFees:update")) {
            return "accessDenied";
        }
        
        String message;
        
        try {
            
            if (memoFees.getId() != null) {
                memoFees = memoFeesService.update(memoFees);
                message = "message_successfully_updated";
            } else {
                memoFees = memoFeesService.save(memoFees);
                message = "message_successfully_created";
            }
        } catch (OptimisticLockException e) {
            logger.log(Level.SEVERE, "Error occured", e);
            message = "message_optimistic_locking_exception";
            // Set validationFailed to keep the dialog open
            FacesContext.getCurrentInstance().validationFailed();
        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "Error occured", e);
            message = "message_save_exception";
            // Set validationFailed to keep the dialog open
            FacesContext.getCurrentInstance().validationFailed();
        }
        
        memoFeesList = null;

        FacesMessage facesMessage = MessageFactory.getMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        
        return null;
    }
    
    public String delete() {
        
        if (!isPermitted(memoFees, "memoFees:delete")) {
            return "accessDenied";
        }
        
        String message;
        
        try {
            memoFeesService.delete(memoFees);
            message = "message_successfully_deleted";
            reset();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occured", e);
            message = "message_delete_exception";
            // Set validationFailed to keep the dialog open
            FacesContext.getCurrentInstance().validationFailed();
        }
        FacesContext.getCurrentInstance().addMessage(null, MessageFactory.getMessage(message));
        
        return null;
    }
    
    public void onDialogOpen(MemoFeesEntity memoFees) {
        reset();
        this.memoFees = memoFees;
    }
    
    public void reset() {
        memoFees = null;
        memoFeesList = null;
        
    }

    public MemoFeesEntity getMemoFees() {
        // Need to check for null, because some strange behaviour of f:viewParam
                // Sometimes it is setting to null
        if (this.memoFees == null) {
            prepareNewMemoFees();
        }
        return this.memoFees;
    }
    
    public void setMemoFees(MemoFeesEntity memoFees) {
            if (memoFees != null) {
        this.memoFees = memoFees;
            }
    }
    
    public List<MemoFeesEntity> getMemoFeesList() {
        if (memoFeesList == null) {
            memoFeesList = memoFeesService.findAllMemoFeesEntities();
        }
        return memoFeesList;
    }

    public void setMemoFeesList(List<MemoFeesEntity> memoFeesList) {
        this.memoFeesList = memoFeesList;
    }
    
    public boolean isPermitted(String permission) {
        return SecurityWrapper.isPermitted(permission);
    }

    public boolean isPermitted(MemoFeesEntity memoFees, String permission) {
        
        return SecurityWrapper.isPermitted(permission);
        
    }
    
}
