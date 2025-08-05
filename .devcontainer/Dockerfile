FROM mcr.microsoft.com/devcontainers/java:8

USER root

RUN apt-get update && apt-get install -y \
    maven \
    git \
    curl \
    wget \
    && rm -rf /var/lib/apt/lists/*

RUN wget -qO - https://packages.microsoft.com/keys/microsoft.asc | gpg --dearmor > /etc/apt/trusted.gpg.d/microsoft.gpg \
    && echo "deb [arch=amd64,arm64,armhf signed-by=/etc/apt/trusted.gpg.d/microsoft.gpg] https://packages.microsoft.com/repos/code stable main" > /etc/apt/sources.list.d/vscode.list

RUN apt-get update && apt-get install -y code \
    && rm -rf /var/lib/apt/lists/*

RUN curl -fsSL https://get.docker.com -o get-docker.sh \
    && sh get-docker.sh \
    && rm get-docker.sh

RUN usermod -aG docker vscode

RUN mkdir -p /home/vscode/.m2 && chown vscode:vscode /home/vscode/.m2

COPY settings.xml /home/vscode/.m2/settings.xml
RUN chown vscode:vscode /home/vscode/.m2/settings.xml

USER vscode