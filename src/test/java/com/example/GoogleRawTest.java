package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

import com.google.i18n.addressinput.common.AddressData;
import com.google.i18n.addressinput.common.FormOptions;
import com.google.i18n.addressinput.common.FormatInterpreter;

public class GoogleRawTest {
    
    @Test
    public void test() {
        AddressData addressData = AddressData.builder()
                .setCountry("US")
                .setLanguageCode("en")
                .setOrganization("ePages GmbH")
                .setRecipient("Bastian Klein")
                .setPostalCode("85705")
                .setLocality("TUCSON")
                .setAdminArea("AZ")
                .setAddressLines(Arrays.asList("795 E DRAGRAM"))
                .build();
        FormatInterpreter interpreter = new FormatInterpreter(new FormOptions().createSnapshot());
        assertThat(interpreter.getEnvelopeAddress(addressData)).containsExactly(
                "Bastian Klein", "ePages GmbH", "795 E DRAGRAM", "TUCSON, AZ 85705"
                );
    }

}
