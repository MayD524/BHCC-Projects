from math import log10 as log, log1p as ln, sqrt, exp
import kivy 
import re
    


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
        
class CalcLayout(GridLayout):

    def calculate(self, calculation):
        if calculation:
            try:
                self.display.text = str(eval(calculation))
                data = f'{calculation}={self.display.text}'
                if data not in history:
                    history.append(data)
            except Exception:
                self.display.text = "Error"
    
    def lastNumber(self) -> str:
        dt = self.display.text
        x = re.findall(r'\d+', dt)
        if x:
            return x[-1]
        return None
    
    def addSqrt(self) -> None:
        dt = self.lastNumber()
        if dt == None:
            return
        self.display.text = self.display.text.replace(dt, f"sqrt({dt})")
        
    def power(self) -> None:
        self.dispaly.text += "**"
    
    def square(self) -> None:
        self.display.text += "**2"
    
    def log(self) -> None:
        dt = self.lastNumber()
        if dt == None:
            return
        self.display.text = self.display.text.replace(dt, f"log({dt})")
    
    def nthRoot(self) -> None:
        dt = self.lastNumber()
        if dt == None:
            return
        self.display.text = self.display.text.replace(dt, f"(1/{dt})") 
    
    def ln(self) -> None:
        dt = self.lastNumber()
        if dt == None:
            return
        self.display.text = self.display.text.replace(dt, f"ln({dt})")

class CalculatorApp(App):
    def build(self):
        return CalcLayout()

# creating object and running it
calcApp = CalculatorApp()
calcApp.run()