package com.dubic.farm.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

@Table("crop")
public record Crop(
        @Id
        Long id,
        String type,
        String farm,
        int season,
        int expected,
        int harvested,
        @Version
        int version) {
}
