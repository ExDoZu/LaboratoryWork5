# Лабораторная работа №5

### Для сборки и запуска проекта нужно выполнить следующие команды:

```shell
mvn package
```

```shell
java -jar .\target\LaboratoryWork5-1.0-jar-with-dependencies.jar <filePath>
```

###### \<filePath> - нужно указать путь к файлу с данными.

### Пример корректного файла с данными:

`data.json`

```json
{
  "organizations": [
    {
      "postalAddress": null,
      "coordinates": {
        "x": 923,
        "y": 4.0
      },
      "name": "name2",
      "annualTurnover": null,
      "id": 2,
      "creationDate": 7654536,
      "type": "PUBLIC"
    },
    {
      "postalAddress": "address",
      "coordinates": {
        "x": 2,
        "y": 5.4
      },
      "name": "name 1",
      "annualTurnover": 2.3,
      "id": 1,
      "creationDate": 97658765,
      "type": "COMMERCIAL"
    }
  ]
}
```