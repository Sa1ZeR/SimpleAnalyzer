## Описание проекта:
Простое SpringBoot приложение с использованием Spring и Kafka для демонстрации общения микросервисов через брокер сообщений

### Используемый стек:
- SpringBoot
- Spring Data Jpa
- Spring Web
- Kafka
- PostgreSQl
- Swagger

## Инструкция по запуску
- Если у вас не установлен postgre или желаете запустить СУБД с созданной бд, то выполните команду docker-compose up для поднятия образа с postgre
- В докере также поднимается кафка (если у вас Linux, то запустите init_for_linux.sh)
- Запустите приложение через вашу среду разработки.

## Информация по endpoint'ам доступна здесь:
producer: http://localhost:3000/swagger-ui/index.html#/
consumer: http://localhost:5000/swagger-ui/index.html#/