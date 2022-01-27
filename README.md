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
* `core`        -> Contain all the logic that could potentially use all over the app. Here I have implemented the base classes and all the extensions.
* `components`  -> Contain all the custom views that would be used all over the app.

## Presentation layer architecture

The presentation layer is now based on three different files:

`ViewModel`, `Fragment` and `State`

Where the ViewModel would do all the logic and the states would be managed in the Fragment. For example:

The `viewModel` (Does an API call) -> the `viewModel` receives data from the API -> the `viewModel` updates the state -> The `state` is observed in the `Fragment` ->
when the `state` is updated, then in the `Fragment` we are able to make the pertinent updates.

![Captura de pantalla 2022-01-27 a las 14 15 39](https://user-images.githubusercontent.com/21090916/151366281-df5b814d-6f37-4111-9e4c-9e0b5781c2a8.png)

## DataBinding

In this project I have used two different ways to bind data to the custom view `GameBoard`.

* Using 2 way dataBinding

For this, I have created a BindingAdapter and an InverseBindingAdapter for the attribute `playerTurn`, where it is going to emit data to the view when user touch any cell of the board and there is no winner combination or draw.

```
@JvmStatic
@InverseBindingAdapter(attribute = "playerTurn", event = "playerTurnAttrChanged")
fun getPlayerTurn(view: GameBoard): Int {
    return view.viewModel.player
}
@SuppressLint("ClickableViewAccessibility")
@JvmStatic
@BindingAdapter(value = ["playerTurnAttrChanged"])
fun setListener(view: GameBoard, listener: InverseBindingListener?) {
    listener?.let {
        view.setOnTouchListener { _, motionEvent ->
            listener.onChange()
            view.onTouchEvent(motionEvent)
        }
    }
}
```

* Access to variables of the view using View binding

In the `GameBoard` custom view, I have created two different variables called `isWinnerMovement` and `isDraw` that has a custom get function that is triggered when any of them are consulted.

```
viewModel.checkGameStatus(
    binding.gbGame.isWinnerMovement,
    binding.gbGame.isDraw,
    playerTurn
)
```

## Testing

For this project, I have created unit tests for the two viewModels that I have created in this project, `TTTViewModel` and `GameBoardViewModel`. For these uni tests I have used
JUnit 5 and I have also created a custom annotation to be able to test LiveData objects (`InstantExecutorExtension`).

## Gradle files structure

![Captura de pantalla 2022-01-25 a las 23 19 20](https://user-images.githubusercontent.com/21090916/151069275-3c8cf533-950a-40d9-b92d-7188f5c424e0.png)

* `BASE.GRADLE` contain all the common logic for all the different modules (app, core and components).
* `DEPENDENCIES.GRADLE` contain a list of LinkedHashMaps with all the dependencies and all the correspondent versions.
* The rest of the gradle files only contain the specific setup that each module require.

