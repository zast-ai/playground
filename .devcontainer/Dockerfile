FROM mcr.microsoft.com/devcontainers/java:8

USER root

# Set up Maven directory and settings
RUN mkdir -p /home/vscode/.m2 && chown vscode:vscode /home/vscode/.m2

# Copy Maven settings
COPY settings.xml /home/vscode/.m2/settings.xml
RUN chown -R vscode:vscode /home/vscode/.m2

# Create workspace directory structure
RUN mkdir -p /workspaces/demo-playground && \
    chown -R vscode:vscode /workspaces

# Switch to vscode user
USER vscode

# Set Java environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

# Set working directory
WORKDIR /workspaces/demo-playground