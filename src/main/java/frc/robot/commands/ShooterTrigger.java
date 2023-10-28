// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;

public class ShooterTrigger extends CommandBase {
    private Shooter shooter;

    /** Creates a new ShooterTrigger. */
    public ShooterTrigger(Shooter _shooter) {
        addRequirements(RobotContainer.m_shooter);
        shooter = _shooter;
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(RobotContainer.controller.a().getAsBoolean())
            shooter.rotate(0.6);
        else if(RobotContainer.controller.b().getAsBoolean())
            shooter.rotate(-.3);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        shooter.rotate(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
