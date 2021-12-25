package Nurbol.Services;

import Nurbol.Entities.Role;
import Nurbol.Repositories.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;

@Stateful
@RunWith(JUnit4.class)
public class RoleService {
    @Inject
    RoleRepository roleRepository;

    @Test
    public List<Role> getAllRoles() {return roleRepository.findAll();}

    @Test
    public Role getRoleByName(String name) {return roleRepository.findByName(name);}
}
