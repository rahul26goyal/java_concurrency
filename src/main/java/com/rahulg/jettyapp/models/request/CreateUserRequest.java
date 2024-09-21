package com.rahulg.jettyapp.models.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
//@AllArgsConstructor
@Data
public class CreateUserRequest {

    @SerializedName("Name") // name to use used using de/serialization.
    private @NonNull String name;

    private @NonNull String id;
    private String email;
    private String password;
    private @NonNull String phone;
}
