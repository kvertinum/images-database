# Images-database
> Просто вставьте ссылку на альбом в свой HTML документ и получайте случайное изображение каждый раз, перезагружая страницу

### /album
```Получение случайной картинки из альбома```
> GET /album/{albumName}

### /create
```Создание альбома```
> POST /create application/x-www-form-urlencoded

* __album-name__: String - *Album name*
* __album-key__: String - *Album key*
* __album-key-repeat__: String - *Repeat album key*

### /upload
```Загрузка изображения в альбом```
> POST /upload multipart/form-data

* __file__: image/jpeg, image/png - *File you want to add to the album*
* __album-name__: String - *Album name*
* __album-key__: String - *Album key*
