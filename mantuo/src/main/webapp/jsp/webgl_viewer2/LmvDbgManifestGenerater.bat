@echo off

if exist %~dp0LmvDbg.manifest (
del %~dp0LmvDbg.manifest
)

echo CACHE MANIFEST >> "LmvDbg.manifest"
REM echo # 2016-03-31 v1.0.0 >> "LmvDbg.manifest"
echo # %date:~0,4%-%date:~5,2%-%date:~8,2% >> "LmvDbg.manifest"

setlocal enabledelayedexpansion

for /r %1 %%a in (*.*) do (
set "t=%%a"
set "t=!t:%~dp0=!"
set "t=!t:\=/!"
set "t=!t: =%%20!"
if not "!t!"=="LmvDbgManifestGenerater.bat" (
if not "!t!"=="LmvDbg.manifest" (
if not "!t!"=="DataManifestGenerater.bat" (
if not "!t!"=="data.html" (
echo !t! >> "LmvDbg.manifest"
)))))

echo. >> "LmvDbg.manifest"
echo NETWORK: >> "LmvDbg.manifest"
echo * >> "LmvDbg.manifest"

REM PAUSE

