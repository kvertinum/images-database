# Images-database
> Просто вставьте ссылку на альбом в свой HTML документ и получайте случайное изображение каждый раз, перезагружая страницу

### /album
```Получение случайной картинки из альбома```
> GET /album/{albumName}

### /create
```Создание альбома```
> POST /create application/x-www-form-urlencoded

* album-name: String - Album name
* album-key: String - Album key
* album-key-repeat: String - Repeat album key

### /upload
```Загрузка изображения в альбом```
> POST /upload multipart/form-data

* file: image/jpeg, image/png - File you want to add to the album
* album-name: String - Album name
* album-key: String - Album key
