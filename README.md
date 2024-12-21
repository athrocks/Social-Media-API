# Social Media API

This repository contains the backend of a **Social Media API** built with **Spring Boot**. The project is designed to provide core functionalities for a social media platform, such as user authentication, post creation, following system, likes, comments, and city-based categorization.

## Features

- **User Authentication**: Support for user registration and login (currently planned for future implementation with Spring Security).
- **Follow System**: Allows users to follow and unfollow each other.
- **Post Creation**: Users can create posts, which are associated with a city.
- **Likes & Comments**: Users can like and comment on posts.
- **City-Based Categorization**: Posts are tied to a city, allowing for location-based sorting.

## Future Plans

1. **API Documentation**: provided on 21 December 2024
2. **Authentication with Spring Security**: Implementing authentication and authorization features using **Spring Security** in collaboration with the frontend will be added soon.

## Installation

### Requirements

- Java 21 or above
- Spring Boot
- MySQL or another preferred database
- Swagger/OpenAPI 3.0

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

# Social Media API Documentation

This document provides details about the REST APIs available in the Social Media application.

## Table of Contents
- [User Management](#user-management)
- [Post Management](#post-management)
- [Comment Management](#comment-management)
- [Like System](#like-system)
- [Follow System](#follow-system)
- [Report System](#report-system)
- [City Management](#city-management)

## User Management

### User Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/users/` | Retrieve all users |
| POST | `/users/` | Create a new user |
| GET | `/users/{id}` | Get user by ID |
| PUT | `/users/{id}` | Update user details |
| DELETE | `/users/{id}` | Delete a user |
| GET | `/users/search?keyword={keyword}` | Search users by keyword |

#### User Model
```json
{
  "userID": "string",
  "firstName": "string",
  "lastName": "string",
  "userName": "string",
  "address": "string",
  "gender": "string",
  "dob": "date",
  "avatarURL": "string"
}
```

## Post Management

### Post Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/posts/` | Get all posts |
| POST | `/posts/` | Create a new post |
| GET | `/posts/{id}` | Get post by ID |
| PUT | `/posts/{id}` | Update a post |
| DELETE | `/posts/{id}` | Delete a post |
| GET | `/posts/users/{userId}` | Get all posts by user |
| GET | `/posts/users/photos/{userId}` | Get user's photo posts |
| GET | `/posts/photos` | Get all photo posts |

#### Post Model
```json
{
  "postID": "integer",
  "user": "User object",
  "city": "City object",
  "imageURL": "string",
  "content": "string",
  "createDay": "datetime"
}
```

## Comment Management

### Comment Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/comment/` | Get all comments |
| POST | `/comment/` | Add a new comment |
| DELETE | `/comment/{id}` | Delete a comment |
| GET | `/comment/users/{userId}` | Get user's comments |
| GET | `/comment/posts/{postId}` | Get post's comments |

#### Comment Model
```json
{
  "commentID": "integer",
  "post": "Post object",
  "user": "User object",
  "content": "string",
  "imageURL": "string"
}
```

## Like System

### Like Post Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/likepost/` | Get all post likes |
| POST | `/likepost/` | Create a post like |
| GET | `/likepost/{likePostID}` | Get specific like |
| DELETE | `/likepost/{likePostID}` | Remove a like |
| GET | `/likepost/users/{userId}` | Get user's likes |
| GET | `/likepost/posts/{postId}` | Get post's likes |

### Like Comment Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/likecomment/` | Get all comment likes |
| POST | `/likecomment/` | Create a comment like |
| GET | `/likecomment/{likeCommentID}` | Get specific like |
| DELETE | `/likecomment/{likeCommentID}` | Remove a like |
| GET | `/likecomment/users/{userId}` | Get user's likes |
| GET | `/likecomment/comment/{commentID}` | Get comment's likes |
| GET | `/likecomment/totalLikes/{commentID}` | Get total likes for comment |

## Follow System

### Follow Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/follows/` | Get all follows |
| POST | `/follows/` | Create a follow |
| GET | `/follows/{id}` | Get specific follow |
| DELETE | `/follows/{id}` | Remove a follow |
| GET | `/follows/target/{userId}` | Get user's followers |
| GET | `/follows/follower/{userId}` | Get user's following |

#### Follow Model
```json
{
  "followID": "integer",
  "follower": "User object",
  "userTarget": "User object"
}
```

## Report System

### Report Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/reports/` | Get all reports |
| POST | `/reports/` | Create a report |
| GET | `/reports/{reportId}` | Get specific report |
| PUT | `/reports/{reportId}` | Update a report |
| DELETE | `/reports/{reportId}` | Delete a report |
| GET | `/reports/users/{userId}` | Get user's reports |

#### Report Model
```json
{
  "reportID": "string",
  "content": "string",
  "typeReport": "enum(POST, COMMENT, USER)",
  "idTarget": "string",
  "userReport": "User object"
}
```

## City Management

### City Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/city/` | Get all cities |
| POST | `/city/` | Create a city |
| GET | `/city/{id}` | Get city by ID |
| DELETE | `/city/{id}` | Delete a city |

#### City Model
```json
{
  "cityID": "integer",
  "cityName": "string",
  "cityMapURL": "string"
}
```

## Response Codes

| Status Code | Description |
|-------------|-------------|
| 200 | Success |
| 400 | Bad Request |
| 404 | Not Found |

## Authentication

_Note: Will Add Soon_

---




