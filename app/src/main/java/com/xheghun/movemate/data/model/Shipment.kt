package com.xheghun.movemate.data.model


enum class Status {
    IN_PROGRESS,
    PENDING,
    LOADING,
    Completed
}

data class Shipment(
    val shipmentNumber: String,
    val sender: String,
    val receiver: String,
    val time: String,
    val status: Status,
    val itemName: String
)


fun main() {
    println(Status.IN_PROGRESS.name.lowercase().capitalize())
}

fun dummyShipments(): List<Shipment> {

    return buildList {
        for (i in 1..5) {
            add(
                Shipment(
                    "NAXE262671718",
                    "Spain",
                    "Germany",
                    "5 days ago",
                    status = Status.LOADING,
                    "Macbook Pro"
                )
            )
            add(
                Shipment(
                    "NAXE262671718",
                    "Spain",
                    "Germany",
                    "5 days ago",
                    status = Status.PENDING,
                    "Macbook Pro"
                )
            )
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

            add(
                Shipment(
                    "NAXE262671718",
                    "Spain",
                    "Germany",
                    "5 days ago",
                    status = Status.Completed,
                    "Macbook Pro"
                )
            )
        }
    }
}

fun getStatusItemCount(status: String): Int {
    //TODO("Find a scalable solution to this filter")
    return dummyShipments().filter {
        if (status.lowercase() == "all")
            true
        else
            it.status.name.lowercase().replace("_", " ").capitalize() == status
    }.size
}