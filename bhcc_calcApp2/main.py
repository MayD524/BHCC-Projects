from math import *
import kivy 
    
kivy.require('2.1.0')

from kivy.app import App
from kivy.uix.gridlayout import GridLayout
from kivy.uix.popup import Popup
from kivy.config import Config

history = []

Config.set('graphics', 'resizable', 1)

class HistoryPopup(Popup):
    def on_open(self):
        self.ids.data_label.text = "\n".join([f'[{i}] {history[i]}' for i in range(len(history))]) if history else "No History"
        
class CalcGridLayout(GridLayout):

    def calculate(self, calculation):
        if calculation:
            try:
                self.display.text = str(eval(calculation))
                history.append(f'{calculation}={self.display.text}')
            except Exception:
                self.display.text = "Error"

class CalculatorApp(App):
    def build(self):
        return CalcGridLayout()

# creating object and running it
calcApp = CalculatorApp()
calcApp.run()