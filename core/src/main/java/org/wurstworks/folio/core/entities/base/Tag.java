/*
 * folio: org.wurstworks.folio.core.entities.base.Tag
 * Folio https://github.com/wurstworks/folio
 * Copyright (c) 2021, WurstWorks Development
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.wurstworks.folio.core.entities.base;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Tag extends AbstractFolioEntity {
    public static Set<Tag> of(final String... tags) {
        return Arrays.stream(tags).map(Tag::of).collect(Collectors.toSet());
    }

    public static Tag of(final String tag) {
        return new Tag(tag);
    }

    @Override
    public String toString() {
        return getValue();
    }

    @NotNull
    @Column(unique = true)
    private String value;
}