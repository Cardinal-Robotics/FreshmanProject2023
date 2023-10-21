// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveTrain;
import frc.robot.RobotContainer;

public class CurveDrive extends CommandBase {
    private DriveTrain train;

    public CurveDrive(DriveTrain driveTrain) {
        addRequirements(RobotContainer.m_driveTrain);
        train = driveTrain;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        final double speed = (Math.pow(RobotContainer.controller.getRightTriggerAxis(), 3)
            - Math.pow(RobotContainer.controller.getLeftTriggerAxis(), 3));
        final double turn = RobotContainer.controller.getLeftX();

        train.getDifferentialDrive()
            .curvatureDrive(speed, -turn * .6, false);

        if (speed != 0 && Math.abs(turn) > .1)
            train.rotate(turn);
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
