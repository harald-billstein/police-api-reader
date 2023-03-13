#!/bin/bash
#
bold=$(tput bold)
green=$(tput setaf 2)
blue=$(tput setaf 4)
reset=$(tput sgr0)


cd ..
cd ..
echo -e "${green}***********************************************************"
echo -e "*${blue} ${bold}                 police-api-reader                      ${green}*"
echo -e "***********************************************************${reset}"


if [ -d "police-api-reader" ]
then
    echo "police-api-reader exists."
else
    echo "police-api-reader does not exists, cloning..."
    git clone https://github.com/harald-billstein/police-api-reader.git
fi

cd police-api-reader || exit
git pull
./mvnw clean install
docker build -f ./Dockerfile -t police-api-reader:latest .


cd ..
echo -e "${green}***********************************************************"
echo -e "*${blue} ${bold}                 police-twitter                         ${green}*"
echo -e "***********************************************************${reset}"
if [ -d "police-twitter" ]
then
    echo "police-twitter exists."
else
    echo "police-twitter does not exists, cloning..."
    git clone https://github.com/harald-billstein/police-twitter.git
fi
cd police-twitter || exit
git pull
./mvnw clean install
docker build -f ./Dockerfile -t police-twitter:latest .

#docker-compose -p crime-syndicate -f ../police-api-reader/script/docker-compose.yml up -d

