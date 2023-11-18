// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.subsystems.DriveTrain;
import frc.robot.RobotContainer;

public class AutonomousDrive extends CommandBase {
    private boolean m_ready = false;

    private DriveTrain train;

    public AutonomousDrive(DriveTrain driveTrain) {
        addRequirements(RobotContainer.m_driveTrain);
        train = driveTrain;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        train.CurveDrive(1, 0);

        Timer.delay(5);

        train.CurveDrive(-1.5, 0);

        Timer.delay(2);

        train.CurveDrive(0, 0);


        m_ready = true;
    }

    @Override
    public void end(boolean interrupted) {
        train.CurveDrive(-1.5, 0);

        Timer.delay(1);

        train.CurveDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return m_ready;
    }
}
