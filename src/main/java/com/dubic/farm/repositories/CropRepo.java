package com.dubic.farm.repositories;

import com.dubic.farm.api.responses.CropReport;
import com.dubic.farm.api.responses.FarmReport;
import com.dubic.farm.models.Crop;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CropRepo extends CrudRepository<Crop, String> {
    Optional<Crop> findByFarmAndType(String farm, String type);

    @Query("""
            select c.season, 
            c.farm, 
            sum(c.expected) as total_expected,
             sum(c.harvested)  as total_actual
            from crop c group by c.season, c.farm
            """)
    List<FarmReport> getFarmReports();

    @Query("""
            select c.season, 
            c.type as crop, 
            sum(c.expected) as expected,
             sum(c.harvested)  as actual
            from crop c group by c.season, c.type
            """)
    List<CropReport> getCropReports();
}
