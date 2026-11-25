package org.firstinspires.ftc.teamcode.drive.opmode.autonomous;

// Imported Libraries
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SubSystem;

// Other Libraries
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;



@Autonomous(group = "drive")
public class SampleAuto extends LinearOpMode {




    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);         // Change to SampleTankDrive if using tank drive
        drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        SubSystem subSystem = new SubSystem();
        subSystem.init(hardwareMap, drive);


        if (isStopRequested()) return;
        waitForStart();

        subSystem.startLauncher();
        sleep(2000);



        Trajectory trajectory = drive.trajectoryBuilder(new Pose2d())
                .forward(20)
                .back(20)
                .back(20)
                .build();

        drive.followTrajectory(trajectory);

        subSystem.startLauncher();





    }
}