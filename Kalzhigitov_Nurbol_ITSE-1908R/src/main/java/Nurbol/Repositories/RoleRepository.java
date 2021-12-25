package Nurbol.Repositories;

import Nurbol.Entities.Role;

import java.util.List;

public interface RoleRepository {
    List<Role> findAll();
    Role findByName(String name);
}
