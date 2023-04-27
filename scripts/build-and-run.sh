#!/bin/bash

# Define the source and target directories
SRC_DIR="./src"
TARGET_DIR="./bin"

# Compile the Java source files
javac -d $TARGET_DIR $SRC_DIR/*.java

echo "Build complete!"

# Run the application
java -cp $TARGET_DIR Main
