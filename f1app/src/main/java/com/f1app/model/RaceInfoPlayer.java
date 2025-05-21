package com.f1app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class RaceInfoPlayer {

    @Id
    private int driverNumber;

    private String shortName;
    private float gapToCarInFront;
    private float gapToLeader;

    @Enumerated(EnumType.STRING)
    private DRS drsStatus;

    @Enumerated(EnumType.STRING)
    private TyreType tyreType;

    public enum DRS {
        DRS_ON,
        DRS_AVAILABLE,
        DRS_OFF,
        PITS
    }

    public enum TyreType {
        WETS,
        INTERS,
        SOFT,
        MEDIUMS,
        HARDS,
        UNKNOWN
    }

    public void setDriverNumber(int driverNumber)
    {
        this.driverNumber = driverNumber;
    }

    public int getDriverNumber()
    {
        return this.driverNumber;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public float getGapToCarInFront() {
        return gapToCarInFront;
    }

    public void setGapToCarInFront(float gapToCarInFront) {
        this.gapToCarInFront = gapToCarInFront;
    }

    public float getGapToLeader() {
        return gapToLeader;
    }

    public void setGapToLeader(float gapToLeader) {
        this.gapToLeader = gapToLeader;
    }
    

    public DRS getDrsStatus() {
        return drsStatus;
    }

    public void setDrsStatus(DRS drsStatus) {
        this.drsStatus = drsStatus;
    }

    public TyreType getTyreType() {
        return tyreType;
    }

    public void setTyreType(TyreType tyreType) {
        this.tyreType = tyreType;
    }
}
