package id.ac.unpas.showroom.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import id.ac.unpas.showroom.model.SepedaMotor
import id.ac.unpas.showroom.ui.theme.Purple700
import id.ac.unpas.showroom.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormPencatatanSepedaMotorScreen(navController: NavHostController, id :String? = null, modifier : Modifier = Modifier) {
    val viewModel = hiltViewModel<PengelolaanSepedaMotorViewModel>()
    val model = remember { mutableStateOf(TextFieldValue("")) }
    val warna = remember { mutableStateOf(TextFieldValue("")) }
    val kapasitas = remember { mutableStateOf(TextFieldValue("")) }
    val tanggal_rilis = remember { mutableStateOf(TextFieldValue("")) }
    val harga = remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else "Simpan"


    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "Model") },
            value = model.value,
            onValueChange = {
                model.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Beat 2010") }
        )
        OutlinedTextField(
            label = { Text(text = "Warna") },
            value = warna.value,
            onValueChange = {
                warna.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Hitam") }
        )
        OutlinedTextField(
            label = { Text(text = "Kapasitas") },
            value = kapasitas.value,
            onValueChange = {
                kapasitas.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "100cc") }
        )
        OutlinedTextField(
            label = { Text(text = "Tanggal Rilis") },
            value = tanggal_rilis.value,
            onValueChange = {
                tanggal_rilis.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "2010") }
        )
        OutlinedTextField(
            label = { Text(text = "Harga") },
            value = harga.value,
            onValueChange = {
                harga.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "15000000") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                if (id == null){
                    scope.launch {
                        viewModel.insert(model.value.text, warna.value.text, kapasitas.value.text, tanggal_rilis.value.text, harga.value.text)
                    }
                } else {
                    scope.launch{
                        viewModel.update(id, model.value.text, warna.value.text, kapasitas.value.text, tanggal_rilis.value.text, harga.value.text)
                    }
                }
                navController.navigate("sepeda-motor")

            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                scope.launch {
                    viewModel.insert(model.value.text, warna.value.text, kapasitas.value.text, tanggal_rilis.value.text, harga.value.text)
                    model.value = TextFieldValue("")
                    warna.value = TextFieldValue("")
                    kapasitas.value = TextFieldValue("")
                    tanggal_rilis.value = TextFieldValue("")
                    harga.value = TextFieldValue("")
                }
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
    viewModel.isLoading.observe(LocalLifecycleOwner.current) {
        isLoading.value = it
    }
    if (id != null) {
        LaunchedEffect(scope) {
            viewModel.loadItem(id) { SepedaMotor ->
                SepedaMotor?.let {
                    model.value = TextFieldValue("")
                    warna.value = TextFieldValue("")
                    kapasitas.value = TextFieldValue("")
                    tanggal_rilis.value = TextFieldValue("")
                    harga.value = TextFieldValue("")
                }
            }
        }
    }
}