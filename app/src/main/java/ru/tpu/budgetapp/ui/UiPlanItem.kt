package ru.tpu.budgetapp.ui

import android.os.Parcel
import android.os.Parcelable
import ru.tpu.budgetapp.api.plan.PlanItemDto

class UiPlanItem() : Parcelable {
    var id: Int = -1
    var title: String = ""
    var category: UiCategory = UiCategory()
    var value: Double = 0.0

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        title = parcel.readString() ?: ""
        category = parcel.readParcelable(UiCategory::class.java.classLoader)!!
        value = parcel.readDouble()
    }

    constructor(dto: PlanItemDto) : this() {
        id = dto.id
        title = dto.title
        category = UiCategory(dto.category)
        value = dto.value
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeParcelable(category, flags)
        parcel.writeDouble(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UiPlanItem> {
        override fun createFromParcel(parcel: Parcel): UiPlanItem {
            return UiPlanItem(parcel)
        }

        override fun newArray(size: Int): Array<UiPlanItem?> {
            return arrayOfNulls(size)
        }
    }
}