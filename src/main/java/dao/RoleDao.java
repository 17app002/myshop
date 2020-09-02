package dao;

import entity.Role;

import java.util.List;

public interface RoleDao {

    public Role login(String username, String password);

    public boolean register(Role role);

    public boolean check(Role role);

    public boolean update(Role role);

    public List<Role> findAll();


}
