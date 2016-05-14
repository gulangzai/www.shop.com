@echo off

if exist %~dp0viewer3d.manifest (
del %~dp0viewer3d.manifest
)

echo CACHE MANIFEST >> "viewer3d.manifest"
REM echo # 2016-03-31 v1.0.0 >> "viewer3d.manifest"
echo # %date:~0,4%-%date:~5,2%-%date:~8,2% >> "viewer3d.manifest"

setlocal enabledelayedexpansion

for /r %1 %%a in (*.*) do (
set "t=%%a"
set "t=!t:%~dp0=!"
set "t=!t:\=/!"
set "t=!t: =%%20!"
if not "!t!"=="Viewer3dManifestGenerater.bat" (
if not "!t!"=="viewer3d.manifest" (
if not "!t!"=="DataManifestGenerater.bat" (
if not "!t!"=="data.html" (
echo !t! >> "viewer3d.manifest"
)))))

echo. >> "viewer3d.manifest"
echo NETWORK: >> "viewer3d.manifest"
echo * >> "viewer3d.manifest"

REM PAUSE

