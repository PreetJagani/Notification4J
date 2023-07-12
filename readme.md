# Notification4J

Java-based Native Notification Library for Windows

<img src="screenshots/GetStarted.png">

// todo: cpp implementation of <lib> using JNI

## Table of Contents
- [Installation](#installation)
- [Features](#features)
- [Usage](#usage)
- [Examples](#examples)
- [Contributing](#contributing)
- [License](#license)

## Installation
[Instructions on how to install your library. Include any prerequisites or dependencies, if applicable.]

## Features
[Highlight the key features and capabilities of your notification library.]

## Usage
[Provide a guide on how to use your library. Include code examples, step-by-step instructions, and any necessary configurations.]

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
The log handler receives messages and information from the native CPP file. You can customize the logger to use a Java logging library like log4J. For more information on log4J, you can refer to this link.

### 2. Creating Notifications
[Describe how to create notifications with different properties, such as title, message, icon, etc.]

### 3. Displaying Notifications
[Explain how to display the notifications on the Windows platform, including any specific methods or APIs used.]

### 4. Handling Events
[Provide information on how to handle user interactions with the notifications, such as click events or dismissals.]

## Examples
[Include some code examples demonstrating the usage of your library. Showcase different types of notifications and their configurations.]

## Contributing
[Explain how others can contribute to your library if they're interested. Include guidelines for bug reports, feature requests, and pull requests.]

## License
[Specify the license under which your library is released. Provide any necessary legal information or disclaimers.]

## Contact
[Provide contact information for users to reach out to you with questions or feedback.]