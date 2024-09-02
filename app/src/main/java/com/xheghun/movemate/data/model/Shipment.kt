package com.xheghun.movemate.data.model


enum class Status {
    IN_PROGRESS,
    PENDING,
    LOADING
}

data class Shipment(
    val shipmentNumber: String,
    val sender: String,
    val receiver: String,
    val time: String,
    val status: Status,
    val itemName: String
)

fun dummyShipments(): List<Shipment> {

    return buildList {
        for (i in 1..5) {
            add(
                Shipment(
                    "NAXE262671718",
                    "Spain",
                    "Germany",
                    "5 days ago",
                    status = Status.IN_PROGRESS,
                    "Macbook Pro"
                )
            )
        }
    }
}