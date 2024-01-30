# Практическая задача 1.1.4 Java pre-project

### Описание задачи:

Необходимо ознакомиться с заготовкой и доработать приложение, которое взаимодействует с базой оперируя пользователем ( класс User ) и проверить свои методы заранее написанными JUnit тестами. По итогу все тесты должны быть пройдены. Разрешается посмотреть реализацию тестов.

Для запуска теста необходимо найти класс в папке test ( показано в предыдущей лекции ) и при нажатии на него правой кнопкой мыши запустить, выбрав **Run** "Имя класса"

Класс **UserHibernateDaoImpl** в рамках этой задачи не затрагивается (остаётся пустой)

**User** представляет собой сущность с полями:

* Long id 
* String name 
* String lastName 
* Byte age

Архитектура приложения создана с опорой на паттерн проектирования _MVC_ ( частично, у нас не WEB приложение)
