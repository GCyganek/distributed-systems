import Ice
from BasicLight import BasicLight
from ProLight import ProLight
from BasicOven import BasicOven
from ProOven import ProOven

config = "config.client"


def getDeviceFromConfig(device_config: str, communicator):
    if device_config.startswith("basicLight"):
        return BasicLight(device_config, communicator)
    elif device_config.startswith("proLight"):
        return ProLight(device_config, communicator)
    elif device_config.startswith("basicOven"):
        return BasicOven(device_config, communicator)
    elif device_config.startswith("proOven"):
        return ProOven(device_config, communicator)
    else:
        raise ValueError("Unknown device: ", device_config)


def devicesFromConfig(communicator):
    devices = dict()

    with open(config) as config_file:
        for line in config_file:
            if line.startswith("# DEVICES_END"):
                break
            if line == "\n":
                continue

            device_config = line

            device = getDeviceFromConfig(device_config, communicator)
            devices[device.name] = device
    return devices


def printLoadedDevices(devices: dict):
    if len(devices) == 0:
        print("No devices found")
        exit(0)

    print("Loaded devices:")

    for device_name in devices.keys():
        print("\t-", device_name)


def displayAvailableDeviceCommands(commands: list):
    print(" ====== Available commands: ====== ")
    for command in commands:
        print("\t-", command)


def run(devices: dict):
    while True:
        selected_device = input(">")
        if selected_device == "q":
            exit(0)
        if selected_device not in devices:
            print("Unknown device, please choose one from the list below.")
            printLoadedDevices(devices)
            continue

        selected_device = devices[selected_device]
        displayAvailableDeviceCommands(selected_device.commands)

        while True:
            print()
            print(selected_device.name, end="")
            selected_command = input(">")
            if selected_command == "q":
                print("Leaving device ", selected_device.name)
                break
            elif selected_command not in selected_device.commands:
                print("\nUnknown command for ", selected_device.name, end="\n\n")
                displayAvailableDeviceCommands(selected_device.commands)
                continue

            selected_device.commandHandler(selected_command)


if __name__ == "__main__":
    with Ice.initialize(config) as communicator:
        devices = devicesFromConfig(communicator)
        printLoadedDevices(devices)
        run(devices)
