# 🍽️ SpringBoot-Food_Ordering_System

This is a full-stack web application built for ordering food online. Users can browse restaurants, view menus, place orders, and track order statuses. Admins can manage restaurants, food items, orders, and users.

---

## 📌 Project Features

### 👨‍🍳 For Customers:
- User registration and login
- Browse restaurant listings and menus
- Add items to cart and place orders
- Payment Gateway
- Track order status
- View order history

### 🛠️ For Admins:
- Manage restaurants and food items (CRUD operations)
- Manage customer orders
- View sales and performance reports

---

## 🧰 Tech Stack

### Backend:
- Java 17
- Spring Boot (REST APIs)
- Spring Security (JWT Authentication)
- Spring Data JPA
- Hibernate
- MySQL (Database)
- H2 (In-memory DB for testing)

### Frontend:  
- React JS / Angular / HTML + CSS + JS

### Others:
- Maven (Project management)
- Postman (API testing)
- Git & GitHub (Version control)

---

## 🗂️ Project Structure

```
com.onlinefoodordering
├── controller              // REST controllers
├── model                  // JPA entity classes
├── repository             // JPA repository interfaces
├── service                // Service layer
├── config                 // Security configuration
└── OnlineFoodOrderingApplication.java
```

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/vivek03052002/SpringBoot-Food_Ordering_System.git
cd SpringBoot-Food_Ordering_System
```

### 2. Configure the Database

Update the `application.properties` file with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/food_ordering
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

Or switch to an H2 database for testing:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=create
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

or

```bash
./mvnw spring-boot:run
```

---

## 🌐 Frontend Setup (React JS)

### Prerequisites:
- Node.js and npm installed
- Code editor like VS Code

### Steps:

```bash
# Navigate to the frontend directory
cd frontend

# Install dependencies
npm install

# Start the development server
npm start
```

### Folder Structure (Example):

```
frontend/
├── public/
├── src/
│   ├── components/        # Reusable UI components
│   ├── pages/             # Page-level components (Home, Menu, Cart, etc.)
│   ├── services/          # API service calls using Axios or Fetch
│   ├── App.js             # Root component
│   └── index.js           # Entry point
├── package.json           # Project config and dependencies
```

- API base URLs should be configured to point to the backend (Spring Boot) endpoints.
- Use environment variables (e.g., `.env`) for secure API key and base URL management.

---

## 🔐 Authentication & Security

This project uses **JWT (JSON Web Tokens)** for authentication and authorization.

- After successful login, a JWT token is returned.
- Include this token in the header for accessing secured endpoints:

```http
Authorization: Bearer <your_token>
```

---

## 🔄 API Testing

You can test the REST APIs using:

- **Postman** (Import API collection if provided)
- **Swagger UI** *(If configured)*

---

## 📦 Future Enhancements

- Real-time order tracking using WebSockets
- Mobile app integration
- Delivery agent module
- Email and SMS notifications

---

## 🤝 Contributing

Contributions are welcome!

1. Fork the repository  
2. Create a new branch (`git checkout -b feature-name`)  
3. Commit your changes (`git commit -am 'Add feature'`)  
4. Push to the branch (`git push origin feature-name`)  
5. Create a new Pull Request

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---

## 👤 Author

**Vivek Akhilesh Tiwari**  
- GitHub: ([https://github.com/vivek03052002](https://github.com/vivek030502))  
- LinkedIn: ([https://www.linkedin.com/in/vivek-tiwari-290224283/](https://www.linkedin.com/in/vivek-tiwari-290224283/))

---
