package com.dubic.farm.services;

import com.dubic.farm.api.requests.HarvestRequest;
import com.dubic.farm.api.requests.PlantRequest;
import com.dubic.farm.models.Crop;
import com.dubic.farm.models.CropType;
import com.dubic.farm.models.Farm;
import com.dubic.farm.repositories.CropRepo;
import com.dubic.farm.repositories.FarmRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
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
}