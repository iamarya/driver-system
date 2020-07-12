# Driver System
The designed system will manage driver details and REST services are exposed to interact with the server. Springboot is used and data is stored in a simple file. To address the race condition in multithreading environment of file read and write ReentrantReadWriteLock is used. So reads are parallel and write will be synchronised.
Following steps are need to be done for running the application. Make sure java 8 and maven is installed.
### Step 1
To package the application and create a jar file run `mvn install`.

### Step 2
Run the below command to run the application. Pass the file path with a name for storing the driver details. The driver details are stored in comma-separated. A sample file is given inside the resource directory. But when started for 1st time, this file can be given empty and application will create new entries through API.
```
java -jar driversystem-0.0.1-SNAPSHOT.jar --ds.filename="C:/tmp/driver_details.csv"
```

## Examples
### Create a driver

Run the below query to create a driver in the system.
```
curl -X POST \
  http://localhost:8080/drivers/create \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{"firstName":"Arya","lastName":"Mishra", "dateofBirth": "1985-05-26"}'
```

#### Validations
If the request is not valid, for example, the first name and last name if have special character then error message will be displayed.
`{"firstName":"A2,rya","lastName":"Mi.shra", "dateofBirth": "1985-05-26"}`
```
{
    "timestamp": "2020-07-11T23:52:00.230+00:00",
    "status": 400,
    "errors": [
        "First name is invalid",
        "Last name is invalid"
    ]
}
```

### Get Drivers
To get all the driver details run the curl command. 

```curl -X GET http://localhost:8080/drivers/```

Sample Result:
```
[
    {
        "id": "9538de1e-b18d-4fe9-a3bf-4fe311d5d7c6",
        "firstName": "Satrujeet",
        "lastName": "Dhirsamant",
        "dateofBirth": "1987-06-12"
    },
    {
        "id": "ce610a20-e964-4e13-b7e5-71e353f8a1b7",
        "firstName": "Khageswar",
        "lastName": "Sahoo",
        "dateofBirth": "1986-07-12"
    },
    {
        "id": "76eb4cc6-b09d-461f-827a-f6661c7159a6",
        "firstName": "Arya",
        "lastName": "Mishra",
        "dateofBirth": "1986-05-26"
    }
```

### Get drivers after the creation date
This API will give the details of the drivers after created the given time. Here the date-time is given in ISO date-time format.
```
curl -X GET \
  http://localhost:8080/drivers/byDate/2020-01-01T12:00:00Z 
```


## Design
As per the instruction simple file is taken to store the details of the driver.

#### UUID
To generate ID automatically UUID is used. Before storing the driver in file this id is generated and stored with the driver.


#### Date format
I have used two types of date format here. For the birthday field, I have used ISO.DATE (yyyy-MM-dd) format and for creation_date I have used ISO.DATE_TIME (yyyy-MM-dd'T'HH:mm:ss'Z') format. Both are ISO standard for data time. 

#### ReentrantReadWriteLock (LOCK)
ReentrantReadWriteLock is used to get the read and write lock. As I am not using the DB, to achieve consistency with the data I have implemented the logic so that writing can be happened by one thread at a time and if reading is happening then write will be in a wait state. Similarly, reading can happen parallel, but if writing is going on the reading will be in a wait state. 

#### Exception Handling and Logging
A global exception handler is written to address any exception occurring across the system. Slf4j is used for logging purpose. One custom exception is written to handling logical exceptions.


## Future-Work
If I will get more time I would like to think about the performance improvement side. For example, some cache framework (ex. Ehcache) can be used to store the records. If there is no record creation then no need to read file for every request. 
Apart from this, If the file size is huge for example 5 GB or more, then the file can be read parallelly using ExecutorSerivce and results are collected finally. More thought process required here to maintain the consistency of the data.

