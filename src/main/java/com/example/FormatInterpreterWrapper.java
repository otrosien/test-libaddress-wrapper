package com.example;

import java.util.List;

import com.google.common.annotations.VisibleForTesting;
import com.google.i18n.addressinput.common.AddressData;
import com.google.i18n.addressinput.common.FormatInterpreter;
import com.neovisionaries.i18n.CountryCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FormatInterpreterWrapper {

    final FormatInterpreter interpreter;

    @VisibleForTesting
    static AddressData swap(AddressData data) {
        return AddressData.builder(data)
                // swap organization and recipient
                .setRecipient(data.getOrganization())
                .setOrganization(data.getRecipient())
                .build();
    }

    public List<String> getEnvelopeAddress(AddressData senderAddress, AddressData recipientAddress) {
        senderAddress = swap(senderAddress);
        recipientAddress = swap(recipientAddress);
        List<String> fields = interpreter.getEnvelopeAddress(recipientAddress);
        if (recipientAddress.getPostalCountry() != null
                && !recipientAddress.getPostalCountry().equals(senderAddress.getPostalCountry())) {
            fields.add(CountryCode.getByCode(recipientAddress.getPostalCountry()).getName());
        }
        return fields;
    }
}
