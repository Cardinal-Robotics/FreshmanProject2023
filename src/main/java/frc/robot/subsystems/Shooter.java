// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Shooter extends SubsystemBase {
    private WPI_TalonSRX shooterMotor = new WPI_TalonSRX(4);

    public Shooter() {}

    public void rotate(double rotate) {
        shooterMotor.set(TalonSRXControlMode.PercentOutput, rotate);
    }

    public void fire() throws Exception {
        throw new Exception("Not yet implemented");
    }

    public CommandBase executeCommand() {
        return runOnce(() -> {
            RobotContainer.m_shooterTrigger.execute();
        });
    }

    @Override
    public void periodic() {}
}
