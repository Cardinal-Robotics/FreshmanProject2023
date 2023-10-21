// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.RobotContainer;

public class ShooterRot extends CommandBase {
    private Shooter shooter;

    public ShooterRot(Shooter shooterSystem) {
        addRequirements(RobotContainer.m_shooter);
        shooter = shooterSystem;  
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        final double turn = RobotContainer.controller.getRightX();

        // A Deadzone, 0.5 is where the joystick rests so anything 0.6 or above will act.
        if(Math.abs(turn) >= .6) return;
        shooter.rotate(turn * .6);
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
