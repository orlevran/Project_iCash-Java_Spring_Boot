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
   (Note- Some requests are expected to receive an error)


 {
	"info": {
		"_postman_id": "3c5ab7fb-ca92-4673-803e-40734af4b79f",
		"name": "SystemProject_iCash",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "20476221"
	},
	"item": [
		{
			"name": "cash_register_service",
			"item": [
				{
					"name": "1. Valid Request (With User ID)",
					"request": {
						"auth": {
							"type": "noauth"
						},
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"supermarketId\": \"SMKT001\",\r\n  \"items\": [\"milk\", \"eggs\"],\r\n  \"userId\": \"123e4567-e89b-12d3-a456-426614174000\"\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/purchases/make",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"purchases",
								"make"
							]
						}
					},
					"response": []
				},
				{
					"name": "2. Valid Request (Without User ID)",
					"request": {
						"auth": {
							"type": "noauth"
						},
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"supermarketId\": \"SMKT002\",\r\n  \"items\": [\"bread\", \"milk\"],\r\n  \"userId\": null\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/purchases/make",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"purchases",
								"make"
							]
						}
					},
					"response": []
				},
				{
					"name": "3. Invalid Request - Missing Supermarket ID",
					"request": {
						"auth": {
							"type": "noauth"
						},
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"items\": [\"milk\", \"eggs\"],\r\n  \"userId\": \"123e4567-e89b-12d3-a456-426614174000\"\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/purchases/make",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"purchases",
								"make"
							]
						}
					},
					"response": []
				},
				{
					"name": "4. Invalid Request - Invalid Supermarket ID",
					"request": {
						"auth": {
							"type": "noauth"
						},
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"supermarketId\": \"UNKNOWN123\",\r\n  \"items\": [\"milk\"],\r\n  \"userId\": null\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/purchases/make",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"purchases",
								"make"
							]
						}
					},
					"response": []
				},
				{
					"name": "5. Exception - Unknown Item in Product Cache",
					"request": {
						"auth": {
							"type": "noauth"
						},
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"supermarketId\": \"SMKT003\",\r\n  \"items\": [\"milk\", \"invalid_item\"],\r\n  \"userId\": null\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/purchases/make",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"purchases",
								"make"
							]
						}
					},
					"response": []
				},
				{
					"name": "6. Error - Empty Request",
					"request": {
						"auth": {
							"type": "noauth"
						},
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/purchases/make",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"purchases",
								"make"
							]
						}
					},
					"response": []
				},
				{
					"name": "7. Edge Case - Too Many Items",
					"request": {
						"auth": {
							"type": "noauth"
						},
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"supermarketId\": \"SMKT001\",\r\n  \"items\": [\"milk\", \"eggs\", \"bread\", \"cheese\", \"butter\", \"water\", \"juice\", \"yogurt\", \"apples\", \"bananas\", \"extra_item\"],\r\n  \"userId\": null\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8080/purchases/make",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"purchases",
								"make"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "supermarket_service",
			"item": [
				{
					"name": "1. Number of distinct users",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8081/supermarket/total-users",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"supermarket",
								"total-users"
							]
						}
					},
					"response": []
				},
				{
					"name": "2. List of loyal customers",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8081/supermarket/loyal-users",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"supermarket",
								"loyal-users"
							]
						}
					},
					"response": []
				},
				{
					"name": "3. Top three best seller products",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8081/supermarket/top-three-products",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"supermarket",
								"top-three-products"
							]
						}
					},
					"response": []
				},
				{
					"name": "0. Ping",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8081/supermarket/ping",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"supermarket",
								"ping"
							]
						}
					},
					"response": []
				}
			]
		}
	]
}  
