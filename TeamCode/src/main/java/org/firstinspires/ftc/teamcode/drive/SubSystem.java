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

    private DcMotorEx motor1, motor2, motor3, motor4;
    private double motor1_RPM, motor2_RPM, motor3_RPM, motor4_RPM;

    // Servos Setup

    private CRServo Pass;
    private CRServo funnelLeft;
    private CRServo funnelRight;

    public void init(HardwareMap hardwareMap, Drive drive) {
        this.drive = drive;

        Intake = hardwareMap.get(DcMotorEx.class, "intake");
        Launcher = hardwareMap.get(DcMotorEx.class, "shoot");

        Intake.setDirection(DcMotorEx.Direction.FORWARD);
        Launcher.setDirection(DcMotorEx.Direction.FORWARD);

        Intake_RPM = 0;
        Launcher_RPM = 0;

        Intake.setPower(0.01);
        Launcher.setPower(0.01);

        Pass = hardwareMap.get(Servo.class, "Pass");
        funnelLeft = hardwareMap.get(CRServo.class, "funnelLeft");
        funnelRight = hardwareMap.get(CRServo.class, "funnelRight");
        funnelLeft.setDirection(CRServo.Direction.FORWARD);
        funnelRight.setDirection(CRServo.Direction.REVERSE);
        Pass.setDirection(Servo.Direction.FORWARD);

    }



    // ---------------------------------------------------------------
    // Motor Systems


    // Intake

    public void startIntake() {
        Intake.setPower(1.0);
        funnelLeft.setPower(1.0);
        funnelRight.setPower(1.0);
    }

    public void stopIntake() {
        Intake.setPower(0.01);
        funnelLeft.setPower(0);
        funnelRight.setPower(0);
    }

    // Launcher

    public void startLauncher() {
        Launcher.setPower(1.0);
    }

    public void stopLauncher() {
        Launcher.setPower(0.01);
    }

    // Passthrough

    public void startPass() {
        Pass.setPower(1.0);
    }

    public void stopPass() {
        Pass.setPower(0.01);
    }

 


}
