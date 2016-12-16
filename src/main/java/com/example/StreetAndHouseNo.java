package com.example;

import com.google.common.base.Strings;
import com.neovisionaries.i18n.CountryCode;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StreetAndHouseNo {

    @NonNull
    private final CountryCode country;

    public String merge(String street, String houseNo) {
        String merged = Strings.nullToEmpty(street) + " " + Strings.nullToEmpty(houseNo);
        return merged.trim();
    }
}
