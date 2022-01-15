package ru.tpu.budgetapp.ui

import android.os.Parcel
import android.os.Parcelable
import ru.tpu.budgetapp.api.budget.BudgetStateDto

class UiBudgetState() : Parcelable {
    var value: Double = 0.0

    constructor(parcel: Parcel) : this() {
        value = parcel.readDouble()
    }

    constructor(dto: BudgetStateDto) : this() {
        value = dto.value
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UiBudgetState> {
        override fun createFromParcel(parcel: Parcel): UiBudgetState {
            return UiBudgetState(parcel)
        }

        override fun newArray(size: Int): Array<UiBudgetState?> {
            return arrayOfNulls(size)
        }
    }
}