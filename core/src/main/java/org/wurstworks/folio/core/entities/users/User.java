/*
 * folio: org.wurstworks.folio.core.entities.users.User
 * Folio https://github.com/wurstworks/folio
 * Copyright (c) 2021, WurstWorks Development
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.wurstworks.folio.core.entities.users;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.wurstworks.folio.core.entities.base.AbstractFolioEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractFolioEntity implements UserDetails {
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.concat(roles.stream(), groups.stream()).collect(Collectors.toSet());
    }

    private String username;

    private String password;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<UserGroup> groups = new HashSet<>();
}
