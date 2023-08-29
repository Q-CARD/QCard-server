CURRENT_CONTAINER=$(sudo docker ps "name=qcard-server")

if [ -z $CURRENT_CONTAINER ]
then
  echo "> 현재 실행중인 도커 컨테이너가 없습니다."
else
  echo "> kill -15 $CURRENT_CONTAINER"
  sudo docker stop qcard-server
  sudo docker rm qcard-server
fi