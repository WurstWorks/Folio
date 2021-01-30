/*
 * folio: org.wurstworks.folio.core.repositories.users.UserRepository
 * Folio https://github.com/wurstworks/folio
 * Copyright (c) 2021, WurstWorks Development
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.wurstworks.folio.core.repositories.users;

import org.wurstworks.folio.core.entities.users.User;
import org.wurstworks.folio.core.repositories.base.AbstractFolioRepository;

import java.util.List;

public interface UserRepository extends AbstractFolioRepository<User> {
    boolean existsByUsername(final String username);

    User findByUsername(final String username);

    User findByUsernameAndPassword(final String username, final String password);

    List<User> findAllByUsernameIn(final List<String> usernames);
}
