@rem hiptest_publisher_run.cmd
@rem Wrapper script to run hiptest_publisher_run
@echo off

setlocal

set ACTION=%1
set RUN_CONFIG=%2

set ruby_base=c:\Ruby25-x64
set hp_script=%ruby_base%\bin\hiptest-publisher.bat
set hp_configs=daf_hiptest
set ant_script=c:\apache-ant-1.7.0\bin\ant.bat
set pkg_name=whip_pulse_daf_hiptest
set test_run="%VIX_TEST_RUN_ID%"

if not exist %ruby_base%\bin\gem.cmd ( 
    echo The Ruby Gem installer is not installed on this host
    echo Download the installer for Ruby and Gem from https://dl.bintray.com/oneclick/rubyinstaller/rubyinstaller-2.3.3.exe
    goto :end
)

if not exist %hp_script% ( 
    echo hiptest-publisher is not installed on this host
    echo Should be at: %hp_script%
    echo The script will attempt to install:
    call %ruby_base%\bin\gem.cmd install hiptest-publisher
    goto :end
) else if "%ACTION%" equ "gen_features" (
    rem Hiptest update hiptest-publisher more or less weekly.
    rem As the behaviour of the client probably depends on the 
    rem web service at hiptest.com, which is presumably updating
    rem equally frequently, we choose to keep the local
    rem component up to date
    call %ruby_base%\bin\gem.cmd update hiptest-publisher
)

if not exist .\build.pl (
    echo This script is intended to be run from the 
    echo base directory of the package which contains
    echo build.pl
    goto :end
)
rem
rem if "%VIX_WHIP_DEPLOYMENT_ENVIRONMENT%" equ "" (
rem    echo Environment variable VIX_WHIP_DEPLOYMENT_ENVIRONMENT must be set
rem    goto :end
rem ) else if not exist src\deployment\%VIX_WHIP_DEPLOYMENT_ENVIRONMENT% (
rem    echo File %VIX_WHIP_DEPLOYMENT_ENVIRONMENT% must exist in src\deployment
rem    goto :end
rem )

rem if "%ACTION%" neq "publish" (
rem    rmdir /s/q pkg 2> NUL
rem )

for %%c in ( %hp_configs% ) do (
    if "%ACTION%" equ "gen_features" (
        call %hp_script% -c etc\hiptest\%%c.hiptest-publisher.conf --force 
        call jats clobber
        call jats all
    ) else if "%ACTION%" equ "run" (
        call jats clobber
        call jats all
        cd pkg\%pkg_name%
        call %ant_script% -buildfile scripts\teamcity-daf-%RUN_CONFIG%.xml
        start test_results\%RUN_CONFIG%\index.html
    ) else if "%ACTION%" equ "runq" (
        cd pkg\%pkg_name%
        call %ant_script% -buildfile scripts\teamcity-daf-%RUN_CONFIG%.xml
        start test_results\%RUN_CONFIG%\index.html
    ) else if "%ACTION%" equ "publish" (
        call %hp_script% -c etc\hiptest\%%c.hiptest-publisher.conf --push=pkg\%pkg_name%\test_results\%RUN_CONFIG%\junit_for_hiptest.xml
    ) else (
        echo Unexpected action %ACTION%
        goto :usage
    )
)
goto :end

:usage
echo hiptest_publisher_run [action]
echo [action] = gen_features ^| run ^| runq ^| publish
goto :end


:end

endlocal


