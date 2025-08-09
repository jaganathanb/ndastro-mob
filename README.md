![NDAstro](./images/dapps.png)

# NDAstro: A Complete Guide to Secure Login, API Calls & Token Management in Jetpack Compose

NDAstro is a **astrology mobile app** built using **Jetpack Compose** and **Kotlin**.  
It provides a vast set of south indian astrology functionalities with at most accuracy. It has been using the **secure and scalable system** by following **MVVM + Clean Architecture**
principles and **Dependency Injection** with **Hilt**. Source of inspiration for the tech stack is from [JetAuth](https://github.com/probelalkhan/JetAuth)

## 🚀 Key Technologies Used

- ✅ **Jetpack Compose** – Modern UI toolkit for building beautiful and reactive UI.
- ✅ **Ktor Client** – Lightweight and efficient networking for API calls.
- ✅ **Jetpack Compose Navigation** – Manages screen transitions seamlessly.
- ✅ **Kotlin Serialization** – Simplifies JSON parsing for API responses.
- ✅ **Hilt (Dependency Injection)** – Ensures modularity and easy testing.
- ✅ **ViewModel + StateFlow** – Manages UI state efficiently.
- ✅ **DataStore** – Stores authentication tokens securely.

## 📦 Installation

### Requirements

### 🔌 API Integration

This project uses the NDAstro API service provided by **[NDAstroAPI](https://github.com/jaganathanb/ndastro-api)**

### Base URL Configuration

The **base URL** for the API is defined in the app-level **`build.gradle`** file. Below is the
configuration used for both the **debug** and **release** build types:

```kotlin
android {
    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"ndastro.onrender.com\"")
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            buildConfigField("String", "BASE_URL", "\"ndastro.onrender.com\"")
        }
    }
}
```

## 📸 Screenshots


## 📝 License

This project is licensed under the **GNU License**. You can freely use, modify, and distribute the code. For more details, check the [GNU License](https://opensource.org/licenses/GNU).


## 🤝 Help & Support

If you encounter any issues or need help with anything, please check the following:

Issues: Look through the issues section to see if your question has already been answered.
Documentation: Make sure to read through the README for detailed instructions on setting up and
using the app.
Community: Ask questions or share feedback by opening a new issue or participating in the
discussion.

## 🌟 Support the Project

If you find this Android Login Tutorial helpful, please consider starring the repository to show
your support! ⭐ It helps others find the project and encourages the community to contribute.

Inspired by [JetAuth](https://github.com/probelalkhan/JetAuth)

