// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;

import frc.robot.subsystems.Shooter;
import frc.robot.RobotContainer;

public class ShooterTrigger extends CommandBase {
    private ShooterInstruction m_instruction;
    private boolean m_finished = false;

    private Shooter shooter;

    public ShooterTrigger(Shooter _shooter, ShooterInstruction instruction) {
        addRequirements(RobotContainer.m_shooter);
        m_instruction = instruction;
        shooter = _shooter;
    }

    @Override
    public void initialize() {}

    public static enum ShooterInstruction {
        A(0), B(1), Fire(2);

        int instruction;

        private ShooterInstruction(int instruction) {
            this.instruction = instruction;
        }
    }
    
    @Override
    public void execute() {
        switch(m_instruction) {
            case A: shooter.rotateShooter(1);
                break;
            case B: shooter.rotateFeeder(0.3);
                break;
            case Fire: m_finished = shooter.timedFire(2);
        }
        
        _checkForInput();
    }

    @Override
    public void end(boolean interrupted) {
        _checkForInput();
    }

    // Motor sometimes becomes a toggle, I'm not sure why this should help to counter this issue.
    private void _checkForInput() {
        if(!RobotContainer.controller.a().getAsBoolean())
            shooter.rotateShooter(0);
        if(!RobotContainer.controller.b().getAsBoolean()) 
            shooter.rotateFeeder(0);
    }

    @Override
    public boolean isFinished() {
        DriverStation.reportWarning("isFinished() is being called", false);
        _checkForInput();

        if(m_instruction == ShooterInstruction.Fire)
            return m_finished;
        else return false;
    }
}
