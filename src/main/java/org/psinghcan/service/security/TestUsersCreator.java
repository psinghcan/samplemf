package org.psinghcan.service.security;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.psinghcan.domain.security.UserEntity;
import org.psinghcan.domain.security.UserRole;
import org.psinghcan.domain.security.UserStatus;

/**
 * Creates some test users in fresh database.
 * 
 * TODO This class is temporary for test, only. Just delete this class
 * if you do not need the test users to be created automatically.
 *
 */
@Singleton
@Startup
public class TestUsersCreator {

    private static final Logger logger = Logger.getLogger(TestUsersCreator.class.getName());
    
    @Inject
    private UserService userService;
    
    @PostConstruct
    public void postConstruct() {
        
       if(userService.countAllEntries() == 0) {
           
            logger.log(Level.WARNING, "Creating test user 'admin' with password 'admin'.");
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setRoles(Arrays.asList(new UserRole[]{UserRole.Administrator}));
            admin.setStatus(UserStatus.Active);
            admin.setEmail("admin@domain.test");
            
            userService.save(admin);
            
            logger.log(Level.WARNING, "Creating test user 'reporter' with password 'reporter'.");
            UserEntity reporterUser = new UserEntity();
            reporterUser.setUsername("reporter");
            reporterUser.setPassword("reporter");
            reporterUser.setRoles(Arrays.asList(new UserRole[]{UserRole.Reporter}));
            reporterUser.setStatus(UserStatus.Active);
            reporterUser.setEmail("reporter@domain.test");
            
            userService.save(reporterUser);
            
        }
    }
}
