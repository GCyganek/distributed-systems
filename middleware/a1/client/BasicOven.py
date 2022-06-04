from Ice import *
from Devices import *


class BasicOven:
    def __init__(self, config, communicator: CommunicatorI):
        self.name = config.split('=')[0]
        basic_oven_proxy = communicator.propertyToProxy(self.name)
        self.proxy = IOvenPrx.checkedCast(basic_oven_proxy)
        self.commands = ["startOven", "getDeviceInfo"]
        self.modes = {"BOTTOMHEAT": OvenMode.BOTTOMHEAT, "TOPHEAT": OvenMode.TOPHEAT, "CONVECTION": OvenMode.CONVECTION,
                      "ECO": OvenMode.ECO, "GRILL": OvenMode.GRILL, "DEFROST": OvenMode.DEFROST, "ROASTING": OvenMode.ROASTING}
        if not self.proxy:
            print("Object not found for", self.name)
            exit(1)

    def commandHandler(self, command: str):
        if command == "getDeviceInfo":
            info = self.proxy.getDeviceInfo()
            print("Data for device ", self.name, ":")
            print(info)

        elif command == "startOven":
            modes = ""
            for mode in self.modes:
                modes += " " + mode
            print("Choose one mode from ", modes, ":", end="")
            input_mode = input()
            input_minutes = input("Choose time in minutes in range [0, 300]:")
            input_temperature = input("Choose temperature in range [0, 300]:")
            try:
                print(self.proxy.startOven(self.modes[input_mode], int(input_minutes), int(input_temperature)))
            except KeyError:
                print("Invalid mode provided, try again")
            except OutOfRangeException:
                print("Wrong minutes or temperature value provided, try again")
            except UnknownOvenModeException:
                print("This mode is not available for this device type.")