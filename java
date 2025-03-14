#!/bin/bash

# Step 1: Remove previous Java versions
echo "Removing old Java versions..."
sudo apt remove --purge -y openjdk* && sudo apt autoremove -y
echo "Old Java versions removed."

# Step 2: Install OpenJDK 21
echo "Installing Java SE 21..."
sudo apt update
sudo apt install -y openjdk-21-jdk
echo "Java SE 21 installed successfully."

# Step 3: Find Java 21 installation path
JAVA_PATH=$(update-alternatives --query java | grep -oP '(?<=Value: ).*java-21-openjdk-amd64' | head -1)

if [[ -z "$JAVA_PATH" ]]; then
    echo "Error: Java installation path not found!"
    exit 1
fi

echo "Java path detected: $JAVA_PATH"

# Step 4: Set JAVA_HOME in .bashrc for current user
echo "Setting JAVA_HOME for current user..."
echo "export JAVA_HOME=$JAVA_PATH" >> ~/.bashrc
echo "export PATH=\$JAVA_HOME/bin:\$PATH" >> ~/.bashrc
source ~/.bashrc
echo "JAVA_HOME set for current user."

# Step 5: Set JAVA_HOME in /etc/environment for all users
echo "Setting JAVA_HOME globally..."
echo "JAVA_HOME=\"$JAVA_PATH\"" | sudo tee -a /etc/environment
echo "PATH=\"$JAVA_PATH/bin:\$PATH\"" | sudo tee -a /etc/environment
source /etc/environment
echo "JAVA_HOME set globally."

# Step 6: Verify installation
echo "Verifying Java version..."
java -version
echo "JAVA_HOME: $JAVA_HOME"

echo "Java SE 21 installation and configuration completed successfully! ✅"
