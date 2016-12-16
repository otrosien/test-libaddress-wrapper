package com.example;

import com.google.i18n.addressinput.common.AddressData;
import com.neovisionaries.i18n.CountryCode;

public class AddressWrapper {

    public static String streetAndHouseNr(CountryCode country, String street, String houseNr) {
        return street + " " + houseNr;
    }

    public static AddressData wrap(AddressData data) {
        return AddressData.builder(data)
                // swap organization and recipient
                .setRecipient(data.getOrganization())
                .setOrganization(data.getRecipient())
                .build();
    }

}
