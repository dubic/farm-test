package com.dubic.farm.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CropReport {
    private int season;
    private String crop;
    private int expected;
    private int actual;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CropReport) obj;
        return this.season == that.season &&
                Objects.equals(this.crop, that.crop) &&
                this.expected == that.expected &&
                this.actual == that.actual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(season, crop, expected, actual);
    }

    @Override
    public String toString() {
        return "CropReport[" +
                "season=" + season + ", " +
                "crop=" + crop + ", " +
                "expected=" + expected + ", " +
                "actual=" + actual + ']';
    }

}
