@echo off
echo [INFO] Use maven eclipse plugin download jar and generate eclipse project files.	eclipse:myeclipse-clean eclipse:clean
echo [INFO] Please add "-Declipse.workspace=<path-to-eclipse-workspace>" at end of follow command.	-DdownloadSources=true

cd %~dp0
cd ..
call mvn eclipse:myeclipse -DdownloadSources=false

pause