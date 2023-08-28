REPOSITORY=/home/ubuntu/snapspot
cd $REPOSITORY

echo "> 도커 이미지 빌드"
sudo docker build -t qcard-image .

echo "> 도커 컨테이너 올리기"
sudo docker run -d docker run -d --name qcard -p 80:8080 qcard-image