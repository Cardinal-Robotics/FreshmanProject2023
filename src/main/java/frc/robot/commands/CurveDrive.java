// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveTrain;
import frc.robot.RobotContainer;

public class CurveDrive extends CommandBase {
    public CurveDrive(DriveTrain train) {
        addRequirements(RobotContainer.m_driveTrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        final double speed = (Math.pow(RobotContainer.controller.getRightTriggerAxis(), 3)
            - Math.pow(RobotContainer.controller.getLeftTriggerAxis(), 3));
        final double turn = RobotContainer.controller.getLeftX();

        RobotContainer.m_driveTrain.getDifferentialDrive()
            .curvatureDrive(speed, -turn * .6, false);

        if (speed != 0 && Math.abs(turn) > .1)
            RobotContainer.m_driveTrain.rotate(turn);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
