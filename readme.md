# Java Developer School Final Project

[![size](https://img.shields.io/github/repo-size/MaxKonduryan/JDS-final-project)](https://github.com/MaxKonduryan/JDS-final-project)
[![lgs](https://img.shields.io/github/languages/top/MaxKonduryan/JDS-final-project)](https://github.com/MaxKonduryan/JDS-final-project)
[![java](https://img.shields.io/badge/java-1.8-blue.svg)](https://github.com/MaxKonduryan/JDS-final-project)
[![opr](https://img.shields.io/github/issues-pr-raw/MaxKonduryan/JDS-final-project?color=red)](https://github.com/MaxKonduryan/JDS-final-project/pulls)
[![cpr](https://img.shields.io/github/issues-pr-closed-raw/MaxKonduryan/JDS-final-project?color=blue)](https://github.com/MaxKonduryan/JDS-final-project/pulls)

Построить модель получения клиентом баланса по карте на банкоматом.\
Классы доменной модели ничего не должны знать о техническом окружении.\
Подробности технического окружения должны быть скрыты за слоями абстракции.

* В качестве системы сборки должен использоваться Maven. 
* В финальной версии приложение должно представлять из себя 2 Spring Boot приложения.
* Одно будет выполнять роль клиента\банкомата, а другое роль сервера.
* Приложение с ролью сервер должно хранить свои данные в базе данных H2.
* Для взаимодействия приложения должны использовать REST.
* Модули должны быть покрыты JUnit тестами.
* Приложение с ролью сервер должно требовать аутентификации по логину и паролю с использованием Spring Security.

В качестве задания на 5+ приложения должны уметь переключаться на взаимодействие через Apache Kafka.