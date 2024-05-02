package com.example.lostandfound.presentation.createAdvert.model;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.lostandfound.domain.use_case.DefaultValues;

import java.util.Date;

public class AdvertFormState {
    public MutableLiveData<String> type
            = new MutableLiveData<>(DefaultValues.getAdvertFormString());
    public String name
            = "Hello";
    @Nullable
    public MutableLiveData<String> nameError
            = new MutableLiveData<>(null);
    public MutableLiveData<String> phone
            = new MutableLiveData<>(DefaultValues.getAdvertFormString());

    @Nullable
    public MutableLiveData<String> phoneError
            = new MutableLiveData<>(null);
    public String description
            = DefaultValues.getAdvertFormString();

    @Nullable
    public MutableLiveData<String> descriptionError
            = new MutableLiveData<>(null);
    public MutableLiveData<Date> date
            = new MutableLiveData<>(new Date());

    @Nullable
    public MutableLiveData<String> dateError
            = new MutableLiveData<>(null);
    public MutableLiveData<String> location
            = new MutableLiveData<>(DefaultValues.getAdvertFormString());

    @Nullable
    public MutableLiveData<String> locationError
            = new MutableLiveData<>(null);

    public MutableLiveData<String> getType() {
        return type;
    }

    public void setType(MutableLiveData<String> type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public MutableLiveData<String> getNameError() {
        return nameError;
    }

    public void setNameError(@Nullable MutableLiveData<String> nameError) {
        this.nameError = nameError;
    }

    public MutableLiveData<String> getPhone() {
        return phone;
    }

    public void setPhone(MutableLiveData<String> phone) {
        this.phone = phone;
    }

    @Nullable
    public MutableLiveData<String> getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(@Nullable MutableLiveData<String> phoneError) {
        this.phoneError = phoneError;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Nullable
    public MutableLiveData<String> getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(@Nullable MutableLiveData<String> descriptionError) {
        this.descriptionError = descriptionError;
    }

    public MutableLiveData<Date> getDate() {
        return date;
    }

    public void setDate(MutableLiveData<Date> date) {
        this.date = date;
    }

    @Nullable
    public MutableLiveData<String> getDateError() {
        return dateError;
    }

    public void setDateError(@Nullable MutableLiveData<String> dateError) {
        this.dateError = dateError;
    }

    public MutableLiveData<String> getLocation() {
        return location;
    }

    public void setLocation(MutableLiveData<String> location) {
        this.location = location;
    }

    @Nullable
    public MutableLiveData<String> getLocationError() {
        return locationError;
    }

    public void setLocationError(@Nullable MutableLiveData<String> locationError) {
        this.locationError = locationError;
    }
}
