package ru.tpu.budgetapp.ui

import android.os.Parcel
import android.os.Parcelable
import ru.tpu.budgetapp.api.plan.PlanDto
import java.time.LocalDate

class UiPlan() : Parcelable {
    var id: Int = -1
    var startDate: LocalDate = LocalDate.now()
    var accuracy: Double = 0.0
    var endDate: LocalDate = LocalDate.now()

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        startDate = parcel.readSerializable() as LocalDate
        accuracy = parcel.readDouble()
        endDate = parcel.readSerializable() as LocalDate
    }

    constructor(dto: PlanDto) : this() {
        id = dto.id
        startDate = dto.startDate
        accuracy = dto.accuracy
        endDate = dto.endDate
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeSerializable(startDate)
        parcel.writeDouble(accuracy)
        parcel.writeSerializable(endDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UiPlan> {
        override fun createFromParcel(parcel: Parcel): UiPlan {
            return UiPlan(parcel)
        }

        override fun newArray(size: Int): Array<UiPlan?> {
            return arrayOfNulls(size)
        }
    }

}