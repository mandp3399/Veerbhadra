sudo add-apt-repository ppa:linuxuprising/java
sudo apt update
sudo apt install oracle-java17-installer -y

  sudo update-alternatives --config java
export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
export PATH=$JAVA_HOME/bin:$PATH
echo "export JAVA_HOME=$JAVA_HOME" >> ~/.bashrc
echo "export PATH=\$JAVA_HOME/bin:\$PATH" >> ~/.bashrc
source ~/.bashrc
