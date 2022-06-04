import Ice.LocalException_ice
from Ice import *
from Devices import *


class BasicLight:
    def __init__(self, config, communicator: CommunicatorI):
        self.name = config.split('=')[0]
        basic_light_proxy = communicator.propertyToProxy(self.name)
        self.proxy = ILightPrx.checkedCast(basic_light_proxy)
        self.commands = ["getDeviceInfo", "toggleLightColor", "toggleLightIntensity"]
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
