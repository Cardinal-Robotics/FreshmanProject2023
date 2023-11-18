// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.ShooterTrigger.ShooterInstruction;
import frc.robot.commands.ShooterTrigger;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.AutonomousDrive;
import frc.robot.commands.CurveDrive;
import frc.robot.subsystems.Shooter;

import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.auto.AutoBuilder;

/**
 * This class is where the bulk of the robot should be declared, 
 * little robot logic should be here, instead, the subsystems, commands, and trigger mappings should be here.
 */
public class RobotContainer {
    public static DriveTrain m_driveTrain = new DriveTrain();
    public static Shooter m_shooter = new Shooter();

    public static CurveDrive m_curveDrive = new CurveDrive(m_driveTrain);

    public static CommandXboxController controller = new CommandXboxController(0);

    public RobotContainer() {
        NamedCommands.registerCommand("fire", new ShooterTrigger(m_shooter, ShooterInstruction.Fire));

        configureBindings();
    }

    private void configureBindings() {
        controller.a().whileTrue(new ShooterTrigger(m_shooter, ShooterInstruction.A));
        controller.b().whileTrue(new ShooterTrigger(m_shooter, ShooterInstruction.B));
    }

    public Command getAutonomousCommand() {

        return Commands.sequence(
            new AutonomousDrive(m_driveTrain), 
            new ShooterTrigger(m_shooter, ShooterInstruction.Fire)
        );
    }

    public void disable() {
        m_driveTrain.disable();
    }
}
