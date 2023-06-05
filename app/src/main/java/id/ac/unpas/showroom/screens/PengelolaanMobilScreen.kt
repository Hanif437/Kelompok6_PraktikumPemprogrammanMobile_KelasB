package id.ac.unpas.showroom.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.ac.unpas.showroom.model.Mobil
import kotlinx.coroutines.launch

@Composable
fun PengelolaanMobilScreen(navController : NavHostController, snackbarHostState: SnackbarHostState, modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<PengelolaanMobilViewModel>()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val items: List<Mobil> by viewModel.list.observeAsState(initial = listOf())

    Column(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = {
            navController.navigate("tambah-pencatatan-mobil")
        }) {
            Text(text = "Tambah")
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Merk", fontSize = 14.sp)
                        Text(text = item.merk, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Model", fontSize = 14.sp)
                        Text(text = item.model, fontSize = 16.sp, fontWeight =
                        FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Bahan Bakar", fontSize = 14.sp)
                        Text(text = "${item.bahan_bakar}", fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                }
                Row(Modifier.padding(top = 0.dp, start = 15.dp, end = 15.dp, bottom = 15.dp).fillMaxWidth().clickable {
                    navController.navigate("edit-pengelolaan-mobil/${item.id}")
                }) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Dijual", fontSize = 14.sp)
                        if(item.dijual.equals("1")){
                            Text(text = "Iya", fontSize = 16.sp, fontWeight =
                            FontWeight.Bold)
                        }else{
                            Text(text = "Tidak", fontSize = 16.sp, fontWeight =
                            FontWeight.Bold)
                        }
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Deskripsi", fontSize = 14.sp)
                            Text(text = item.deskripsi, fontSize = 16.sp, fontWeight =
                            FontWeight.Bold)
                        }
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })

        }
    }
    LaunchedEffect(scope) {
        viewModel.loadItems()
    }
    viewModel.success.observe(LocalLifecycleOwner.current) {
        if (it) {
            scope.launch {
                viewModel.loadItems()
            }
        }
    }
    viewModel.toast.observe(LocalLifecycleOwner.current) {
        scope.launch {
            snackbarHostState.showSnackbar(it, actionLabel =
            "Tutup", duration = SnackbarDuration.Long)
        }
    }
}