/*
 * folio: org.wurstworks.folio.core.FolioCore
 * Folio https://github.com/wurstworks/folio
 * Copyright (c) 2021, WurstWorks Development
 * All Rights Reserved
 *
 * Released under the Simplified BSD.
 */

package org.wurstworks.folio.core;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.wurstworks.folio.core.repositories.base.impl.AbstractFolioRepositoryImpl;

@Configuration
@EnableJpaRepositories(basePackages = "org.wurstworks.folio.core.repositories", repositoryBaseClass = AbstractFolioRepositoryImpl.class)
@EnableTransactionManagement
// @EnableMongoRepositories(basePackages = "org.wurstworks.folio.core.documents")
@EntityScan("org.wurstworks.folio.core.entities")
public class FolioCore {
}
