package com.rahulg.jettyapp.models.response;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateUserResponse {

    @SerializedName("UserName")
    private String username;
    private String id;
}
