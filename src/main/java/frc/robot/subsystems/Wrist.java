package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Wrist extends SubsystemBase {
    public static Wrist instance;
    public static Wrist getInstance(){
        if (instance == null){
            instance = new Wrist();
        }

        return instance;
    }
    
    public CANSparkMax wristMotor;
    public AbsoluteEncoder wristEncoder;
    public SparkPIDController PIDcontroller;

    public Wrist(){
        wristMotor = new CANSparkMax(11, MotorType.kBrushless);

        PIDcontroller = wristMotor.getPIDController();
        PIDcontroller.setP(Constants.WristConstants.P);
        PIDcontroller.setI(Constants.WristConstants.I);
        PIDcontroller.setD(Constants.WristConstants.D);

        wristEncoder = wristMotor.getAbsoluteEncoder();
    }

    public void wristToHardStop(){
        PIDcontroller.setFeedbackDevice(wristEncoder);
        PIDcontroller.setReference(55,ControlType.kPosition);
    }

    public void wristToAmp(){
        PIDcontroller.setFeedbackDevice(wristEncoder);
        PIDcontroller.setReference(135,ControlType.kPosition);
    }

    public void wristToAngle(double value) {
        PIDcontroller.setFeedbackDevice(wristEncoder);
        PIDcontroller.setReference(value, ControlType.kPosition);
    }

 /**    public void takeTheShot() {
        PIDcontroller.setFeedbackDevice(wristEncoder);
        PIDcontroller.setReference(getShooterAngle(), ControlType.kPosition);
    } */
}
