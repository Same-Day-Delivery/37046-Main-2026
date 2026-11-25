package org.firstinspires.ftc.teamcode.drive.opmode.teleop;

// Imported Libraries
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;
import org.firstinspires.ftc.teamcode.drive.StandardTrackingWheelLocalizer;
import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SubSystem;

// Other Libraries
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;




// Global Variables

boolean launch = false;
boolean intake = false;
boolean intakeReverse = false;
boolean pass = false;





@TeleOp(group = "drive")
public class SampleTeleop extends LinearOpMode {





    @Override
    public void runOpMode() throws InterruptedException {
        SampleTankDrive drive = new SampleTankDrive(hardwareMap);         // Change to SampleTankDrive if using tank drive
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        SubSystem subSystem = new SubSystem();
        subSystem.init(hardwareMap, drive);


        waitForStart();

        while (!isStopRequested()) {
            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x,
                            -gamepad1.right_stick_x
                    )
            );

            drive.update();


        }

        if(gamepad2.xWasPressed() ){
            launch = !launch;
        }

        if (launch) {
            subSystem.startLauncher();
        } else {
            subSystem.stopLauncher();
        }

        if(gamepad2.yWasPressed() ){
            intake = !intake;
        }

        if(gamepad2.aWasPressed() ){
            intakeReverse = !intakeReverse;
        }

        if (intakeReverse) {
            subSystem.reverseIntake();
        } else if (intake){
            subSystem.startIntake();
        } else {
            subSystem.stopIntake();
        }

        if (gamepad2.bWasPressed() ){
            pass = !pass;

        }


        if (pass){
            subSystem.startPass();
        } else {
            subSystem.stopPass();
        }
        // Telemetry

        telemetry.addData("x", poseEstimate.getX());
        telemetry.addData("y", poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());
        telemetry.addData("Shoot Queue:", pass);
        telemetry.addData("Inputs", gamepad1.toString());
        telemetry.update();

    }
}

