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

Vasu Bhalodi
- Email: vasubhalodi12@gmail.com
- LinkedIn: [Vasu Bhalodi](https://www.linkedin.com/in/vasu-bhalodi-092450335/)
- GitHub: [Vasubhalodi011](https://github.com/Vasubhalodi011)


- <p align="center">
  <img src="https://github.com/user-attachments/assets/cec68545-fdc2-4897-8722-78026d3b1e14" width="22%" style="margin-right: 2%;">
  <img src="https://github.com/user-attachments/assets/dfa5f8e9-8b9b-4fb6-9ed8-593b822ff8e3" width="22%" style="margin-right: 2%;">
  <img src="https://github.com/user-attachments/assets/1c54375e-6c90-4112-9cc2-ebd010949cf2" width="22%" style="margin-right: 2%;">
  <img src="https://github.com/user-attachments/assets/d96b265f-9fb5-47c4-9301-114848c50898" width="22%" style="margin-right: 2%;">
  <img src="https://github.com/user-attachments/assets/2eb67216-fb75-4860-b746-becf1a15cfb0" width="22%" style="margin-right: 2%;">
  <img src="https://github.com/user-attachments/assets/86cc8957-d738-4906-9a94-6be2bc47f8a3" width="22%">
</p>
