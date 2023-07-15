# Notification4J

Java-based Native Notification Library for Windows

<img src="screenshots/GetStarted.png">

Notification4J is a Java wrapper for the [WinToast](https://github.com/mohabouje/WinToast) C++ library, developed using JNI. This wrapper acts as a bridge between Java and native code, enabling communication and utilization of the WinToast functionality within Java projects.

## Table of Contents
- [Installation](#installation)
- [Features](#features)
- [Usage](#usage)
- [Examples](#examples)
- [Contributing](#contributing)
- [License](#license)

## Installation
### Installation

### Gradle
Add it in your root `build.gradle` at the end of repositories:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add the following dependency to your project's `build.gradle` file:

```groovy
dependencies {
     implementation 'com.github.PreetJagani:Notification4J:v1.0.0'   
}
```

### Maven
Add the JitPack repository to your build file

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add the dependency

```xml
<dependency>
    <groupId>com.github.PreetJagani</groupId>
    <artifactId>Notification4J</artifactId>
    <version>v1.0.0</version>
</dependency>
```

## Features
- Create notifications with customizable title, subtitle, and content.
- Display notifications with including avatar.
- Include action buttons within notifications for user interaction.
- Handle click events on notifications.
- Customize notification sound for individual notification.
- Support for a custom Java logger, to handle and process native messages and log them using preferred logging framework.

## Usage

### 1. Initialization
To start using the library, you need to initialize it with the application name and appUserModelID. Follow the example below to perform the initialization:
```java
// Initialize the library
NotificationManager.init("Notification4J", "Notification4J.ID");
```

Optionally, you can provide a notification handler to handle notification actions and a logger as demonstrated below:

```java
// Set notification click listener
NotificationManager.listener = new NotificationClickListener() {
    @Override
    public void onNotificationClicked(int identifier) {
        System.out.println("Clicked " + identifier);
    }

    @Override
    public void onNotificationActionClicked(int identifier, int index) {
        System.out.println("Clicked " + identifier + " index " + index);
    }
};
```

```java
// Set custom log handler
NotificationManager.logHandler = message -> {
    System.out.println("Notification log: " + message);
};
```
The log handler receives messages and information from the native CPP file. You can customize the logger to use a Java logging library like [log4J](https://github.com/apache/logging-log4j2). For more information on log4J, you can refer to this link.

### 2. Creating Notifications
To create a notification, you can utilize the singleton NotificationManager.getInstance() and invoke the postNotification() method with the title, subtitle, and notification identifier like this
```java
// Notification with title and subtitle
NotificationManager.getInstance().postNotification("First Notification", "Hello, World!", 1);
```

Optionally you can provide sound, notification actions and avatar like this
 ```java
 // Notification with sound, actions and notification avatar
 NotificationManager.getInstance().postNotification("First Notification",
                 "Hello, World!", 1, <Path_To_png_Avatar>,
                 NotificationSound.Mail, new String[]{"Get Started"});
 ```

### 3. Handling Events
To handle notification events, you can set the `NotificationManager.listener` as shown in the Initialization step. This listener allows you to respond to user interactions with the notification.

```java
// Set notification click listener
NotificationManager.listener = new NotificationClickListener() {
    @Override
    public void onNotificationClicked(int identifier) {
        // Perform actions based on the notification identifier
    }

    @Override
    public void onNotificationActionClicked(int identifier, int index) {
        // Perform actions based on the notification identifier and action index
    }
};
```  

### 4. Additional Functionality

To clear all notifications, you can simply call the `clearNotifications()` method using the NotificationManager instance:
```java
NotificationManager.getInstance().clearNotifications();
```


## Examples
// quick start link

## Contributing


## License


## Contact
