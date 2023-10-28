// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Shooter extends SubsystemBase {
    private WPI_TalonSRX arm = new WPI_TalonSRX(4);

    public Shooter() {}

    public void rotate(double rotate) {
        arm.set(TalonSRXControlMode.PercentOutput, rotate * -.6);
    }

    public void fire() throws Exception {
        throw new Exception("Not yet implemented");
    }

    public CommandBase rotateForward() {
        return runOnce(() -> {
            rotate(1);
        });
    }

    public CommandBase rotateBackwards() {
        return runOnce(() -> {
            rotate(-0.3);
        });
    }

    @Override
    public void periodic() {}
}
