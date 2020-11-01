package main

import tornadofx.App
import tornadofx.launch
import views.MenuUI

class MainApp : App(MenuUI::class)

fun main() = launch<MainApp>()