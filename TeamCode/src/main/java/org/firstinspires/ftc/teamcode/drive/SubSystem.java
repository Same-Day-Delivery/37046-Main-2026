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

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.function.BooleanSupplier;


@Config
public class SubSystem {

    

    // Works with any Road Runner drive (SampleMecanumDrive, SampleTankDrive, ...).
    private Drive drive;

    // Motors Setup

    private DcMotorEx Intake, Launcher;

    // Servos Setup

    private CRServo Pass;
    private CRServo funnelLeft;
    private CRServo funnelRight;
    private Servo Flood, Blocker;

    // Variables
    boolean intakeOn = false;
    boolean intakeReverse = false;
    boolean launcherOn = false;
    boolean passOn = false;

    double launcherTargetSpeed = 1250;

    public void init(HardwareMap hardwareMap, Drive drive) {
        this.drive = drive;

        Intake = hardwareMap.get(DcMotorEx.class, "Intake");
        Launcher = hardwareMap.get(DcMotorEx.class, "launcher");

        Intake.setDirection(DcMotorEx.Direction.FORWARD);
        Launcher.setDirection(DcMotorEx.Direction.FORWARD);

        Intake.setPower(0.01);
        Launcher.setPower(0.01);

        Pass = hardwareMap.get(CRServo.class, "Pass");
        funnelLeft = hardwareMap.get(CRServo.class, "funnelLeft");
        funnelRight = hardwareMap.get(CRServo.class, "funnelRight");
        funnelLeft.setDirection(CRServo.Direction.REVERSE);
        funnelRight.setDirection(CRServo.Direction.FORWARD);
        Pass.setDirection(CRServo.Direction.REVERSE);

        Flood = hardwareMap.get(Servo.class, "Flood");
        Flood.setPosition(0);

        Blocker = hardwareMap.get(Servo.class, "Block");

    }

    // ---------------------------------------------------------------
    // Floodlight

    private final double idleColour = 1.0;

    // set up our states
    Map<BooleanSupplier, Double> lightColours = new LinkedHashMap<>();
    {
        lightColours.put(() -> intakeReverse, 0.3);
        lightColours.put(() -> launcherOn, 0.61);
        lightColours.put(() -> passOn, 0.38);
        lightColours.put(() -> intakeOn, 0.50);
    }

    public void Floodlight() {
        double position = idleColour;

        for (Map.Entry<BooleanSupplier, Double> entry: lightColours.entrySet()) {
            if (entry.getKey().getAsBoolean()) {
                position = entry.getValue();
                // stop at the highest possible state (so we hopefully get the best fit state)
                break;
            }
        }

        Flood.setPosition(position);
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
        intakeReverse = !intakeReverse;
        Intake.setPower(-1.0);
    }

    // Launcher

    public void startLauncher() {
        Launcher.setVelocity(launcherTargetSpeed);
        launcherOn = true;
    }

    public void stopLauncher() {
        Launcher.setVelocity(0);
        launcherOn = false;
    }

    public void setLaunchSpeed(double speed) {
        launcherTargetSpeed = speed;

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

    public void blocker(double Position) {
        Blocker.setPosition(Position);
    }

    public void blockerDown() {
        Blocker.setPosition(0);
    }



 


}
