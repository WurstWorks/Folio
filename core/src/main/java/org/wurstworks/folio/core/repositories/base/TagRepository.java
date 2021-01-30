/*
 * folio: org.wurstworks.folio.core.repositories.base.TagRepository
 * Folio https://github.com/wurstworks/folio
 * Copyright (c) 2021, WurstWorks Development
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.wurstworks.folio.core.repositories.base;

import org.wurstworks.folio.core.entities.base.Tag;

import java.util.List;

public interface TagRepository extends AbstractFolioRepository<Tag> {
    Tag findByValue(final String value);

    List<Tag> findAllByValueIn(final List<String> values);
}
