package org.firstinspires.ftc.teamcode.drive.opmode.teleop;

// Imported Libraries
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;
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


@TeleOp(group = "drive")
public class Teleop extends LinearOpMode {


    boolean launch = false;
    boolean intake = false;
    boolean intakeReverse = false;
    boolean pass = false;

    double blockerPosition = 1;
    double blockSpeed = 0.1;

    double movePower = 0.1;

    @Override
    public void runOpMode() throws InterruptedException {
        SampleTankDrive drive = new SampleTankDrive(hardwareMap);         // Change to SampleTankDrive if using tank drive
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        SubSystem subSystem = new SubSystem();
        subSystem.init(hardwareMap, drive);


        waitForStart();

        while (!isStopRequested()) {
            if(gamepad1.left_bumper){
                movePower = 0.3;
            } else {
                movePower = 1;
            }
            drive.setWeightedDrivePower(
                    new Pose2d(
                            gamepad1.right_stick_y*movePower,
                            -gamepad1.left_stick_y,
                            gamepad1.left_stick_x*movePower
                    )
            );

            
            if(gamepad2.xWasPressed()||gamepad1.xWasPressed()){
                launch = !launch;
            }

            if (launch) {
                subSystem.startLauncher();
            } else {
                subSystem.stopLauncher();
            }


            if(gamepad2.yWasPressed()||gamepad1.yWasPressed()){
                intake = !intake;
            }

            if(gamepad2.rightBumperWasPressed()||gamepad1.rightBumperWasPressed()){
                intakeReverse = !intakeReverse;
            }

            if (intakeReverse) {
                subSystem.reverseIntake();
            } else if (intake){
                subSystem.startIntake();
            } else {
                subSystem.stopIntake();
            }

            if (gamepad1.b){
                pass = true;

            } else {
                pass = false;
            }


            if (pass){
                subSystem.startPass();
            } else {
                subSystem.stopPass();
            }

            if (gamepad2.right_trigger<1) {
                if(blockerPosition>0.4) {
                    blockerPosition = blockerPosition - (gamepad2.right_trigger * blockSpeed);
                }
            }
            if (gamepad2.left_trigger<1) {
                if(blockerPosition<1) {
                    blockerPosition = blockerPosition + (gamepad2.left_trigger * blockSpeed);
                }
            }
            subSystem.blocker(blockerPosition);
            if (gamepad2.aWasPressed()) {
                blockerPosition = 1;
            }



            // Telemetry
            telemetry.addData("Gamepad 1:", gamepad1.toString());
            telemetry.addData("Gamepad 2:", gamepad2.toString());
            telemetry.update();

            subSystem.Floodlight();

            drive.update();


        }
    }
}

