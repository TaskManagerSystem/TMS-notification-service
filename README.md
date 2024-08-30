## TMS notification service

### Overview:
The service is designed to automate notifications to users about 
project deadlines. It sends messages to Telegram, and if the user
is not connected to Telegram, the notification is sent to their email.

***
## Project Repositories
The project consists of several separate repositories:

1. **[TMS Main Service](https://github.com/TaskManagerSystem/TMS-main-service)**: The main service for task and project management.
2. **[TMS Attachment Service](https://github.com/TaskManagerSystem/TMS-attachment-service)**: The service for managing attachments and integration with Dropbox.
3. **[TMS Notification Service](https://github.com/TaskManagerSystem/TMS-notification-service)**: The service for sending notifications via email and Telegram.
4. **[TMS Common DTO](https://github.com/TaskManagerSystem/TMS-common-dto)**: A shared DTO library used across all microservices.

***
## Key features:
1. **Integration with Task Management Service**:
The service integrates with tasks management service to 
track task and project phase deadlines.
2.  **Personalized Notification**:
    Notifications are customized for each user based on their role in the project
and the importance of the deadline.
3. **Telegram Messaging**:
   If the user is connected to Telegram, messages are automatically sent to this messenger.
4. **Alternative Channel – Email**:
   If the user is not registered in Telegram, or if Telegram is unavailable, the message 
is automatically sent to their specified email address.

***

## Technology:
The service was implemented using a microservices architecture
with Apache Kafka as the core messaging system. Key components
are responsible for integrating with the Telegram API, sending 
emails, and connecting with the project management system. Kafka
ensures reliable and scalable message delivery between these 
components, allowing for real-time processing and communication
across the service. This architecture enhances the service’s 
ability to handle high volumes of notifications efficiently, 
ensuring that messages are delivered promptly and reliably
to the appropriate channels.

***
## Security:
Ensuring the security of user data is a priority.
All messages are encrypted, and access to the service is protected 
by authentication and authorization.

This service helps teams manage projects effectively 
and receive timely reminders about deadlines, minimizing
the risk of missing important tasks.