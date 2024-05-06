echo Compiling Java files...
javac ../src/*.java -d ../bin

if [ $? -ne 0 ]; then
    echo Compilation failed.
    exit 1
fi

echo Compilation successful.
echo Running Main class...
cd ../bin
java src/Main
