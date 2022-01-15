package ru.tpu.budgetapp.ui

import android.os.Parcel
import android.os.Parcelable
import ru.tpu.budgetapp.api.category.CategoryDto

class UiCategory() : Parcelable {
    var id: Int = -1
    var title: String = ""
    var isIncome: Boolean = false

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        title = parcel.readString() ?: ""
        isIncome = parcel.readString() == "true"
    }

    constructor(dto: CategoryDto) : this() {
        id = dto.id
        title = dto.title
        isIncome = dto.isIncome
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        out.writeInt(id)
        out.writeString(title)
        out.writeString(isIncome.toString())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UiCategory> {
        override fun createFromParcel(parcel: Parcel): UiCategory {
            return UiCategory(parcel)
        }

        override fun newArray(size: Int): Array<UiCategory?> {
            return arrayOfNulls(size)
        }
    }
}
