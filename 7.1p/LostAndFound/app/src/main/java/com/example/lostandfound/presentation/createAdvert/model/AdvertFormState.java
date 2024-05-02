package com.example.lostandfound.presentation.createAdvert.model;

import java.util.Date;

public class AdvertFormState {
    public String type = "Lost";
    public String name = "";
    public String nameError = null;
    public String phone = "";
    public String phoneError = null;
    public String description = "";
    public String descriptionError = null;
    public Date date = new Date();
    public String dateError = null;
    public String location = "";
    public String locationError = null;
}
