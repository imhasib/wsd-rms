package com.hsb.rms.service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class CustomQueryExecutor {

    private final EntityManager entityManager;

    @Autowired
    public CustomQueryExecutor(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Object[]> executeNativeHQL(String queryString) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery(queryString, Object[].class);
        return query.getResultList();
    }

    public List<Object[]> executeNativeSQL(String queryString, Object... parameters) {
        Query query = entityManager.createNativeQuery(queryString);
        setParameters(query, parameters);
        return query.getResultList();
    }
    private void setParameters(Query query, Object... parameters) {
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                query.setParameter(i + 1, parameters[i]);
            }
        }
    }
}
