# Eten

## Current implementation
This application lets you find restaurants that are close to your current location. This is a multimodule application that follows clean architecture guidelines. Data flow from the `data` layer to the presentation layer using [coroutines.flow](https://kotlinlang.org/docs/reference/coroutines/flow.html), and from `ViewModel` to `Fragment` using [LiveData](https://developer.android.com/topic/libraries/architecture/livedata). Navigation is handled using [Navigation Component](https://developer.android.com/guide/navigation), and deeplinks are used to ease multimodule navigation.

## Possible improvements
- Display loading and error states.
- Handle errors more granularly to apply different strategies such as retries, when needed.
- Improve the restaurant detail page by showing more information & pictures.
- Add a local storage data source to access data offline.
- Implement a more user friendly UI that follows material design guidelines.
