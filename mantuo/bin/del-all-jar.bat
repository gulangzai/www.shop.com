@echo off
echo [INFO] 删除[\src\main\webapp\WEB-INF\lib]下面的所有jar包.

cd %~dp0
cd ..
:: Send the length of the variable %MyVar%
:: to the variable %length%
@set MyVar=%cd%\src\main\webapp\WEB-INF\lib

dir %MyVar%

del /q /s %MyVar%\*.*

echo [INFO] 删除成功！

pause
