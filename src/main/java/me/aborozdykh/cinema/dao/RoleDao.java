package me.aborozdykh.cinema.dao;

import me.aborozdykh.cinema.models.Role;

public interface RoleDao extends AbstractDao<Role> {
    Role getRoleByName(String roleName);
}
