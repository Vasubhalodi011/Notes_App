# Notes App

A modern Android application for managing notes with priority levels and theme support.

## Features

- Create, read, update, and delete notes
- Priority levels (High, Urgent, Medium, Normal)
- Color-coded notes based on priority
- Dark/Light theme support
- Material Design UI
- SQLite database for data persistence
- ViewPager2 with Bottom Navigation

## Screenshots

[Add screenshots here]

## Video Demo

[Add video demo link here]

## Technical Details

- Minimum SDK: 21 (Android 5.0)
- Target SDK: 33 (Android 13)
- Language: Kotlin
- Architecture: MVVM
- Libraries:
  - Room for database
  - ViewModel and LiveData
  - Material Design Components
  - ViewPager2
  - Coroutines

## Setup Instructions

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the app on an emulator or physical device

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/jayesh/notesapp/
│   │   │   ├── data/
│   │   │   │   ├── Note.kt
│   │   │   │   ├── NoteDao.kt
│   │   │   │   ├── NotesDatabase.kt
│   │   │   │   └── NotesRepository.kt
│   │   │   ├── ui/
│   │   │   │   ├── NotesAdapter.kt
│   │   │   │   ├── NotesViewModel.kt
│   │   │   │   └── fragments/
│   │   │   │       ├── HomeFragment.kt
│   │   │   │       ├── AddEditNoteFragment.kt
│   │   │   │       └── SettingsFragment.kt
│   │   │   └── MainActivity.kt
│   │   └── res/
│   │       ├── layout/
│   │       ├── values/
│   │       └── drawable/
│   └── test/
└── build.gradle
```

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

Jayesh Parmar
- Email: jayeshbapu001j@gmail.com
- LinkedIn: [Jayesh Parmar](https://www.linkedin.com/in/jayesh-bapu-1751b72a4/)
- GitHub: [jayesh001j](https://github.com/jayesh001j) 