<h1 align="center">
Easy Cash Android Task
</h1>

:point_right: Clean Architecture:
-----------------
<div align="center">
<img src="https://github.com/Mina-Mikhail/Kotlin-Base-MVVM/blob/master/images/architecture.png">
</div>


:point_right: Domain & Data Layer:
-----------------
<div align="center">
<img src="https://github.com/Mina-Mikhail/Kotlin-Base-MVVM/blob/master/images/data_layer.png">
</div>


:point_right: Presentation Layer:
-----------------
<div align="center">
<img src="https://github.com/Mina-Mikhail/Kotlin-Base-MVVM/blob/master/images/ui_layer.png">
</div>


:point_right: Architecture:
-----------------

- Following Clean Architecture.
- MVVM Architecture.
- Repository pattern.
- Use Cases.
- Applying SOLID principles, each class has a single job with separation of concerns by making classes independent
  of each other and communicating with interfaces.
- Using Kotlin-KTS & buildSrc to handle project dependencies.

:point_right: Tech Stack & Libraries:
-----------------

- Navigation component - navigation graph for navigating and replacing screens/fragments
- DataBinding - allows to more easily write code that interacts with views and replaces ```findViewById```.
- ViewModel - UI related data holder, lifecycle aware.
- Flow & StateFlow - Build data objects that notify views when the underlying database changes.
- Dagger-Hilt for dependency injection. Object creation and scoping is handled by Hilt.
- Kotlin Coroutines - for managing background threads with simplified code and reducing needs for callbacks
- Retrofit2 & OkHttp3 - to make REST requests to the web service integrated.
- Coil - for image loading.
- Material Bottom Navigation - to handle bottom tabs with support for multiple backStack.

:point_right: Project Structure:
-----------------

- Tasks contains the following screens :
  - Splash.
  - App Tutorial.
  - Login - (With Business Logic).
  - Competitions.
  - Competition Details.
  - Teams.
  - Favorites.
  
:point_right: Extra Modules:
-----------------

- You will find extra modules also developed by me like :
  - AppTutorial - (To handle onBoarding tutorial screens).
  - ActionChooser - (A customized pop up with recyclerView of single selection).
  - PrettyPopUp (A customized pop up to display message to user with two actions (positive & negative buttons)).
  - ImagesSlider (An images slider supports auto scrolling for images from url and support GIF images).
  
:point_right: Apis:
-----------------

- I'm using www.football-data.org Apis, and i was created this postman file :
  - https://www.getpostman.com/collections/74eb077bd0a9f0188c49


:point_right: Code Style:
-----------

- Following official kotlin code style

:point_right: Apply Git Hooks:
-----------

- To apply git hooks in order to automate process of styling and checking your code, just follow this steps:
  - Copy ```pre-commit``` file depending on your OS from ```myGitHooks```.
  - Paste it into ```.git/hooks``` in your project.
- Now each time you commit your changes, ```ktlintFormat``` and  ```ktlintCheck``` will automatically run

:point_right: Local Development:
-----------

- Here are some useful Gradle commands for executing this example:
  - `./gradlew clean` - Deletes build directory.

:warning: License:
--------

```
   Copyright (C) 2022 MINA MIKHAIL PRIVATE LIMITED

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
