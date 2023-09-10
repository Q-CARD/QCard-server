REPOSITORY=../
cd $REPOSITORY

chmod +x gradlew
./gradlew clean build

docker compose up