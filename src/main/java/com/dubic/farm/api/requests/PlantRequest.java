package com.dubic.farm.api.requests;

import com.dubic.farm.models.CropType;

public record PlantRequest(CropType cropType, String farm, int expected, int season) {
}
