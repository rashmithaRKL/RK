package presentation.ui.main.map.view_model

import business.core.BaseViewModel
import business.core.DataState
import business.core.ProgressBarState
import business.core.Queue
import business.core.UIComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MapViewModel : BaseViewModel() {
    private val _state = MutableStateFlow(MapState())
    val state: StateFlow<MapState> = _state.asStateFlow()

    fun onTriggerEvent(event: MapEvent) {
        when (event) {
            is MapEvent.OnLocationUpdate -> {
                updateLocation(event.latitude, event.longitude)
            }
            MapEvent.OnError -> {
                appendToMessageQueue(
                    UIComponent.Dialog(
                        title = "Error",
                        description = "Failed to get location. Please check your permissions and try again."
                    )
                )
            }
        }
    }

    private fun updateLocation(latitude: Double, longitude: Double) {
        _state.value = state.value.copy(
            latitude = latitude,
            longitude = longitude
        )
    }
}

data class MapState(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf())
)

sealed class MapEvent {
    data class OnLocationUpdate(val latitude: Double, val longitude: Double) : MapEvent()
    data object OnError : MapEvent()
}
