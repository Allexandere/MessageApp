**Запуск:**<br />

  Запуск осуществляется при помощи docker-compose:<br />
  ```
  git clone https://github.com/Allexandere/MessageApp.git
  cd MessageApp/
  mvn clean install -DskipTests=true
  docker-compose up
  ```
  Если во время сборки возникла ошибка 127 - нужно удалить wait-for-it.sh и скачать его вручную, не используя git

**Требования к данным:**<br />

  Всего есть 3 доступных очереди - `queue1`,`queue2`,`queue3`

**Эндпоинты**

* **/message**

  Отправляет полученное сообщение консьюмеру который записывает его в БД

* **Method:**

  `POST`
  
*  **Data Params**

   **Required:**
   
   `queueName=[string]`
   `messageId=[long]`

   **Optional:**
 
   `message=[string]`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `Sent`
 
* **Error Response:**

  Если один из обязательных параметров указан неверно возвращает ошибку 400.

  * **Code:** 400 <br />
    **Content:** `Id or Queue name can't be null`

* **Sample Call:**

  ![alt text](https://github.com/Allexandere/MessageApp/blob/main/message_post.jpg?raw=true)

 ---
 
* **/messages**

  Отправляет список полученных сообщений консьюмеру который записывает их в БД

* **Method:**

  `POST`
  
*  **Data Params**

   Список сообщений

   **Required:**
   
   `queueName=[string]`
   `messageId=[long]`

   **Optional:**
 
   `message=[string]`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `Successfully sent messages`
 
* **Error Response:**

  Если один из обязательных параметров указан неверно возвращает ошибку 400 и тело сообщений в котором была найдена ошибка.

  * **Code:** 400 <br />
    **Content:** `%message body% is incorrect`

* **Sample Call:**

  ![alt text](https://github.com/Allexandere/MessageApp/blob/main/messages_post.jpg?raw=true)

 ---
 
 * **/messages**

  Получает список сообщений в зависимости от указанного тела фильтра

* **Method:**

  `GET`
  
*  **Data Params**

   **Optional:**
 
   `queueName=[string] по умолчанию ""`
   `message=[string] по умолчанию ""`
   `messageId=[long]`
   `startDate=[date]`
   `finishDate=[date]`
   
   Дата должна быть указана в одном из следующих форматов: "yyyy-MM-dd'T'HH:mm:ss.SSSX", "yyyy-MM-dd'T'HH:mm:ss.SSS", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd"

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `%Array of relevant messages%`
 
* **Sample Call:**

  ![alt text](https://github.com/Allexandere/MessageApp/blob/main/messages_get_1.jpg?raw=true)
  ![alt text](https://github.com/Allexandere/MessageApp/blob/main/messages_get_2.jpg?raw=true) 
