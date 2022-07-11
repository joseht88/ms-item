#!/bin/bash -ex
echo "Deploying app.jar to coker folder"
packageName=`ls target/*.jar`
versionid=`echo $packageName | awk -F "-" '{print $2}'`
versionName=`echo $packageName | awk -F "-" '{print $3}' | awk -F "." '{print $1}'`
version=`echo $versionid-$versionName`
dockerImageName=ms-item
dockerpid=`docker ps -a | grep $dockerImageName | grep "Up" | awk -F " " '{print $1}'`
if [[ $dockerpid != "" ]];then
      docker stop $dockerpid
      docker rm $dockerpid
fi
docker build -t $dockerImageName:v1 .
docker run -d -p 8002:8002 --name $dockerImageName --network backend $dockerImageName:v1
dockerImageId=`docker images | grep $dockerImageName | grep v1 | awk -F " " '{print $3}'`
docker tag $dockerImageId $dockerImageName:v1
docker rmi $(docker images -f "dangling=true" -q)