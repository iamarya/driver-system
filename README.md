# Driver System
The designed system will manage driver details and REST services are exposed to intract with the server.
Following steps are need to be done for running the application.
### Step 1
To package the application and create a jar file run `mvn install`.

### Step 2
Run the below command to run the application. Pass the file path with name for storing the driver details. The driver details are stored in comma separated. A sample file ig given inside resource directory. But when started for 1st time, this file can be given empty and application will create new enries through API.
`driversystem-0.0.1-SNAPSHOT.jar --ds.filename="C:/tmp/driver_details.csv"`

## Examples
### Create driver

Run the below query to create a driver in the system.
```
curl -X POST \
  http://localhost:8080/drivers/create \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: bd8590a1-62d2-4884-3332-89a347c5bcfb' \
  -d '{"firstName":"Arya","lastName":"Mishra", "dateofBirth": "1985-05-26"}'
```

### Validations
If the request is not valid, for example the first name and last name if have special character then error message will be displayed.
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


