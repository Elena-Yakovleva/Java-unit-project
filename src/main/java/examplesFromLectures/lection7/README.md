###  Docker и Docker Compose

Docker — программное обеспечение для автоматизации развёртывания и управления приложениями в средах с поддержкой контейнеризации, контейнеризатор приложений. Позволяет «упаковать» приложение со всем своим окружением и зависимостями в контейнер, который может быть развёрнут на любой Linux-системе с поддержкой контрольных групп в ядре, а также предоставляет набор команд для управления этими контейнерами.

**Комманды:**
```
docker -v //версия
docker help //мануал

docker pull name_images //скачать образ программы

docker container create --name name_container name_image //создание контейнера
docker container start name_container //запуск контейнера
docker containet run name_container name_image //одна команда, которая скачивает, создает и запускает контейнер

docker container ls --all //просмотр информации о запущенных контейнерах
docker container images //просмотр листа образов программ
docker container ps //просмотр листа контейнеров

docker container exec //доступ к контейнеру
docker container rm id_number //удаление контейнера по номеру идентификатора
docker container rmi id_number //удаления образа контейнера
docker container restart id_number //перезапуск контейнера
docker container stop id_number //остановка контейнера
docker container kill id_number //уничтожение контейнера

```

**Работа с СУБД**

При работе с СУБД(Системы Управления Базами Данных) необходимо не только создать контейнер, но и указать необходимые переменные окружения, которые необходимы для работы с базой.

```
docker container run -d \    //команде Docker запускает контейнер в фоновом режиме (detached mode).
-e MYSQL_RANDOM_ROOT_PASSWORD yes \
-e MYSQL_DATABASE app \
-e MYSQL_USER app \
-e MYSQL_PASSWORD 9mREsvXDs9Gk89Ef \
-p 3000:3306 \     // привязка порта контейнера к порту хоста Первым всегда пишется порт хоста, а через двоеточие — порт контейнера. часто порты совпадают. Информацию о портам можно найти в тех.документации от разработчиков
-v "$PWD/data":var/lib/mysql  // указание папки, где должны сохранятся логи до : - место на пк, после - место на виртуальной машине
mysql

$ docker container ls
$ docker container stop id //останавливает работу контейнера

```
```docker container exec -it <container_id> sh``` - доступ к информации внутри контейнера, выход из режима ctrl + d

**Подключения jar к базе данных**
* создать файл  ```application.properties```  в папке, где хранится jar файл
```
spring.datasource.url=jdbc:mysql://localhost:3306/db
spring.datasource.username=user
spring.datasource.password=password

// где
mysql — тип БД
localhost — хост БД
3306 — порт
db — имя БД
user — пользователь
password - пароль
```
* после запуска приложения при переходе на страницу  http://localhost:9999//api/cards  открывается список карт

**Docker Compose**

Docker Compose — инструмент, позволяющий запускать мультиконтейнерные приложения

1) Установка:
```
docker pull docker/compose

```
2) Для работы создается файл ```docker-compose.ylm```, в котором прописываются все настройки проекта

Пример ддя MySQL
```
version: '3.9'

services:

  mysql:
    image: mysql:8.0.18
    ports:
      - '3306:3306'
    volumes:
      - ./data:/var/lib/mysql
    environment:
      - MYSQL_RANDOM_ROOT_PASSWORD=yes
      - MYSQL_DATABASE=app
      - PMYSQL_USER=app
      - MYSQL_PASSWORD 9mREsvXDs9Gk89Ef

```
3) Запуск осуществляется с помощью команды ```docker-compose up```, а отключение ```docker-compose down```
4) После запуска контейнера с базой можно запускать jar файл программы

