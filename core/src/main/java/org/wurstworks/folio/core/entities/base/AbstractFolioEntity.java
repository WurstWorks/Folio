/*
 * folio: org.wurstworks.folio.core.entities.base.BaseEntity
 * Folio https://github.com/wurstworks/folio
 * Copyright (c) 2021, WurstWorks Development
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.wurstworks.folio.core.entities.base;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractFolioEntity implements Serializable { // extends DefaultRevisionEntity { Consider extending org.hibernate.envers.DefaultRevisionEntity
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof AbstractFolioEntity)) {
            return false;
        }
        return Objects.equals(getId(), ((AbstractFolioEntity) object).getId());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Long   id;
    @CreatedBy
    protected String createdBy;
    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date   created;
    @LastModifiedBy
    protected String lastModifiedBy;
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date   lastModified;
}