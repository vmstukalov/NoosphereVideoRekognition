package ru.noosphere.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"ru.noosphere.config", "ru.noosphere.entities", "ru.noosphere.impl", "ru.noosphere.services", "ru.noosphere.controllers", "ru.noosphere.services.repo"})
public class SpringRootConfig {
}
