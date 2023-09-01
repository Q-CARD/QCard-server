CURRENT_CONTAINER=$(sudo docker ps "name=qcard-server")

if [ -z $CURRENT_CONTAINER ]
then
  echo "> kill -15 $CURRENT_CONTAINER"
    sudo docker stop qcard
    sudo docker rm qcard
else
  echo "> 현재 실행중인 도커 컨테이너가 없습니다."
fi