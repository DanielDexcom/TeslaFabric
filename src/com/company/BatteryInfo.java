package com.company;

public final class BatteryInfo {
    float estimatedVoltageRemaining;
    float batteryPercentage;

    public BatteryInfo(float estimatedVoltageRemaining, float batteryPercentage) {
        this.estimatedVoltageRemaining = estimatedVoltageRemaining;
        this.batteryPercentage = batteryPercentage;
    }
}
