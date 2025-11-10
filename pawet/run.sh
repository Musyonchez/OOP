#!/bin/bash
# PAWET-B Run Script

# Option 1: Run with warnings (shows all messages)
# java -cp "bin:sqlite-jdbc-3.45.0.0.jar:slf4j-api-1.7.36.jar:slf4j-simple-1.7.36.jar" Main

# Option 2: Run without warnings (cleaner output) - RECOMMENDED
java --enable-native-access=ALL-UNNAMED -cp "bin:sqlite-jdbc-3.45.0.0.jar:slf4j-api-1.7.36.jar:slf4j-simple-1.7.36.jar" Main

