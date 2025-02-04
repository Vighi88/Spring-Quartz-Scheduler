# README

## Pre-requisite
- Java 17
- Spring 3.x

## Setup Instructions

1. Run the DDL script `quartz.sql` present under the `resources` folder. If required, execute `task.sql` as well.
2. Run the following command to build the project:
   ```sh
   mvn clean install -DskipTests -f pom.xml
   ```
3. Change the application port if required in `application.yaml`.
4. Update the database name as per your local configuration.
5. Run the main application and spin up the service.

## API Usage

Use the following API, which behaves as if it receives an event from a WiFi service to the scheduler for disabling guest WiFi (for POC purposes, replicating Kafka behavior):

### Request

**POST** `http://localhost:8091/api/event`

```json
{
    "requestId": "12345-47356-34534-34534-21355-00012",
    "delayInSeconds": 50,
    "type": "WIFI_DISABLE",
    "body": "{ \"disable\": true, \"reason\": \"Resume Execution after service restart\" }",
    "serviceName": "WiFiService",
    "targetTopic": "guest-wifi"
}
```

### Notes
- `delayInSeconds` is configurable; the application will schedule the job based on the value mentioned in `delayInSeconds`.
- Observe the entry in the `task` table with `Status` as `scheduled`.
- Also, check the `qrtz_job_details` and `qrtz_triggers` tables.

### Testing Service Restart Scenario
To validate a scenario where execution resumes after a service restart:
1. Set `delayInSeconds` to more than 50 seconds.
2. Trigger the request.
3. Restart your application.
4. You will see that the application picks up the batch from `qrtz_triggers` ("batch acquisition of 1 trigger").
5. Execution resumes once the `execute-at` time arrives.

### Running in Cluster Mode
To run the application in cluster mode, enable the cluster properties in `application.yaml`.

### Video DEMO


https://github.com/user-attachments/assets/a6ea8c06-ce4d-443a-b3f5-00cfd8ae0e56


https://github.com/user-attachments/assets/04ebf0dd-8d1c-442d-97f0-c01cb5d80c8a


https://github.com/user-attachments/assets/a1cdd301-b759-4271-8655-246ccd80c324


