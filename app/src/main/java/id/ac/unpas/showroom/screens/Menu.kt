package id.ac.unpas.showroom.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import id.ac.unpas.showroom.R

enum class Menu (
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {

    HOME(R.string.home, Icons.Default.Home, "home"),
    SHOWROOM(R.string.showroom, Icons.Default.List, "showroom"),
    SETTING(R.string.setting, Icons.Default.Settings, "setting");

    companion object {
        fun getTabFromResource(@StringRes resource: Int): Menu {
            return when (resource) {
                R.string.home -> HOME
                R.string.showroom -> SHOWROOM
                else -> SETTING
            }
        }
    }
}