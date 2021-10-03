#!/usr/bin/env bash

trap abort ERR

abort() {
  echo "Failed at line $BASH_LINENO"
  exit 1
}

# Show banner text
BANNER_TEXT=$(cat banner.txt)
echo -e "\e[32m${BANNER_TEXT}\e[0m"

# project name
COMPOSE_PROJECT_NAME="atm-emulator"

cd atm-emulator || exit
CURRENT_DIRECTORY=$(pwd)

echo -e "Change current directory to [${CURRENT_DIRECTORY}] \e[92mdone\e[0m"

echo "Start docker containers for project [${COMPOSE_PROJECT_NAME}] ..."

docker-compose build
echo -e "Build images \e[92mdone\e[0m"

docker-compose up -d --force-recreate --remove-orphans
echo -e "Run containers \e[92mdone\e[0m"

rm -rf ../atm-emulator/
echo -e "Delete directory \e[92mdone\e[0m"

exit
