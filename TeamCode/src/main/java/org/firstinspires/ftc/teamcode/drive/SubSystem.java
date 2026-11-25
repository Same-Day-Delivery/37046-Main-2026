package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.drive.Drive;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;



@Config
public class SubSystem {

    

    // Works with any Road Runner drive (SampleMecanumDrive, SampleTankDrive, ...).
    private Drive drive;

    // Motors Setup

    private DcMotorEx motor1, motor2;

    // Servos Setup

    private CRServo Pass;
    private CRServo funnelLeft;
    private CRServo funnelRight;
    private Servo Flood;

    // Variables
    boolean intakeOn = false;
    boolean intakeReverse = false;
    boolean launcherOn = false;
    boolean passOn = false;

    public void init(HardwareMap hardwareMap, Drive drive) {
        this.drive = drive;

        Intake = hardwareMap.get(DcMotorEx.class, "Intake");
        Launcher = hardwareMap.get(DcMotorEx.class, "launcher");

        Intake.setDirection(DcMotorEx.Direction.FORWARD);
        Launcher.setDirection(DcMotorEx.Direction.FORWARD);

        Intake.setPower(0.01);
        Launcher.setPower(0.01);

        Pass = hardwareMap.get(Servo.class, "Pass");
        funnelLeft = hardwareMap.get(CRServo.class, "funnelLeft");
        funnelRight = hardwareMap.get(CRServo.class, "funnelRight");
        funnelLeft.setDirection(CRServo.Direction.FORWARD);
        funnelRight.setDirection(CRServo.Direction.REVERSE);
        Pass.setDirection(Servo.Direction.FORWARD);

        Flood = hardwareMap.get(Servo.class, "Flood");
        Flood.setPosition(0);

    }

    // ---------------------------------------------------------------
    // Floodlight

    public void FloodLight(){
        if(intakeReverse){
            Flood.setPosition(0.28);
        }
        else if(intakeOn&&!launcherOn&&!passOn){
            Flood.setPosition(0.72);
        }
        else{
            Flood.setPosition(0.5);
        }
    }


    // ---------------------------------------------------------------
    // Motor Systems


    // Intake

    public void startIntake() {
        Intake.setPower(1.0);
        funnelLeft.setPower(1.0);
        funnelRight.setPower(1.0);
        intakeOn = true;
    }

    public void stopIntake() {
        Intake.setPower(0.01);
        funnelLeft.setPower(0);
        funnelRight.setPower(0);
        intakeOn = false;
    }

    public void reverseIntake() {
        intakeReverse = true;   
        Intake.setPower(-1.0);
    }

    // Launcher

    public void startLauncher() {
        Launcher.setPower(1.0);
        launcherOn = true;
    }

    public void stopLauncher() {
        Launcher.setPower(0.01);
        launcherOn = false;
    }

    // Passthrough

    public void startPass() {
        Pass.setPower(1.0);
        passOn = true;
    }

    public void stopPass() {
        Pass.setPower(0.01);
        passOn = false;
    }



 


}
