package com.ratepay.bugtracker.config;

import com.ratepay.bugtracker.repository.BugRepository;
import com.ratepay.bugtracker.service.BugService;
import com.ratepay.bugtracker.service.impl.BugServiceImpl;
import org.springframework.context.annotation.Bean;

public class BeanConfig {
    @Bean
    public BugService bugService(BugRepository bugRepository) {
        return new BugServiceImpl(bugRepository);
    }
}
