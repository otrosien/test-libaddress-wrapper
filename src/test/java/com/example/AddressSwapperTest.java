package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

import com.google.i18n.addressinput.common.AddressData;
import com.google.i18n.addressinput.common.FormOptions;
import com.google.i18n.addressinput.common.FormatInterpreter;
import com.neovisionaries.i18n.CountryCode;

public class AddressSwapperTest {

    @Test
    public void testSwappedRecipient() {
        AddressData addressData = AddressData.builder()
                .setCountry("US")
                .setLanguageCode("en")
                .setOrganization("Big Data AG")
                .setRecipient("Max Mustermann")
                .setPostalCode("85705")
                .setLocality("TUCSON")
                .setAdminArea("AZ")
                .setAddressLines(Arrays.asList("795 E DRAGRAM"))
                .build();
        AddressData swappedAddress = FormatInterpreterWrapper.swap(addressData);

        assertThat(swappedAddress.getOrganization()).isEqualTo("Max Mustermann");
        assertThat(swappedAddress.getRecipient()).isEqualTo("Big Data AG");
    }

    @Test
    public void testStreetAndHouseNr() {
        AddressData addressData = AddressData.builder()
                .setCountry("DE")
                .setLanguageCode("en")
                .setRecipient("Max Mustermann")
                .setPostalCode("12345")
                .setAddressLines(Arrays.asList(new StreetAndHouseNo(CountryCode.DE).merge("Hauptstr.", "101a")))
                .build();

        assertThat(addressData.getAddressLines()).containsExactly("Hauptstr. 101a");
    }

    @Test
    public void testAddCountryToOutput() {
        AddressData senderData = AddressData.builder()
                .setCountry("US")
                .setLanguageCode("en")
                .setOrganization("Western Union")
                .setRecipient("John G. G. Tucker")
                .setPostalCode("12345")
                .setLocality("TUCSON")
                .setAddressLines(Arrays.asList("223 Town Square"))
                .build();

        AddressData recipientData = AddressData.builder()
                .setCountry("DE")
                .setLanguageCode("de")
                .setOrganization("Big Data AG")
                .setRecipient("Max Mustermann")
                .setPostalCode("20355")
                .setLocality("Hamburg")
                .setAddressLines(Arrays.asList("Wolkengasse 2"))
                .build();

        FormatInterpreter interpreter = new FormatInterpreter(new FormOptions().createSnapshot());
        // recipient equals sender in this case.
        assertThat(new FormatInterpreterWrapper(interpreter).getEnvelopeAddress(senderData, recipientData)).containsExactly(
                "Big Data AG", "Max Mustermann", "Wolkengasse 2", "20355 Hamburg", "Germany"
                );
    }
}
