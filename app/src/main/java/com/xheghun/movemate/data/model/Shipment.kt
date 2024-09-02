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
    val status: Status
)

