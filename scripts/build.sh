#!/bin/bash

# Define the source and target directories
TARGET_DIR="./bin"
SRC_DIR="./src"

javac -d $TARGET_DIR $SRC_DIR/*.java

echo "Build complete!"