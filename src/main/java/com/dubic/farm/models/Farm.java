package com.dubic.farm.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;


@Table("farm")
public record Farm(
        @Id
        String name,
        int area,
        @Version
        int version) {
}
