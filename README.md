# Twilio REST


## Configuration
Replace following parameters in `application.properties` file
1. *defaults.to*  
    Replace with your phone number for easier testing
2. *twilio.from*  
    Replace with phone number you received from Twilio
3. *twilio.accountSid*  
    Replace with ACCOUNT SID from Twilio Console
4. *twilio.authToken*  
    Replace with AUTH TOKEN from Twilio Console

## How to Run

### Maven
```bash
./mvnw spring-boot:run
```

### Gradle
```bash
./gradlew bootRun
```
## Usage

### Get Configuration
To Test what values are picked  
[Get Configuration](http://localhost:8080/config)

### Send SMS by GET
This is easier to test using web browsers  
[Send SMS by GET](http://localhost:8080/)

### Send SMS by POST
```bash
curl -X POST -H "Content-Type: application/json" -d"@-" "http://localhost:8080" << EOF
{
    "to": "replace with phone number",
    "message": "hello world"
}
EOF
```

