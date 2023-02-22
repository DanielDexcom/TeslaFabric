package com.company;

public class ModelSBatteryCharger {

    private final float CHARGER_PERCENTAGE_PER_MINUTE = 20;

    public void chargeBattery(ModelSBattery modelSBattery) {
        if (modelSBattery.getBatteryPercentage() >= 100) {
            System.out.println("Battery fully charged");
            return;
        }
        float batteryPercentage = modelSBattery.getBatteryPercentage() + CHARGER_PERCENTAGE_PER_MINUTE;
        modelSBattery.setBatteryPercentage(batteryPercentage);
        System.out.println("Battery percentage: " + batteryPercentage + "%");
    }
}
