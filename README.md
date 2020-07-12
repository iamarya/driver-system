# Driver System
The designed system will manage driver details and REST services are exposed to interact with the server. Springboot is used and data is stored in simple file. To address the race condition in multithreading environment of file read and write ReentrantReadWriteLock is used. So reads are parallel and write will be synchronised.
Following steps are need to be done for running the application.
### Step 1
To package the application and create a jar file run `mvn install`.

### Step 2
Run the below command to run the application. Pass the file path with a name for storing the driver details. The driver details are stored in comma-separated. A sample file is given inside the resource directory. But when started for 1st time, this file can be given empty and application will create new entries through API.
```driversystem-0.0.1-SNAPSHOT.jar --ds.filename="C:/tmp/driver_details.csv"```

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
