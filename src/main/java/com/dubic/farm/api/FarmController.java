package com.dubic.farm.api;

import com.dubic.farm.api.requests.HarvestRequest;
import com.dubic.farm.api.requests.PlantRequest;
import com.dubic.farm.api.responses.CropReport;
import com.dubic.farm.api.responses.FarmReport;
import com.dubic.farm.models.CropType;
import com.dubic.farm.services.FarmService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/farm")
public class FarmController {
    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @PostMapping("/planted")
    @Operation(summary = "Submit crop planted", description = "Submits the data for the crop planted.")
    public ResponseEntity<CropType> planted(@RequestBody PlantRequest plantRequest) {
        CropType type = this.farmService.savePlanted(plantRequest);
        return ResponseEntity.ok(type);
    }

    @PostMapping("/harvested")
    @Operation(summary = "Submit crop harvested", description = "Submits the data for the crop harvested.")
    public ResponseEntity<CropType> harvested(@RequestBody HarvestRequest harvestRequest) {
        CropType type = this.farmService.saveHarvested(harvestRequest);
        return ResponseEntity.ok(type);
    }

    @GetMapping("/reports")
    @Operation(summary = "View farm report", description = "Returns the a report of total expected produce vs actual produce")
    public ResponseEntity<List<FarmReport>> totalReports() {
        List<FarmReport> farmReports = this.farmService.getFarmReports();
        return ResponseEntity.ok(farmReports);
    }

    @GetMapping("/reports/crops")
    @Operation(summary = "View crop reports", description = "Returns the a report of the expected produce vs actual produce of a crop")
    public ResponseEntity<List<CropReport>> cropReports() {
        List<CropReport> reports = this.farmService.getCropReports();
        return ResponseEntity.ok(reports);
    }

}
