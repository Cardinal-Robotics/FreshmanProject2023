// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class DriveTrain extends SubsystemBase {
    private WPI_TalonSRX m_rightMotor = new WPI_TalonSRX(2);
    private WPI_TalonSRX m_leftMotor = new WPI_TalonSRX(6);
    private DifferentialDrive m_diffControl;

    private double m_lastRotation = 0;
    private double m_lastSpeed = 0;
    private double m_lastTurn = 0;

    public DriveTrain() {
        m_rightMotor.setInverted(true);
        m_leftMotor.setInverted(false);

        m_diffControl = new DifferentialDrive(m_leftMotor, m_rightMotor);
        m_diffControl.setExpiration(2);
        m_diffControl.setSafetyEnabled(false);

        //diffControl.curvatureDrive(0, 0, false);
    }

    public void CurveDrive(double speed, double turn) {
        m_diffControl.curvatureDrive(speed, -turn, true);
        
        m_lastSpeed = speed;
        m_lastTurn = turn;
    }

    public void rotate(double rotate) {
        m_rightMotor.set(TalonSRXControlMode.PercentOutput, rotate * -.6);
        m_leftMotor.set(TalonSRXControlMode.PercentOutput, rotate * .6);

        m_lastRotation = rotate;
    }

    @Override
    public void periodic() {
        CommandScheduler.getInstance().setDefaultCommand(RobotContainer.m_driveTrain, RobotContainer.m_curveDrive);
    }

    public DifferentialDrive getDifferentialDrive() {
        return m_diffControl;
    }

    // Breaks
    public void disable() {
        DriverStation.reportWarning("[DISABLING] DriveTrain", false);

        m_diffControl.setSafetyEnabled(false);
        m_rightMotor.setSafetyEnabled(false);
        m_leftMotor.setSafetyEnabled(false);

        CurveDrive(-m_lastSpeed * 1.5, -m_lastTurn * 1.5);
        rotate(-m_lastRotation * 1.5);

        Timer.delay(2);

        CurveDrive(0, 0);
        rotate(0);

        System.exit(0);
    }
}
