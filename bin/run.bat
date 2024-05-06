@echo off
cls
echo Compiling Java files...
javac ../src/*.java -d ../bin

if %errorlevel% neq 0 (
    echo Compilation failed.
    exit /b %errorlevel%
)

echo Compilation successful.
echo Running Main class...
cd ../bin
java src/Main
