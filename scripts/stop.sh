CURRENT_CONTAINER=$(sudo docker ps)

if [ -z $CURRENT_CONTAINER ]
then
  echo "> kill $CURRENT_CONTAINER"
    sudo docker down
else
  echo "> 현재 실행중인 도커 컨테이너가 없습니다."
fi

sudo docker rm -f $(sudo docker ps -aq)
sudo docker rmi -f $(sudo docker images -q)