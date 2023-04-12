package com.wa.drools.configuration;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RuleConfiguration
 * 2023/2/15 11:14 上午
 *
 * @author wuao
 */
@Configuration
public class RuleConfiguration {

    @Bean
    public InternalKnowledgeBase internalKnowledgeBase() {
        return KnowledgeBaseFactory.newKnowledgeBase();
    }

    @Bean
    public StatelessKieSession statelessKieSession(InternalKnowledgeBase base) {
        return base.newStatelessKieSession();
    }

}
