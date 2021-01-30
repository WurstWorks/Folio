/*
 * folio: org.wurstworks.folio.core.TestFolioDataEntities
 * Folio https://github.com/wurstworks/folio
 * Copyright (c) 2021, WurstWorks Development
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.wurstworks.folio.core;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.wurstworks.folio.core.repositories.base.TagRepository;
import org.wurstworks.folio.core.repositories.users.RoleRepository;
import org.wurstworks.folio.core.repositories.users.UserGroupRepository;
import org.wurstworks.folio.core.repositories.users.UserRepository;

@DataJpaTest
public class TestFolioDataEntities {
    @Autowired
    TestFolioDataEntities(final RoleRepository roleRepository, final TagRepository tagRepository, final UserGroupRepository userGroupRepository, final UserRepository userRepository) {
        _roleRepository = roleRepository;
        _tagRepository = tagRepository;
        _userGroupRepository = userGroupRepository;
        _userRepository = userRepository;
    }

    @Test
    void sanityTest() {
        assertThat(_roleRepository).isNotNull();
        assertThat(_tagRepository).isNotNull();
        assertThat(_userGroupRepository).isNotNull();
        assertThat(_userRepository).isNotNull();
    }

    private final RoleRepository      _roleRepository;
    private final TagRepository       _tagRepository;
    private final UserGroupRepository _userGroupRepository;
    private final UserRepository      _userRepository;
}
