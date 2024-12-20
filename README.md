# Social Media API

This repository contains the backend of a **Social Media API** built with **Spring Boot**. The project is designed to provide core functionalities for a social media platform, such as user authentication, post creation, following system, likes, comments, and city-based categorization.

## Features

- **User Authentication**: Support for user registration and login (currently planned for future implementation with Spring Security).
- **Follow System**: Allows users to follow and unfollow each other.
- **Post Creation**: Users can create posts, which are associated with a city.
- **Likes & Comments**: Users can like and comment on posts.
- **City-Based Categorization**: Posts are tied to a city, allowing for location-based sorting.

## Future Plans

1. **API Documentation**: After the frontend work is completed, comprehensive API documentation will be provided to help with integration and usage.
2. **Authentication with Spring Security**: Implementing authentication and authorization features using **Spring Security** in collaboration with the frontend will be added soon.

## Installation

### Requirements

- Java 21 or above
- Spring Boot
- MySQL or another preferred database

### Steps

1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/social-media-api.git
   cd social-media-api
   ```

2. Build the project:
   ```bash
   ./mvnw clean install
   ```

3. Configure your application properties (database connection, JWT secret, etc.) in `application.properties`.

4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

5. The API will be accessible at `http://localhost:8080`.

## Planned Features

- **Authentication**: Implement user login, registration, and secure endpoints using **Spring Security**.
- **Frontend Integration**: The API will be integrated with a frontend application, which will provide a user-friendly interface for interacting with the platform.

## License

This project is licensed under the MIT License.

---
