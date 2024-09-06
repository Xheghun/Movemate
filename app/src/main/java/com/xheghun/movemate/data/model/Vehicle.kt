package com.xheghun.movemate.data.model

data class Vehicle(
    val name: String,
    val remark: String
)


fun vehicleList(): List<Vehicle> {
    return buildList {
        for (i in 1..3) {
            add(Vehicle("Ocean Freight", "International"))
            add(Vehicle("Cargo Freight", "Reliable"))
            add(Vehicle("Air Freight", "International"))
        }
    }
}