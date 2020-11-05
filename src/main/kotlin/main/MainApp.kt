package main

import tornadofx.App
import tornadofx.launch
import views.MainVUI
import views.UIHeader

class MainApp : App(MainVUI::class)

fun main() = launch<MainApp>()