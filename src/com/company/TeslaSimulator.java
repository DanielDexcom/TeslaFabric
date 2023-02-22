package com.company;

public class TeslaSimulator {

    private final int SLEEP_TIME = 1000;

    public void startSimulation() throws InterruptedException {

        ModelS modelS = buildModelS();

        modelS.turnOnCar();

        ModelSBatteryCharger modelSBatteryCharger = new ModelSBatteryCharger();


        // Charge full power
        while (!modelS.isBatteryFull()) {
            modelS.pluginCharger(modelSBatteryCharger);
            Thread.sleep(SLEEP_TIME);
        }
        while (true) {
            if (modelS.isTurnedOn()) {
                modelS.turnOnCar();
            } else {
                modelS.accelerate();
            }
            Thread.sleep(SLEEP_TIME);
        }
    }

    private ModelS buildModelS() {
        HighVoltageCabling highVoltageCabling = new HighVoltageCabling();
        ModelSBattery modelSBattery = new ModelSBattery(highVoltageCabling);
        FrontMotor frontMotor = new FrontMotor(highVoltageCabling);
        HeatPump heatPump = new HeatPump(highVoltageCabling);
        return new ModelS(frontMotor, modelSBattery, heatPump);
    }
}
