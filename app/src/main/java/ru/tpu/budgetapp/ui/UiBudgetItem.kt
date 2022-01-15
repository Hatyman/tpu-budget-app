package ru.tpu.budgetapp.ui

import android.os.Parcel
import android.os.Parcelable
import ru.tpu.budgetapp.api.budget.BudgetItemDto

class UiBudgetItem(): Parcelable {
    var id: Int = -1
    var title: String = ""
    var category: UiCategory = UiCategory()

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        title = parcel.readString() ?: ""
        category = parcel.readParcelable(UiCategory::class.java.classLoader)!!
    }

    constructor(dto: BudgetItemDto): this() {
        id = dto.id
        title = dto.title
        category = UiCategory(dto.category)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeParcelable(category, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UiBudgetItem> {
        override fun createFromParcel(parcel: Parcel): UiBudgetItem {
            return UiBudgetItem(parcel)
        }

        override fun newArray(size: Int): Array<UiBudgetItem?> {
            return arrayOfNulls(size)
        }
    }
}
