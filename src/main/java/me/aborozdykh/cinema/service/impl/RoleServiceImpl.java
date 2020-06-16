package me.aborozdykh.cinema.service.impl;

import me.aborozdykh.cinema.dao.RoleDao;
import me.aborozdykh.cinema.models.Role;
import me.aborozdykh.cinema.service.RoleService;

/**
 * @author Andrii Borozdykh
 */
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Role get(Long id) {
        return roleDao.get(id);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }
}
