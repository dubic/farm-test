package com.dubic.farm.services;

import com.dubic.farm.api.requests.HarvestRequest;
import com.dubic.farm.api.requests.PlantRequest;
import com.dubic.farm.api.responses.CropReport;
import com.dubic.farm.api.responses.FarmReport;
import com.dubic.farm.models.Crop;
import com.dubic.farm.models.CropType;
import com.dubic.farm.models.Farm;
import com.dubic.farm.repositories.CropRepo;
import com.dubic.farm.repositories.FarmRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FarmService {
    private final FarmRepo farmRepo;
    private final CropRepo cropRepo;

    public FarmService(FarmRepo farmRepo, CropRepo cropRepo) {
        this.farmRepo = farmRepo;
        this.cropRepo = cropRepo;
    }

    public CropType savePlanted(PlantRequest plantRequest) {
        log.info("Saving crop {} planted data: {}", plantRequest.cropType(), plantRequest);
        //create a farm if it does not exist.
        Farm farm = this.farmRepo.findById(plantRequest.farm())
                .orElseGet(() -> this.farmRepo.save(new Farm(plantRequest.farm(), 205, 0)));
        Crop saved = this.cropRepo.save(new Crop(
                null,
                plantRequest.cropType().name(),
                farm.name(),
                plantRequest.season(),
                plantRequest.expected(), 0, 0));
        return CropType.valueOf(saved.type());
    }

    public CropType saveHarvested(HarvestRequest harvestRequest) {
        log.info("Saving crop {} harvested data: {}", harvestRequest.cropType(), harvestRequest);
        //Farm and crop must be created
        Crop found = this.cropRepo.findByFarmAndType(harvestRequest.farm(), harvestRequest.cropType().name())
                .orElseThrow(() -> new IllegalArgumentException("Farm and crop planted data has not been set "));
        Crop saved = this.cropRepo.save(new Crop(
                found.id(),
                found.type(),
                found.farm(),
                harvestRequest.season(),
                found.expected(),
                harvestRequest.harvested(),
                found.version()
        ));
        return CropType.valueOf(saved.type());
    }

    public List<FarmReport> getFarmReports() {
        return this.cropRepo.getFarmReports();
    }

    public List<CropReport> getCropReports() {
        return this.cropRepo.getCropReports();
    }
}
