# swag-labs-qa-automation

# 🧪 Saucedemo Automation Test Suite

This is an automated UI test suite  functionality on [Saucedemo](https://www.saucedemo.com/) using **Selenium WebDriver**, **TestNG**, and the **Page Object Model** design pattern.

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


## 🚀 How to Run Tests
▶️ Option 1: Run via Eclipse

- Right-click testng.xml → Run As → TestNG Suite.

▶️ Option 2: Run via Terminal

```bash
mvn clean test
```

## 👨‍💻 Author
Ebenezer Ayuba – QA Engineer
✉️ ebenezerayuba21@gmail.com