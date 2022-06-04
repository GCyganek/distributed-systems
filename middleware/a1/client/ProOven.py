from Ice import *
from Devices import *


class ProOven:
    def __init__(self, config, communicator: CommunicatorI):
        self.name = config.split('=')[0]
        pro_oven_proxy = communicator.propertyToProxy(self.name)
        self.proxy = IProOvenPrx.checkedCast(pro_oven_proxy)
        self.commands = ["startOven", "getDeviceInfo", "displayRecipe"]
        self.modes = {"BOTTOMHEAT": OvenMode.BOTTOMHEAT, "TOPHEAT": OvenMode.TOPHEAT, "CONVECTION": OvenMode.CONVECTION,
                      "ECO": OvenMode.ECO, "GRILL": OvenMode.GRILL, "DEFROST": OvenMode.DEFROST, "ROASTING": OvenMode.ROASTING}
        self.recipes = {"LEMONCAKE": Recipe.LEMONCAKE, "CARROTCAKE": Recipe.CARROTCAKE, "CHOCOLATECAKE": Recipe.CHOCOLATECAKE}
        if not self.proxy:
            print("Object not found for ", self.name)
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
            input_minutes = input("Choose time in minutes in range [0, 500]:")
            input_temperature = input("Choose temperature in range [0, 500]:")
            try:
                print(self.proxy.startOven(self.modes[input_mode], int(input_minutes), int(input_temperature)))
            except KeyError:
                print("Invalid mode provided, try again")
            except OutOfRangeException:
                print("Wrong minutes or temperature value provided, try again")
            except UnknownOvenModeException:
                print("This mode is not available for this device type.")

        elif command == "displayRecipe":
            recipes = ""
            for recipe in self.recipes:
                recipes += " " + recipe
            print("Choose one recipe from", recipes, ": ", end="")
            input_recipe = input()
            try:
                print(self.proxy.displayRecipe(self.recipes[input_recipe]))
            except UnknownRecipeException:
                print("This recipe is not available for this device")
            except KeyError:
                print("Invalid recipe provided, try again")