@echo off
echo ======================================================
echo == Executing Cucumber Tests via Maven Failsafe Plugin ==
echo ======================================================

REM Clean previous builds, compile, and run the integration tests using the 'verify' phase.
REM The Failsafe plugin is executed during the 'verify' phase, which runs TestRunner.java.
call mvn clean verify

REM Check the exit code of the Maven command
if %errorlevel% equ 0 (
    echo.
    echo =======================================================
    echo == Test Execution Complete. Status: SUCCESS          ==
    echo == Report available at: target\cucumber-reports\index.html
    echo =======================================================
) else (
    echo.
    echo =======================================================
    echo == Test Execution Complete. Status: FAILED           ==
    echo == Check the console output for errors.
    echo =======================================================
)

REM Pause the script to keep the window open after execution
pause