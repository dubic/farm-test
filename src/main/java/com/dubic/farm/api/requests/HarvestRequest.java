package com.dubic.farm.api.requests;

import com.dubic.farm.models.CropType;

public record HarvestRequest(CropType cropType, String farm, int harvested, int season) {
}
