/*
 * folio: org.wurstworks.folio.core.repositories.users.UserGroupRepository
 * Folio https://github.com/wurstworks/folio
 * Copyright (c) 2021, WurstWorks Development
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.wurstworks.folio.core.repositories.users;

import org.wurstworks.folio.core.entities.users.UserGroup;
import org.wurstworks.folio.core.repositories.base.AbstractFolioRepository;

import java.util.List;

public interface UserGroupRepository extends AbstractFolioRepository<UserGroup> {
    boolean existsByName(final String name);

    UserGroup findByName(final String name);

    List<UserGroup> findAllByNameIn(final List<String> names);
}
