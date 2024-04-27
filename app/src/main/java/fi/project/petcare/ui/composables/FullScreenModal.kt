package fi.project.petcare.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import fi.project.petcare.model.data.PetResponse
import fi.project.petcare.ui.screens.PetProfileFormScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenModal(
    showModal: Boolean,
    toggleShowFullModal: () -> Unit,
    onSubmit: (PetResponse.Pet) -> Unit = {},
    newPet: PetResponse.Pet,
    onUpdatePet: (PetResponse.Pet) -> Unit
) {
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    if (showModal) {
        ModalBottomSheet(
            shape = MaterialTheme.shapes.extraSmall,
            onDismissRequest = { toggleShowFullModal() },
            sheetState = bottomSheetState,
            content = {
                Scaffold (
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            modifier = Modifier.padding(bottom = 4.dp),
                            navigationIcon = {
                                IconButton(
                                    onClick = {
                                        scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                                            if (!bottomSheetState.isVisible) {
                                                toggleShowFullModal()
                                            }
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Close,
                                        contentDescription = "Close dialog"
                                    )
                                }
                            },
                            title = {
                                Text(
                                    text = "New pet profile",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            actions = {
                                IconButton(
                                    onClick = {
                                        scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                                            if (!bottomSheetState.isVisible) {
                                                onSubmit(newPet)
                                                toggleShowFullModal()
                                            }
                                        }
                                    },
                                    modifier = Modifier.padding(end = 16.dp)
                                ) {
                                    Text(
                                        text = "Save",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    PetProfileFormScreen(
                        innerPadding,
                        newPet,
                        onUpdatePet
                    )
                }
            }
        )
    }
}