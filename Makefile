build:
	mvn clean package
	docker-compose build

up:
	docker-compose up -d

down:
	docker-compose down