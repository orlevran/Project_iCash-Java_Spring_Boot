The project is written in Java and uses the Spring Boot framework.
It includes two microservices as required by the assignment.
Both services can be run simultaneously using Docker Compose.
Additionally, a MySQL 8 server is utilized, which was set up and managed through AWS cloud services.

Run the project with Docker-Compose:
1. Install Docker + Docker Compose inside WSL2 (Ubuntu):
  1. a. Install prerequisites:
    * sudo apt update
    * sudo apt install ca-certificates curl gnupg lsb-release -y
  1. b. Add Docker GPG key:
     * sudo mkdir -p /etc/apt/keyrings curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
  1. c. Add Docker repository:
     * echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
  1. d. Install Docker + Docker Compose plugin:
     * sudo apt update
     * sudo apt install docker-ce docker-ce-cli containerd.io docker-compose-plugin -y
  1. e. Start Docker and allow current user:
     * sudo service docker start
     * sudo usermod -aG docker $USER
     * newgrp docker
2. Install Java 17+ and Maven:
   2. a. Install Java 21 (includes Java 17 support):
      * sudo apt install openjdk-21-jdk -y
   2. b. Install Maven:
      * sudo apt install maven -y
3. Prepare the Project:
   3. a. Clone the project:
      * git clone https://github.com/orlevran/Project_iCash.git
      * cd Project_iCash
4. Build Both Microservices:
   4. a. First Microservice:
      * cd ~/Project_iCash/cash_register_service
      * mvn clean package
   4. b. Second Microservice:
      * cd ~/Project_iCash/supermarket_service
      * mvn clean package
5. Run with Docker Compose:
    5. a. From the root project folder:
       * docker compose down
         (Not for the first time)
       * docker compose up --build
6. Test in Postman (Or Insomnia) the following request:
   (Note- Some requests are expected to receive a response with an error message)



curl --location 'http://localhost:8080/purchases/make' \
--header 'Content-Type: application/json' \
--data '{
  "supermarketId": "SMKT001",
  "items": ["milk", "eggs", "cereal"],
  "userId": "123e4567-e89b-12d3-a456-426614174000"
}'


curl --location 'http://localhost:8080/purchases/make' \
--header 'Content-Type: application/json' \
--data '{
  "supermarketId": "SMKT002",
  "items": ["bread", "milk"],
  "userId": null
}'


curl --location 'http://localhost:8080/purchases/make' \
--header 'Content-Type: application/json' \
--data '{
  "items": ["milk", "eggs"],
  "userId": "123e4567-e89b-12d3-a456-426614174000"
}'


curl --location 'http://localhost:8080/purchases/make' \
--header 'Content-Type: application/json' \
--data '{
  "supermarketId": "UNKNOWN123",
  "items": ["milk"],
  "userId": null
}'


curl --location 'http://localhost:8080/purchases/make' \
--header 'Content-Type: application/json' \
--data '{
  "supermarketId": "SMKT003",
  "items": ["milk", "invalid_item"],
  "userId": null
}'


curl --location 'http://localhost:8080/purchases/make' \
--header 'Content-Type: application/json' \
--data '{}'


curl --location 'http://localhost:8080/purchases/make' \
--header 'Content-Type: application/json' \
--data '{
  "supermarketId": "SMKT001",
  "items": ["milk", "eggs", "bread", "cheese", "butter", "water", "juice", "yogurt", "apples", "bananas", "extra_item"],
  "userId": null
}'


curl --location 'http://localhost:8081/supermarket/ping' \
--data ''


curl --location 'http://localhost:8081/supermarket/total-users' \
--data ''


curl --location 'http://localhost:8081/supermarket/loyal-users' \
--data ''


curl --location 'http://localhost:8081/supermarket/top-three-products' \
--data ''