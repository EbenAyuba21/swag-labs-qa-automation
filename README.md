# swag-labs-qa-automation

# 🧪 Saucedemo and Petstore Automation Test Suite

This project contains both **UI automation tests** and **API tests** for the [Saucedemo](https://www.saucedemo.com/) and [Swagger PetStore](https://petstore.swagger.io/#/user) respectively

---

## 🧰 Technologies Used

- Java JDK 8+
- Selenium WebDriver
- TestNG
- Maven
- WebDriverManager
- Page Object Model (POM)
- Git
- Eclipse
- Postman

---

## ⚙️ Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/EbenAyuba21/swag-labs-qa-automation.git
cd swag-labs-qa-automation
```

### 2. Import as a Maven Project in Eclipse:
- File → Import → Maven → Existing Maven Project.


### 3. 🔧 Configuration
Update the config.properties file inside the resources directory with your credentials:

```bash
baseUrl=https://www.saucedemo.com/
standard_username=standard_user
problem_username=problem_user
password=secret_sauce
```


## 🚀 How to Run UI Automation Tests
▶️ Option 1: Run via Eclipse

- Right-click testng.xml → Run As → TestNG Suite.

▶️ Option 2: Run via Terminal

```bash
mvn clean test
```

## ▶️ How to Run API Tests with Postman
1. Open Postman.
2. Import the `Petstore_API_Test.postman_collection.json` collection.
3. Run requests individually or as a collection via the **Runner** tab.

### 🔄 Run from CLI using Newman
1. Install Newman (if you haven’t already):

```bash
npm install -g newman
```
2. Run the collection:

```bash
newman run Petstore_API_Test.postman_collection.json
```


## 👨‍💻 Author
Ebenezer Ayuba – QA Engineer
✉️ ebenezerayuba21@gmail.com