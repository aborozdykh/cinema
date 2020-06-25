package me.aborozdykh.cinema.service;

import me.aborozdykh.cinema.models.Role;

/**
 * @author Andrii Borozdykh
 */

public interface RoleService extends AbstractService<Role> {
    Role getRoleByName(String roleName);
}
