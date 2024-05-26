# farm-test
Farm test project

## APIs
### Submit Planted
Submits the data for the crop planted.\
URL: POST /farm/planted

Request:
```
PlantRequest{
cropType	string
Enum: [ CORN, POTATO, RICE, CABBAGE ]
farm	string
expected	integer($int32)
season	integer($int32)
}
```

### Submit Harvested
Submits the data for the crop harvested. \
URL: POST /farm/harvested

Request:
```
HarvestRequest{
cropType	string
Enum:[ CORN, POTATO, RICE, CABBAGE ]
farm	string
harvested	integer($int32)
season	integer($int32)
}
```

### View total farm reports
Returns a report of the total expected produce vs actual produce.\
URL: GET /farm/reports

Sample Response:
```json
[
  {
    "season": 2023,
    "farm": "Farm a",
    "totalExpected": 17,
    "totalActual": 15
  }
]
```

### View total crop reports
Returns the a report of the expected produce vs actual produce of a crop\
URL: GET /farm/reports/crops\

Sample Response:
```json
[
  {
    "season": 2023,
    "crop": "CORN",
    "expected": 17,
    "actual": 15
  }
]
```

## Note:
This project APIs can be executed by running the command from the root directory: 
```./mvnw spring-boot:run```. 
Then the APIs can be executed with this link: http://localhost:8080/swagger-ui/index.html#/
