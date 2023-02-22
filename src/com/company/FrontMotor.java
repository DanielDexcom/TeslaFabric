package com.company;

public class FrontMotor {

    HighVoltageCabling highVoltageCabling;

    private final float MIN_REQUIRED_VOLTAGE = 270;

    public FrontMotor(HighVoltageCabling highVoltageCabling) {
        this.highVoltageCabling = highVoltageCabling;
    }

    public boolean startUp() {
        if (isMotorTurnedOn()) {
            System.out.println("Motor started up...");
            return true;
        }
        System.out.println("Not enough voltage to startUp motor");
        return false;
    }

    public boolean isMotorTurnedOn() {
        return highVoltageCabling.getVoltage() > MIN_REQUIRED_VOLTAGE;
    }

    public void runDiagnostic() {
        System.out.println("Checking motor...");
    }
}
