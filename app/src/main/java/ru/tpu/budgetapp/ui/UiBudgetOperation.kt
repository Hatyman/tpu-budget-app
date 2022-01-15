package ru.tpu.budgetapp.ui

import android.os.Parcel
import android.os.Parcelable
import ru.tpu.budgetapp.api.budget.BudgetOperationDto
import java.time.LocalDate

class UiBudgetOperation() : Parcelable {
    var id: Int = -1
    var date: LocalDate = LocalDate.now()
    var category: UiCategory = UiCategory()
    var value: Double = 0.0
    var title: String = ""

    constructor(
        _id: Int,
        _date: LocalDate,
        _category: UiCategory,
        _value: Double,
        _title: String
    ) : this() {
        id = _id
        date = _date
        category = _category
        value = _value
        title = _title
    }

    constructor(dto: BudgetOperationDto) :this() {
        id = dto.id
        date = dto.date
        category = UiCategory(dto.category)
        value = dto.value
        title = dto.title
    }

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        date = parcel.readSerializable() as LocalDate
        category = parcel.readParcelable(UiCategory::class.java.classLoader) ?: UiCategory()
        value = parcel.readDouble()
        title = parcel.readString() ?: ""
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        out.writeInt(id)
        out.writeSerializable(date)
        out.writeParcelable(category, flags)
        out.writeDouble(value)
        out.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UiBudgetOperation> {
        override fun createFromParcel(parcel: Parcel): UiBudgetOperation {
            return UiBudgetOperation(parcel)
        }

        override fun newArray(size: Int): Array<UiBudgetOperation?> {
            return arrayOfNulls(size)
        }
    }

}