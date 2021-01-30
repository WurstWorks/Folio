/*
 * folio: org.wurstworks.folio.core.repositories.base.impl.AbstractFolioRepositoryImpl
 * Folio https://github.com/wurstworks/folio
 * Copyright (c) 2021, WurstWorks Development
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.wurstworks.folio.core.repositories.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.wurstworks.folio.core.entities.base.AbstractFolioEntity;
import org.wurstworks.folio.core.entities.users.User;
import org.wurstworks.folio.core.repositories.base.AbstractFolioRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

public class AbstractFolioRepositoryImpl<E extends AbstractFolioEntity> extends SimpleJpaRepository<E, Long> implements AbstractFolioRepository<E> {
    @Autowired
    public AbstractFolioRepositoryImpl(final JpaEntityInformation<E, ?> entityInformation, final EntityManager entityManager) {
        super(entityInformation, entityManager);
        _entityManager = entityManager;
    }

    @Override
    public List<E> findAllByCreatedBy(final String createdBy) {
        return findAllByStringAttribute("createdBy", createdBy);
    }

    @Override
    public List<E> findAllByCreatedBy(final User createdBy) {
        return findAllByCreatedBy(createdBy.getUsername());
    }

    @Override
    public List<E> findAllByLastModifiedBy(final String lastModifiedBy) {
        return findAllByStringAttribute("lastModifiedBy", lastModifiedBy);
    }

    @Override
    public List<E> findAllByLastModifiedBy(final User lastModifiedBy) {
        return findAllByLastModifiedBy(lastModifiedBy.getUsername());
    }

    @Override
    public List<E> findAllByCreated(final Date created) {
        return findAllByDateAttribute("created", created);
    }

    @Override
    public List<E> findAllByCreatedBefore(final Date created) {
        return findAllByDateAttributeBefore("created", created);
    }

    @Override
    public List<E> findAllByCreatedAfter(final Date created) {
        return findAllByDateAttributeAfter("created", created);
    }

    @Override
    public List<E> findAllByCreatedBetween(final Date start, final Date end) {
        return findAllByDateAttributeBetween("created", start, end);
    }

    @Override
    public List<E> findAllByLastModified(final Date lastModified) {
        return findAllByDateAttribute("lastModified", lastModified);
    }

    @Override
    public List<E> findAllByLastModifiedBefore(final Date lastModified) {
        return findAllByDateAttributeBefore("lastModified", lastModified);
    }

    @Override
    public List<E> findAllByLastModifiedAfter(final Date lastModified) {
        return findAllByDateAttributeAfter("lastModified", lastModified);
    }

    @Override
    public List<E> findAllByLastModifiedBetween(final Date start, final Date end) {
        return findAllByDateAttributeBetween("lastModified", start, end);
    }

    protected List<E> findAllByStringAttribute(final String attribute, final String value) {
        return findAllByStringAttribute(attribute, value, true);
    }

    protected List<E> findAllByStringAttributeLike(final String attribute, final String value) {
        return findAllByStringAttribute(attribute, value, false);
    }

    protected List<E> findAllByDateAttribute(final String attribute, final Date value) {
        return findAllByDateAttribute(attribute, DateCriteria.Exact, value, null);
    }

    protected List<E> findAllByDateAttributeBefore(final String attribute, final Date value) {
        return findAllByDateAttribute(attribute, DateCriteria.Before, value, null);
    }

    protected List<E> findAllByDateAttributeAfter(final String attribute, final Date value) {
        return findAllByDateAttribute(attribute, DateCriteria.After, value, null);
    }

    protected List<E> findAllByDateAttributeBetween(final String attribute, final Date start, final Date end) {
        return findAllByDateAttribute(attribute, DateCriteria.Between, start, end);
    }

    protected enum DateCriteria {
        Exact,
        Before,
        After,
        Between
    }

    private List<E> findAllByStringAttribute(final String attribute, final String value, final boolean exact) {
        final CriteriaBuilder  builder = _entityManager.getCriteriaBuilder();
        final CriteriaQuery<E> query   = builder.createQuery(getDomainClass());
        final Root<E>          root    = query.from(getDomainClass());
        final Predicate equal = exact
                                ? builder.equal(root.get(attribute), value)
                                : builder.like(root.get(attribute), "%" + value + "%");
        return _entityManager.createQuery(query.select(root).where(equal)).getResultList();
    }

    private List<E> findAllByDateAttribute(final String attribute, final DateCriteria criteria, final Date value, final Date end) {
        final CriteriaBuilder  builder    = _entityManager.getCriteriaBuilder();
        final CriteriaQuery<E> query      = builder.createQuery(getDomainClass());
        final Root<E>          root       = query.from(getDomainClass());
        final Expression<Date> expression = root.get(attribute);

        final Predicate predicate;
        switch (criteria) {
            case After:
                predicate = builder.greaterThan(expression, value);
                break;
            case Before:
                predicate = builder.lessThan(expression, value);
                break;
            case Between:
                predicate = builder.between(expression, value, end);
                break;
            default:
                predicate = builder.equal(expression, value);
        }

        return _entityManager.createQuery(query.select(root).where(predicate)).getResultList();
    }

    private final EntityManager _entityManager;
}
