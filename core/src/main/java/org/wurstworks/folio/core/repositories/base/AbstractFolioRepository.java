/*
 * folio: org.wurstworks.folio.core.repositories.base.BaseRepository
 * Folio https://github.com/wurstworks/folio
 * Copyright (c) 2021, WurstWorks Development
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.wurstworks.folio.core.repositories.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.wurstworks.folio.core.entities.base.AbstractFolioEntity;
import org.wurstworks.folio.core.entities.users.User;
import org.wurstworks.folio.core.repositories.base.impl.AbstractFolioRepositoryImpl;

import java.util.Date;
import java.util.List;

@NoRepositoryBean
public interface AbstractFolioRepository<E extends AbstractFolioEntity> extends JpaRepository<E, Long> {
    List<E> findAllByCreatedBy(final String createdBy);

    List<E> findAllByCreatedBy(final User createdBy);

    List<E> findAllByLastModifiedBy(final String lastModifiedBy);

    List<E> findAllByLastModifiedBy(final User lastModifiedBy);

    List<E> findAllByCreated(final Date created);

    List<E> findAllByCreatedBefore(final Date created);

    List<E> findAllByCreatedAfter(final Date created);

    List<E> findAllByCreatedBetween(final Date start, final Date end);

    List<E> findAllByLastModified(final Date lastModified);

    List<E> findAllByLastModifiedBefore(final Date lastModified);

    List<E> findAllByLastModifiedAfter(final Date lastModified);

    List<E> findAllByLastModifiedBetween(final Date start, final Date end);
}