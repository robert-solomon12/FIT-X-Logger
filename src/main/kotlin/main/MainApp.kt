package main

import tornadofx.App
import tornadofx.launch
import views.WelcomeUI

class MainApp : App(WelcomeUI::class)

fun main() = launch<MainApp>()