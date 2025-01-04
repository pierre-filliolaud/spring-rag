# Spring RAG

## Build docker image
```shell
docker build -t my-springboot-app .
```


## Chat with my Assistant
```shell
curl 'http://localhost:8080/assistants/chat' --data-raw '"Hi, my name is Pierre"'
curl 'http://localhost:8080/assistants/chat' --data-raw '"What is the name of my school?"'
```