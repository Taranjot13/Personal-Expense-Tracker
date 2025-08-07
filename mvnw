#!/bin/sh
# Maven Wrapper Script

set -e

MAVEN_PROJECTBASEDIR=${MAVEN_BASEDIR:-"$BASE_DIR"}
MAVEN_OPTS="$(concat_lines "$MAVEN_PROJECTBASEDIR/.mvn/jvm.config") $MAVEN_OPTS"

# OS specific support
darwin=false
case "`uname`" in
  Darwin*) darwin=true ;;
esac

MAVEN_WRAPPER_VERSION="3.9.3"
MAVEN_USER_HOME="${user.home}/.m2"

exec java $MAVEN_OPTS \
  -classpath "$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar" \
  "-Dmaven.home=${M2_HOME}" \
  "-Dmaven.multiModuleProjectDirectory=${MAVEN_PROJECTBASEDIR}" \
  org.apache.maven.wrapper.MavenWrapperMain "$@"
