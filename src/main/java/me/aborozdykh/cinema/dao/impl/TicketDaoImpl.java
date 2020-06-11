package me.aborozdykh.cinema.dao.impl;

import me.aborozdykh.cinema.dao.TicketDao;
import me.aborozdykh.cinema.models.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl extends AbstractDaoImpl<Ticket> implements TicketDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public TicketDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Ticket get(Long id) {
        return super.get(Ticket.class, id);
    }
}
