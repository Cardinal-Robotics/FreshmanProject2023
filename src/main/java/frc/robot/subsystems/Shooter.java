// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;

public class Shooter extends SubsystemBase {
    private WPI_TalonSRX shooterMotor = new WPI_TalonSRX(3);
    private WPI_TalonSRX feederMotor = new WPI_TalonSRX(4);

    public Shooter() {
        shooterMotor.setInverted(true);
        feederMotor.setInverted(true);
    }

    public void rotateShooter(double rotate) {
        shooterMotor.set(TalonSRXControlMode.PercentOutput, rotate);
    }

    public void rotateFeeder(double rotate) {
        feederMotor.set(TalonSRXControlMode.PercentOutput, rotate);
    }

    public boolean timedFire(double delay) {
        shooterMotor.set(TalonSRXControlMode.PercentOutput, 1);
        Timer.delay(delay);
        shooterMotor.set(TalonSRXControlMode.PercentOutput, 0);
        
        return true;
    }

    @Override
    public void periodic() {}
}
