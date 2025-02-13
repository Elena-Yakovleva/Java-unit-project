### Задача №1: CashBackHacker
#### Введение
Вы участвуете в проекте CashBackHacker — это небольшой сервис, который сообщает пользователю о том, сколько ему нужно докупить в рамках конкретной траты, чтобы получить максимальное количество кешбека.

Подробнее: кешбек начисляется за каждую потраченную полную тысячу рублей, поэтому, если вы покупаете что-то на 900 рублей, сервис должен посоветовать вам докупить ещё чего-нибудь на 100 рублей.

Код сервиса выглядит следующим образом:
````
package ru.netology.service;

public class CashbackHackService {
private final int boundary = 1000;

    public int remain(int amount) {
        return boundary - amount % boundary;
    }
}

````
Вам нужно создать проект на базе Gradle, как на лекции, не добавляя в него внешних зависимостей.

Выложите полученный проект на GitHub. Не забудьте о файле .gitignore. Подключите GitHub Actions. Можете воспользоваться примером файла для конфигурации gradle-сборки в Github Actions.

##### Легенда
Поскольку вы уже умеете работать с JUnit5, вам поручили провести небольшое исследование, в рамках которого попробовать использовать TestNG и JUnit4 — тот самый пресловутый кейс — попробуем что-то сделать, а потом уже будем принимать решения.

#### Часть 1. TestNG
Описание
TestNG сравнительно неплохо документирован.

На этом уровне, с точки зрения пользователя, почти всё, что поменяется, — мы подключим другую библиотеку и будем использовать аннотации из неё и asserts.
[Аннотации](https://testng.org/#_annotations)

[Asserts](https://testng.org/#_success_failure_and_assert)
#### Что нужно сделать
Сделайте ветку testng, в которой:

1. Добавьте в зависимости TestNG:
````
dependencies {
   testImplementation 'org.testng:testng:7.1.0'

}

test {
   useTestNG()
}
````
2. Напишите простые автотесты без параметризации на основании материала следующего раздела.

#### Особенности
На этом уровне для нас поменяется всего три вещи:

* Аннотация @Test должна иметь Fully Qualified Name org.testng.annotations.Test.
* Asserts расположены в классе org.testng.Assert. Обратите внимание: в TestNG принято actual, expected вместо expected, actual.
* Класс и тестовые методы должны иметь модификатор доступа public. Именно поэтому мы вам рекомендовали прописывать модификаторы в тестовых классах.

#### Часть 2. JUnit4
##### Описание

JUnit4, по сравнению с JUnit5, практически не документирован, поэтому всё, что нам доступно, — это Javadoc и FAQ.

На этом уровне, с точки зрения пользователя, почти всё, что поменяется, — мы подключим другую библиотеку и будем использовать аннотации из неё и asserts.
Аннотации
![image](https://github.com/netology-code/aqa-homeworks/raw/master/basics/pic/junit4-annotations.png)
Asserts
![](https://github.com/netology-code/aqa-homeworks/raw/master/basics/pic/junit4-asserts.png)
##### Что нужно сделать
Сделайте ветку junit4, в которой:

1. Добавьте в зависимости JUnit:
````
dependencies {
   testImplementation 'junit:junit:4.13'
}

test {
   useJUnit()
}
````
2. Напишите простые автотесты без параметризации.

#### Особенности
На этом уровне для нас поменяется всего три вещи:

* Аннотация @Test должна иметь Fully Qualified Name org.junit.Test.
* Asserts расположены в классе org.junit.Assert.
* Класс и тестовые методы должны иметь модификатор доступа public. Именно поэтому мы вам рекомендовали прописывать модификаторы в тестовых классах.

##### Об ошибках
В сервисе точно есть ошибка, поэтому один из ваших автотестов должен падать. На обнаруженный дефект должен быть оформлен репорт по установленным правилам
````
Если пользователь купил ровно на 1000 рублей, то приложение не должно ему говорить, что нужно купить ещё на 1000.
````

### Задача №2: JUnit5 и legacy (необязательно)
#### Легенда
Автотесты — это тоже код, и он подвержен тем же проблемам, что и обычный код.

Довольно часто встречается ситуация, когда в вашем проекте большое наследие (legacy) кода автотестов, например, на JUnit4.

Но новые тесты хочется писать, используя JUnit5. Что же делать в этом случае?

#### Описание
JUnit5 представляет из себя комплексный проект, состоящий из трёх частей: JUnit Platform + JUnit Jupiter + JUnit Vintage.
![](https://github.com/netology-code/aqa-homeworks/raw/master/basics/pic/architecture.png)


До этого мы достаточно вольно использовали названия JUnit5 и JUnit Jupiter, фактически как синонимы, но начиная с этого ДЗ, мы должны их различать.

* JUnit Platform отвечает за запуск тестовых фреймворков на JVM и определяет интерфейс TestEngine. Это интерфейс, определяющий движок для поиска и запуска тестов.
* JUnit Jupiter определяет API и реализацию TestEngine для запуска тестов, использующих данное API, junit-jupiter-engine — уже готовая реализация JupiterTestEngine.
* JUnit Vintage предоставляет TestEngine для запуска тестов, использующих JUnit4 и JUnit3.

#### Что нужно сделать
Из ветки junit4 создайте ветку junit4-platform, в которой:

1. Добавьте в зависимости JUnit Vintage:
````
dependencies {
   testImplementation 'junit:junit:4.13'
   testRuntimeOnly 'org.junit.vintage:junit-vintage-engine:5.6.2'
}

test {
   useJUnitPlatform()
}
````
2. Удостоверьтесь, что ваши тесты запускаются.

3. Добавьте в зависимости JUnit Jupiter (по факту — замените код из п.1):
````
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.6.1'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.6.1'

    testImplementation 'junit:junit:4.13'
    testRuntimeOnly 'org.junit.vintage:junit-vintage-engine:5.6.2'
}

test {
    useJUnitPlatform()
}
````
4. Напишите те же тесты, но с использованием API JUnit Jupiter.

5. Удостоверьтесь, что запускаются и тесты JUnit4, и тесты JUnit Jupiter.

6. Соберите отчёты и запакуйте их в zip-архив. Напоминаем, они должны выглядеть примерно так:
![](https://github.com/netology-code/aqa-homeworks/raw/master/basics/pic/report.png)
7. Создайте в проекте issue, к которому приложите отчёты. В личном кабинете вам нужно будет отправить ссылку на тот issue, что вы создали для демонстрационного проекта: [netology-code/aqa-hw-sample#1](https://github.com/netology-code/aqa-hw-sample/issues/1).