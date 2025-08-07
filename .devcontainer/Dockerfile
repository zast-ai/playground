FROM mcr.microsoft.com/devcontainers/java:8

USER root

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get clean

# Verify Maven installation and set up environment
RUN mvn -version

# Set up Maven directory (without custom settings)
RUN mkdir -p /home/vscode/.m2 && chown vscode:vscode /home/vscode/.m2

# Create workspace directory structure
RUN mkdir -p /workspaces/demo-playground && \
    chown -R vscode:vscode /workspaces

# Switch to vscode user
USER vscode

# Set Java and Maven environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV M2_HOME=/usr/share/maven
ENV MAVEN_HOME=/usr/share/maven
ENV PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH

# Set working directory
WORKDIR /workspaces/demo-playground