# Mountain trip app

Before running any of the clients install [Erlang](https://www.erlang.org/downloads) and
[RabbitMQ](https://www.rabbitmq.com/download.html), then run the RabbitMQ starting service on your pc

Group client can receive items to order on input.
Each is sent as an order to queue named as the name of the item ordered.
Orders are processed by active suppliers if they have requested items on sale.
After processing an order, an acknowledgement is sent back to the client.
Admin can send messages to every supplier, every group or all other clients in the system.
Admin can intercept all messages sent between suppliers and clients.

Enter `exit` to close the client.

## Running the clients

- Admin

```bash
    gradle run -DclassToExecute=gcyganek.app.admin.AdminMain --args="host" --console=plain
```

- Supplier

```bash
    gradle run -DclassToExecute=gcyganek.app.supplier.SupplierMain --args="host supplierName item1 item2..." --console=plain
```

- Group

```bash
    gradle run -DclassToExecute=gcyganek.app.group.GroupMain --args="host groupName" --console=plain
```

Put `classToExecute` system property into `''` if there is an error while running the client