package com.valledor.gclassroom_clone.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.valledor.gclassroom_clone")
@EnableJpaRepositories("com.valledor.gclassroom_clone")
@EnableTransactionManagement
public class DomainConfig {
}
