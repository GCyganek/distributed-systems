from Ice import *

import devices_ice
from Devices import *


class ProLight:
    def __init__(self, config, communicator: CommunicatorI):
        self.name = config.split('=')[0]
        pro_light_proxy = communicator.propertyToProxy(self.name)
        self.proxy = IProLightPrx.checkedCast(pro_light_proxy)
        self.commands = ["getDeviceInfo", "toggleLightColor", "toggleLightIntensity", "setLightMode"]
        self.intensities = {"LOW":LightIntensity.LOW, "MEDIUM":LightIntensity.MEDIUM, "HIGH":LightIntensity.HIGH}
        self.colors = {"YELLOW":LightColor.YELLOW, "RED":LightColor.RED, "BLUE":LightColor.BLUE, "GREEN":LightColor.GREEN}
        if not self.proxy:
            print("Object not found for ", self.name)
            exit(1)

    def commandHandler(self, command: str):
        if command == "getDeviceInfo":
            info = self.proxy.getDeviceInfo()
            print("Data for device ", self.name, ":")
            print(info)

        elif command == "toggleLightColor":
            print(self.proxy.toggleLightColor())

        elif command == "toggleLightIntensity":
            print(self.proxy.toggleLightIntensity())

        elif command == "setLightMode":
            colors = ""
            for color in self.colors:
                colors += color + " "
            print("Choose one color from ", colors, ": ", end="")
            input_color = input()

            intensities = ""
            for intensity in self.intensities:
                intensities += intensity + " "
            print("Choose one intensity from ", intensities, ": ", end="")
            input_intensity = input()

            try:
                print(self.proxy.setLightMode(self.colors[input_color], self.intensities[input_intensity]))
            except KeyError:
                print("Wrong values provided, try again")
            except UnknownLightColor:
                print("Wrong light color entered, try again")
            except UnknownLightIntensity:
                print("Wrong light intensity entered, try again")