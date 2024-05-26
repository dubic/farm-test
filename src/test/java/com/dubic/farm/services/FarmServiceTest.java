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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class FarmServiceTest {
    private FarmRepo farmRepo = mock(FarmRepo.class);
    private CropRepo cropRepo = mock(CropRepo.class);
    private FarmService farmService;

    @BeforeEach
    public void setUp() {
        this.farmService = new FarmService(farmRepo, cropRepo);
    }

    @Test
    void savePlanted() {
        //given
        given(farmRepo.findById("farm a")).willReturn(Optional.of(
                new Farm("farm a", 202, 0)
        ));
        given(farmRepo.save(any(Farm.class))).willReturn(new Farm("farm a", 202, 0));
        given(cropRepo.save(any(Crop.class))).willReturn(new Crop(1L, CropType.CORN.name(), "farm a", 2023, 6, 2, 0));
        //when
        CropType saved = this.farmService.savePlanted(new PlantRequest(CropType.CORN, "farm a", 5, 2023));
        //then
        assertThat(saved, notNullValue());
        assertThat(saved, is(CropType.CORN));
    }


    @Test
    void saveHarvested() {
        //given
        given(cropRepo.findByFarmAndType("farm a", CropType.CORN.name())).willReturn(Optional.of(
                new Crop(1L, CropType.CORN.name(), "farm a", 2023, 6, 2, 0)
        ));
        given(cropRepo.save(any(Crop.class))).willReturn(new Crop(1L, CropType.CORN.name(), "farm a", 2023, 6, 2, 0));
        //when
        CropType saved = this.farmService.saveHarvested(new HarvestRequest(CropType.CORN, "farm a", 2, 2023));
        //then
        assertThat(saved, notNullValue());
        assertThat(saved, is(CropType.CORN));
    }

    @Test
    void saveHarvested_no_farm_or_crop() {
        //given
        given(cropRepo.findByFarmAndType("farm a", CropType.CORN.name())).willReturn(Optional.empty());
        given(cropRepo.save(any(Crop.class))).willReturn(new Crop(1L, CropType.CORN.name(), "farm a", 2023, 6, 2, 0));
        //when
        assertThrows(IllegalArgumentException.class, () ->
                this.farmService.saveHarvested(new HarvestRequest(CropType.CORN, "farm a", 2, 2023)));
    }

    @Test
    void farmReports() {
        //given
        given(cropRepo.getFarmReports()).willReturn(List.of(
                new FarmReport(2023, "farm a", 16, 12)
        ));
        //when
        List<FarmReport> farmReports = this.farmService.getFarmReports();
        assertThat(farmReports, notNullValue());
        assertThat(farmReports.size(), is(1));
        assertThat(farmReports.get(0).getFarm(), is("farm a"));
        assertThat(farmReports.get(0).getTotalActual(), is(12));
        assertThat(farmReports.get(0).getTotalExpected(), is(16));
        assertThat(farmReports.get(0).getSeason(), is(2023));
    }

    @Test
    void cropReports() {
        //given
        given(cropRepo.getCropReports()).willReturn(List.of(
                new CropReport(2023, "CORN", 16, 12)
        ));
        //when
        List<CropReport> cropReports = this.farmService.getCropReports();
        assertThat(cropReports, notNullValue());
        assertThat(cropReports.size(), is(1));
        assertThat(cropReports.get(0).getCrop(), is("CORN"));
        assertThat(cropReports.get(0).getActual(), is(12));
        assertThat(cropReports.get(0).getExpected(), is(16));
        assertThat(cropReports.get(0).getSeason(), is(2023));
    }
}