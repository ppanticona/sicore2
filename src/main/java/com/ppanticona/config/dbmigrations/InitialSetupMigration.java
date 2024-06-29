package com.ppanticona.config.dbmigrations;

import com.ppanticona.config.Constants;
import com.ppanticona.domain.Authority;
import com.ppanticona.domain.User;
import com.ppanticona.security.AuthoritiesConstants;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import java.time.Instant;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Creates the initial database setup.
 */
@ChangeUnit(id = "users-initializationv2", order = "001")
public class InitialSetupMigration {

    private final MongoTemplate template;

    public InitialSetupMigration(MongoTemplate template) {
        this.template = template;
    }

    @Execution
    public void changeSet() {
        Authority userAuthority = createUserAuthority();
        userAuthority = template.save(userAuthority);
        Authority adminAuthority = createAdminAuthority();
        adminAuthority = template.save(adminAuthority);
        
        Authority cajeroAuthority = createCajeroAuthority();
        cajeroAuthority = template.save(cajeroAuthority);
        Authority meseroAuthority = createMeseroAuthority();
        meseroAuthority = template.save(meseroAuthority);
        Authority cocineroAuthority = createCocineroAuthority();
        cocineroAuthority = template.save(cocineroAuthority);
        Authority gerenteAuthority = createGerenteAuthority();
        gerenteAuthority = template.save(gerenteAuthority);
        Authority inventarioAuthority = createInventarioAuthority();
        inventarioAuthority = template.save(inventarioAuthority);
        addUsers(userAuthority, adminAuthority);
        
    }

    @RollbackExecution
    public void rollback() {}

    private Authority createAuthority(String authority) {
        Authority adminAuthority = new Authority();
        adminAuthority.setName(authority);
        return adminAuthority;
    }

    private Authority createAdminAuthority() {
        Authority adminAuthority = createAuthority(AuthoritiesConstants.ADMIN);
        return adminAuthority;
    }

    private Authority createUserAuthority() {
        Authority userAuthority = createAuthority(AuthoritiesConstants.USER);
        return userAuthority;
    }
    private Authority createCajeroAuthority() {
        Authority cajeroAuthority = createAuthority(AuthoritiesConstants.CAJERO);
        return cajeroAuthority;
    }

    private Authority createMeseroAuthority() {
        Authority meseroAuthority = createAuthority(AuthoritiesConstants.MESERO);
        return meseroAuthority;
    }
    private Authority createCocineroAuthority() {
        Authority cocineroAuthority = createAuthority(AuthoritiesConstants.COCINERO);
        return cocineroAuthority;
    }

    private Authority createGerenteAuthority() {
        Authority gerenteAuthority = createAuthority(AuthoritiesConstants.GERENTE);
        return gerenteAuthority;
    }
    private Authority createInventarioAuthority() {
        Authority inventarioAuthority = createAuthority(AuthoritiesConstants.INVENTARIO);
        return inventarioAuthority;
    }
 

    private void addUsers(Authority userAuthority, Authority adminAuthority) {
        User user = createUser(userAuthority);
        template.save(user);
        User admin = createAdmin(adminAuthority, userAuthority);
        template.save(admin);
    }

    private User createUser(Authority userAuthority) {
        User userUser = new User();
        userUser.setId("user-2");
        userUser.setLogin("user");
        userUser.setPassword("$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K");
        userUser.setFirstName("");
        userUser.setLastName("User");
        userUser.setEmail("user@localhost");
        userUser.setActivated(true);
        userUser.setLangKey("es");
        userUser.setCreatedBy(Constants.SYSTEM);
        userUser.setCreatedDate(Instant.now());
        userUser.getAuthorities().add(userAuthority);
        return userUser;
    }

    private User createAdmin(Authority adminAuthority, Authority userAuthority) {
        User adminUser = new User();
        adminUser.setId("user-1");
        adminUser.setLogin("admin");
        adminUser.setPassword("$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC");
        adminUser.setFirstName("admin");
        adminUser.setLastName("Administrator");
        adminUser.setEmail("admin@localhost");
        adminUser.setActivated(true);
        adminUser.setLangKey("es");
        adminUser.setCreatedBy(Constants.SYSTEM);
        adminUser.setCreatedDate(Instant.now());
        adminUser.getAuthorities().add(adminAuthority);
        adminUser.getAuthorities().add(userAuthority);
        return adminUser;
    }
}
