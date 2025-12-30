package Devonox.oktaauthentication.security;


import Devonox.oktaauthentication.enums.Role;
import org.springframework.stereotype.Component;

@Component("roles")
public class RoleBean {

    public String ADMIN = Role.ADMIN.name();
    public String USER = Role.USER.name();
    public String LOAN_OFFICER = Role.LOAN_OFFICER.name();
}

