# TicTacToe

Game with noughts and corsses 

## Libraries used

- Koin for dependency injection.
- Junit 5 & Mockito for testing.
- ViewModel and dataBinding

## Project architecture

![Captura de pantalla 2022-01-25 a las 23 09 27](https://user-images.githubusercontent.com/21090916/151068094-4d51e33e-8cd8-49f0-88b1-20222b0b3c7b.png)

The different modules have different responsabilities:

* `app`         -> Has the main logic of the app.
* `core`        -> Contain all the logic that could potentially use all over the app. Here I have implemented the base classes.
* `components`  -> Contain all the custom views that would be used all over the app.

## Gradle files structure

![Captura de pantalla 2022-01-25 a las 23 19 20](https://user-images.githubusercontent.com/21090916/151069275-3c8cf533-950a-40d9-b92d-7188f5c424e0.png)


BASE.GRADLE contain all the common logic for all the different modules (app, core and components).
DEPENDENCIES.GRADLE contain a list of LinkedHashMaps with all the dependencies and all the correspondant versions.
The rest of the gralde files only contain the specific setup that each module require.

