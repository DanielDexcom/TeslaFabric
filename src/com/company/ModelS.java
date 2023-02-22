package com.company;

public class ModelS {
    private final FrontMotor frontMotor;
    private final ModelSBattery modelSBattery;
    private final HeatPump heatPump;

    private final float MAX_SPEED_KM_HOURS = 200;
    private final float SPEED_PER_SECOND = 50;

    private float currentSpeed = 0;

    public ModelS(FrontMotor frontMotor, ModelSBattery modelSBattery, HeatPump heatPump) {
        this.frontMotor = frontMotor;
        this.modelSBattery = modelSBattery;
        this.heatPump = heatPump;
    }

    public void turnOnCar() {
        modelSBattery.power(true);
        frontMotor.startUp();
    }

    public boolean isTurnedOn() {
        return modelSBattery.isTurnedOn() && frontMotor.isMotorTurnedOn();
    }

    public boolean isBatteryFull() {
        return modelSBattery.getBatteryPercentage() >= 100;
    }

    public boolean requiresCharging() {
        return modelSBattery.getBatteryPercentage() <= 0;
    }

    public void pluginCharger(ModelSBatteryCharger modelSBatteryCharger) {
        modelSBatteryCharger.chargeBattery(modelSBattery);
    }

    public BatteryInfo getBatteryInfo() {
        float percentage = modelSBattery.getBatteryPercentage();
        float estimatedVoltage = calculateBatteryVoltage();
        return new BatteryInfo(estimatedVoltage, percentage);
    }

    public void accelerate() {
        useBatteryPower();
        if (requiresCharging()) {
            return;
        }

        if (currentSpeed < MAX_SPEED_KM_HOURS) {
            currentSpeed += SPEED_PER_SECOND;
        }

        System.out.println("Accelerating... " + currentSpeed + "km/h");
        useBatteryPower();
    }

    public void breaks() {
        useBatteryPower();
        if (requiresCharging()) {
            return;
        }

        if (currentSpeed > 0) {
            currentSpeed -= SPEED_PER_SECOND;
        }

        System.out.println("Breaking... " + currentSpeed + "km/h");
    }

    private void useBatteryPower() {
        float currentBattery = modelSBattery.getBatteryPercentage();
        modelSBattery.setBatteryPercentage(currentBattery - 10);
        if (currentBattery == 0) {
            System.out.println("Need to recharge!!");
        }
    }

    private float calculateBatteryVoltage() {
       float voltageDelta = modelSBattery.MODEL_S_VOLTAGE - modelSBattery.MIN_MODEL_S_VOLTAGE;
       float estimatedVoltageRemaining = voltageDelta * modelSBattery.getBatteryPercentage() / 100;
       return modelSBattery.MIN_MODEL_S_VOLTAGE - estimatedVoltageRemaining;
    }
}
