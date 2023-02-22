package com.company;

public class ModelSBattery {
    public final float MODEL_S_VOLTAGE = 300;
    public final float MIN_MODEL_S_VOLTAGE = 250;

    private float batteryPercentage = 0;
    private boolean turnedOn = false;

    private HighVoltageCabling highVoltageCabling;

    public ModelSBattery(HighVoltageCabling highVoltageCabling) {
        this.highVoltageCabling = highVoltageCabling;
    }

    public float getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setBatteryPercentage(float batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    public void power(boolean on) {
        if (!turnedOn) {
            this.turnedOn = on;
            System.out.println("Battery power off...");
        }
        if (hasMinimumChargeToTurnOn()) {
            highVoltageCabling.setVoltage(MODEL_S_VOLTAGE);
            System.out.println("Battery power on...");
        } else  {
            System.out.println("Not enough charge to power on...");
        }
    }

    public boolean isTurnedOn() {
        return turnedOn;
    }

    public boolean hasMinimumChargeToTurnOn() {
        return batteryPercentage >= 5;
    }
}
