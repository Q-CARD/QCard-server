CURRENT_CONTAINER=$(sudo docker ps -f "name=qcard")

if [ -z $CURRENT_CONTAINER ]
then
  echo "> 현재 실행중인 도커 컨테이너가 없습니다."
else
  echo "> kill -15 $CURRENT_CONTAINER"
  sudo docker rm -f qcard
fi