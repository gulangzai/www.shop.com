@echo off

if exist %~dp0data.manifest (
del %~dp0data.manifest
)

echo CACHE MANIFEST >> "data.manifest"
REM echo # 2016-03-31 v1.0.0 >> "data.manifest"
echo # %date:~0,4%-%date:~5,2%-%date:~8,2% >> "data.manifest"

setlocal enabledelayedexpansion

for /r %1 %%a in (*.*) do (
set "t=%%a"
set "t=!t:%~dp0=!"
set "t=!t:\=/!"
set "t=!t: =%%20!"
if not "!t!"=="DataManifestGenerater.bat" (
if not "!t!"=="data.manifest" (
echo !t! >> "data.manifest"
)))

echo. >> "data.manifest"
echo NETWORK: >> "data.manifest"
echo * >> "data.manifest"

REM PAUSE
