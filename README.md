# ResumeTracker

ResumeTracker is a Spring Boot application designed to help users manage their resumes, track their job applications, and receive notifications when their resumes are viewed by potential employers.

## Features

- **User Authentication**: Users can register and login securely using JWT (JSON Web Tokens) along with Spring Security.
- **Resume Upload**: Users can upload their resumes, which are stored securely in the application.
- **Resume Link Generation**: Upon uploading a resume, the application generates a unique link that users can share with potential employers when applying for jobs.
- **Resume View Notification**: Users receive email notifications when their resumes are viewed by employers, enabling them to track the interest in their profiles.

## Technologies Used

- **Spring Boot**: For building the backend application and providing various features such as security, RESTful APIs, etc.
- **Spring Security**: To handle authentication and authorization.
- **JWT (JSON Web Tokens)**: For secure user authentication and authorization.
- **Spring Data JPA**: For data persistence and manipulation.
- **Spring Mail**: To send email notifications.
- **Hibernate**: As the ORM (Object-Relational Mapping) tool for database interaction.
- **MySQL Database**: As the database management system for storing user data and resumes.


## Getting Started

1. **Clone the Repository**: ```git clone https://github.com/yourusername/resumetracker.git```
2. **Database Configuration**:
- Set up your preferred database (MySQL, PostgreSQL, or H2) and configure the application properties accordingly.
3. **Build and Run**:
- Build the project using Maven or Gradle.
- Run the application locally using your IDE or command line.

4. **Access the Application**:
- Access the application via `http://localhost:8080` or the configured port.
- Register a new user or login with existing credentials.
- Upload your resume and obtain the generated link.

## Usage

- **Register/Login**: Users need to register and log in to access the application's features.
- **Upload Resume**: After logging in, users can upload their resumes.
- **Generate Resume Link**: Upon successful upload, the application generates a unique link for the resume.
- **Share Link**: Users can share the generated link with potential employers when applying for jobs.
- **Receive Notifications**: Users receive email notifications when their resumes are viewed by employers.

## Contributing

Contributions are welcome! Feel free to submit pull requests or open issues for any bugs or feature requests.

## License

This project is licensed under the [MIT License](LICENSE).
