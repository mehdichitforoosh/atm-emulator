#!/usr/bin/env bash

trap abort ERR

abort()
{
  echo "Failed at line $BASH_LINENO"
  exit 1
}

# Show banner text
BANNER_TEXT=$(cat banner.txt)
echo -e "\e[32m${BANNER_TEXT}\e[0m"

# project name
COMPOSE_PROJECT_NAME="atm-emulator"
CURRENT_DIRECTORY=`pwd`

echo "Clone git repositories for project [${COMPOSE_PROJECT_NAME}] in directory [${CURRENT_DIRECTORY}] ..."

# dominant git repositories urls
GIT_REPOSITORY_URLS=(
    [1]="https://github.com/mehdichitforoosh/atm-emulator.git"
)

# clone git repositories
for url in "${GIT_REPOSITORY_URLS[@]}"; do
    if  git clone "$url" ; then
        echo -e "clone git repository [$url] \e[92mdone\e[0m"
     else
        echo -e "clone git repository [$url] \e[91mfailed\e[0m"
        abort
    fi
done

cd atm-emulator || exit

echo -e "Change current directory to [${pwd}] \e[92mdone\e[0m"

echo "Start docker containers for project [${COMPOSE_PROJECT_NAME}] ..."
docker-compose build
echo -e "Build images \e[92mdone\e[0m"

docker-compose up -d --force-recreate --remove-orphans
echo -e "Run containers \e[92mdone\e[0m"

rm -rf ../atm-emulator/
echo -e "Delete directory \e[92mdone\e[0m"

exit