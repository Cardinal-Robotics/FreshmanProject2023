// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Shooter extends SubsystemBase {
    private TalonSRX arm = new TalonSRX(2);

    public Shooter() {}

    public void rotate(double rotate) {
        arm.set(TalonSRXControlMode.PercentOutput, rotate * -.6);
    }

    public void fire() throws Exception {
        throw new Exception("Not yet implemented");
    }

    public CommandBase executeRotation() {
        return runOnce(() -> {
            RobotContainer.m_shooterRot.execute();
        });
    }

    @Override
    public void periodic() {}
}
