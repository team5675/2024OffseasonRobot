package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;


public class Elevator extends SubsystemBase {
  public static Elevator instance;
  public static Elevator getinstance(){
    if (instance == null) {
      instance = new Elevator();
    }

    return instance;
  }

  public CANSparkMax elevatorMotor;
  public SparkPIDController PIDcontroller;
  public RelativeEncoder relativeEncoder;

  /** Creates a new Elevator. */
  public Elevator() {
    elevatorMotor = new CANSparkMax(10,MotorType.kBrushless);
    PIDcontroller = elevatorMotor.getPIDController();
    PIDcontroller.setP(0.02);
    PIDcontroller.setI(0);
    PIDcontroller.setD(0);
    relativeEncoder = elevatorMotor.getEncoder();

  }
  public void ElevatorRise(){
    PIDcontroller.setFeedbackDevice(relativeEncoder);
    PIDcontroller.setReference(100,ControlType.kPosition);
  }
  public void ElevatorFall(){
    PIDcontroller.setFeedbackDevice(relativeEncoder);
    PIDcontroller.setReference(0,ControlType.kPosition);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}