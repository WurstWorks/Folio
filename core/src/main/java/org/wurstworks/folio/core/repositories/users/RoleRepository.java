/*
 * folio: org.wurstworks.folio.core.repositories.users.RoleRepository
 * Folio https://github.com/wurstworks/folio
 * Copyright (c) 2021, WurstWorks Development
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.wurstworks.folio.core.repositories.users;

import org.wurstworks.folio.core.entities.users.Role;
import org.wurstworks.folio.core.repositories.base.AbstractFolioRepository;

import java.util.List;

public interface RoleRepository extends AbstractFolioRepository<Role> {
    boolean existsByAuthority(final String authority);

    Role findByAuthority(final String authority);

    List<Role> findAllByAuthorityIn(final List<String> authorities);
}
