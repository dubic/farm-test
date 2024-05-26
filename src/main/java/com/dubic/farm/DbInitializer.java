package com.dubic.farm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class DbInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DbInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Initializing farm and crop tables");
        this.jdbcTemplate.execute(readFileContent("create-tables.sql"));
    }

    public String readFileContent(String fileName) throws IOException {
        Resource resource = new ClassPathResource(fileName);
        return resource.getContentAsString(StandardCharsets.UTF_8);
    }
}
