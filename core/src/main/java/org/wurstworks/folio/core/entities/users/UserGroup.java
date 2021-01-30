/*
 * folio: org.wurstworks.folio.core.entities.users.UserGroup
 * Folio https://github.com/wurstworks/folio
 * Copyright (c) 2021, WurstWorks Development
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.wurstworks.folio.core.entities.users;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.wurstworks.folio.core.entities.base.AbstractFolioEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserGroup extends AbstractFolioEntity implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return name;
    }

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<User> users = new HashSet<>();
}
