package org.firstinspires.ftc.teamcode.drive.opmode.autonomous;

// Imported Libraries

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;
import org.firstinspires.ftc.teamcode.drive.SubSystem;

// Other Libraries


@Autonomous(group = "drive")
public class AutoShootFirst extends LinearOpMode {




    @Override
    public void runOpMode() throws InterruptedException {
        SampleTankDrive drive = new SampleTankDrive(hardwareMap);         // Change to SampleTankDrive if using tank drive
        drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        SubSystem subSystem = new SubSystem();
        subSystem.init(hardwareMap, drive);



        if (isStopRequested()) return;
        waitForStart();

        subSystem.startLauncher();

        drive.turn(Math.toRadians(270));
        Trajectory trajectory = drive.trajectoryBuilder(new Pose2d())
                .forward(20)
                .build();

        drive.followTrajectory(trajectory);
        drive.turn(Math.toRadians(0));

        Trajectory trajectory2 = drive.trajectoryBuilder(new Pose2d())
                .forward(70)
                .build();

        drive.followTrajectory(trajectory2);
        drive.turn(Math.toRadians(270));

        Trajectory trajectory3 = drive.trajectoryBuilder(new Pose2d())
                .forward(20)
                .build();

        drive.followTrajectory(trajectory3);

    }
}