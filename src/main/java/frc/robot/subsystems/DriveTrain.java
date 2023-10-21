// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.RobotContainer;

public class DriveTrain extends SubsystemBase {
  private TalonSRX rightMotor = new TalonSRX(1);
  private TalonSRX leftMotor = new TalonSRX(0);
  private DifferentialDrive diffControl;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    rightMotor.setInverted(false);
    leftMotor.setInverted(false);

    diffControl = new DifferentialDrive((MotorController)leftMotor, (MotorController)rightMotor);
    diffControl.setExpiration(2);
    diffControl.setSafetyEnabled(true);
    
    diffControl.curvatureDrive(0, 0, false);
  }

  public void rotate(double rotate) {
    rightMotor.set(TalonSRXControlMode.PercentOutput, rotate * -.6);
    leftMotor.set(TalonSRXControlMode.PercentOutput, rotate * .6);
    
  }

  @Override
  public void periodic() {
    CommandScheduler.getInstance().setDefaultCommand(RobotContainer.m_driveTrain, RobotContainer.m_curveDrive);
  }

  public DifferentialDrive getDifferentialDrive() {
    return diffControl;
  }
}
