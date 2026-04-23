#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$ROOT_DIR"

JAR_PATH=""
if ls mysql-connector-j-*.jar >/dev/null 2>&1; then
  JAR_PATH="$(ls -1 mysql-connector-j-*.jar | head -n1)"
elif ls lib/mysql-connector-j-*.jar >/dev/null 2>&1; then
  JAR_PATH="$(ls -1 lib/mysql-connector-j-*.jar | head -n1)"
fi

if [[ -n "$JAR_PATH" ]]; then
  javac -cp ".:${JAR_PATH}" $(find . -name '*.java' -not -path './target/*')
  java -cp ".:${JAR_PATH}" App
  exit 0
fi

DOWNLOAD_VERSION="8.4.0"
DOWNLOAD_JAR="mysql-connector-j-${DOWNLOAD_VERSION}.jar"
DOWNLOAD_URL="https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/${DOWNLOAD_VERSION}/${DOWNLOAD_JAR}"

mkdir -p lib

if command -v curl >/dev/null 2>&1; then
  echo "MySQL connector jar not found. Downloading ${DOWNLOAD_JAR}..."
  if curl -fsSL "$DOWNLOAD_URL" -o "lib/${DOWNLOAD_JAR}"; then
    javac -cp ".:lib/${DOWNLOAD_JAR}" $(find . -name '*.java' -not -path './target/*')
    java -cp ".:lib/${DOWNLOAD_JAR}" App
    exit 0
  fi
fi

if command -v wget >/dev/null 2>&1; then
  echo "MySQL connector jar not found. Downloading ${DOWNLOAD_JAR}..."
  if wget -q "$DOWNLOAD_URL" -O "lib/${DOWNLOAD_JAR}"; then
    javac -cp ".:lib/${DOWNLOAD_JAR}" $(find . -name '*.java' -not -path './target/*')
    java -cp ".:lib/${DOWNLOAD_JAR}" App
    exit 0
  fi
fi

if command -v mvn >/dev/null 2>&1; then
  mvn -q -DskipTests compile exec:java
  exit 0
fi

echo "Unable to auto-download MySQL connector and Maven is not installed."
echo "Option 1: Place mysql-connector-j-<version>.jar in project root or lib/."
echo "Option 2: Install Maven and run ./run.sh again."
exit 1
