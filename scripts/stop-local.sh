REPOSITORY=../
cd $REPOSITORY

docker compose down
docker rmi -f $(docker images -q)