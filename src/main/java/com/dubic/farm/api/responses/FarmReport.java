package com.dubic.farm.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmReport {
    private int season;
    private String farm;
    private int totalExpected;
    private int totalActual;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (FarmReport) obj;
        return this.season == that.season &&
                Objects.equals(this.farm, that.farm) &&
                this.totalExpected == that.totalExpected &&
                this.totalActual == that.totalActual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(season, farm, totalExpected, totalActual);
    }

    @Override
    public String toString() {
        return "FarmReport[" +
                "season=" + season + ", " +
                "farm=" + farm + ", " +
                "totalExpected=" + totalExpected + ", " +
                "totalActual=" + totalActual + ']';
    }

}
