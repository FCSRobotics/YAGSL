// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase
{

  private final CANSparkMax bottomMotor;
  private final CANSparkMax topMotor;
  private final DoubleSolenoid solenoid;
  private final boolean reversed;
  private boolean in;

  public IntakeSubsystem(int topMotorId, 
                int bottomMotorId, 
                int extendChannel, 
                int retractChannel,
                boolean reverseSolenoid) {
    in = true;
    bottomMotor = new CANSparkMax(bottomMotorId,MotorType.kBrushless);
    topMotor = new CANSparkMax(topMotorId, MotorType.kBrushless);
    solenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, extendChannel, retractChannel);
    reversed = reverseSolenoid;
  }

  // currently using percentage voltage might be better to change it to explicit speed
  public void setTopMotorSpeed(double speed) {
    topMotor.set(speed);
  }

  public void setBottomMotorSpeed(double speed) {
    bottomMotor.set(speed);
  }

  public void extendOut() {
    in = false;
    solenoid.set(reversed ? kReverse : kForward);
  }

  public void pullIn() {
    in = true;
    solenoid.set(reversed ? kForward : kReverse );
  }

  public void togglePosition() {
    if (in) {
      extendOut();
    } else {
      pullIn();
    }
  }
}
