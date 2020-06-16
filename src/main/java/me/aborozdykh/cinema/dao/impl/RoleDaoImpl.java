package me.aborozdykh.cinema.dao.impl;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import me.aborozdykh.cinema.dao.RoleDao;
import me.aborozdykh.cinema.exceptions.DataProcessingException;
import me.aborozdykh.cinema.models.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Andrii Borozdykh
 */
@Repository
public class RoleDaoImpl extends AbstractDaoImpl<Role> implements RoleDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role get(Long id) {
        return super.get(Role.class, id);
    }

    @Override
    public Role getRoleByName(String roleName) {
        try (var session = sessionFactory.openSession()) {
            var cb = session.getCriteriaBuilder();
            CriteriaQuery<Role> query = cb.createQuery(Role.class);
            Root<Role> roleRoot = query.from(Role.class);
            query.select(roleRoot)
                    .where(cb.like(roleRoot.get("roleName"), roleName));
            return session.createQuery(query).uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find role with roleName " + roleName, e);
        }
    }
}
