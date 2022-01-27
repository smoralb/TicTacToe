# TicTacToe

Game with noughts and corsses that has different ways to win:

* 4 in row horizontal
* 4 in row vertical
* 2x2 box
* Main diagonal
* Reverse diagonal

## Libraries used

- Koin for dependency injection.
- Junit 5 & Mockito for testing.
- ViewModel, dataBinding and LiveData.

## Project architecture

![Captura de pantalla 2022-01-25 a las 23 09 27](https://user-images.githubusercontent.com/21090916/151068094-4d51e33e-8cd8-49f0-88b1-20222b0b3c7b.png)

The different modules have different responsibilities:

* `app`         -> Has the main logic of the app.
* `core`        -> Contain all the logic that could potentially use all over the app. Here I have implemented the base classes.
* `components`  -> Contain all the custom views that would be used all over the app.

## Presentation layer architecture

The presentation layer is now based on three different files:

`ViewModel`, `Fragment` and `State`

Where the ViewModel would do all the logic and the states would be managed in the Fragment. For example:

The `viewModel` (Does an API call) -> the `viewModel` receives data from the API -> the `viewModel` updates the state -> The `state` is observed in the `Fragment` ->
when the `state` is updated, then in the `Fragment` we are able to make the pertinent updates.

![Captura de pantalla 2022-01-27 a las 14 11 28](https://user-images.githubusercontent.com/21090916/151366032-afcd6443-51f6-4d55-93b1-be09a39bd856.png)

## Testing

For this project, I have created unit tests for the two viewModels that I have created in this project, `TTTViewModel` and `GameBoardViewModel`. For these uni tests I have used
JUnit 5 and I have also created a custom annotation to be able to test LiveData objects (`InstantExecutorExtension`).

## Gradle files structure

![Captura de pantalla 2022-01-25 a las 23 19 20](https://user-images.githubusercontent.com/21090916/151069275-3c8cf533-950a-40d9-b92d-7188f5c424e0.png)

* `BASE.GRADLE` contain all the common logic for all the different modules (app, core and components).
* `DEPENDENCIES.GRADLE` contain a list of LinkedHashMaps with all the dependencies and all the correspondent versions.
* The rest of the gradle files only contain the specific setup that each module require.

