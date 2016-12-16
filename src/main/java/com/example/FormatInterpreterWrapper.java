package com.example;

import java.util.List;

import com.google.i18n.addressinput.common.AddressData;
import com.google.i18n.addressinput.common.FormatInterpreter;
import com.neovisionaries.i18n.CountryCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FormatInterpreterWrapper {

    final FormatInterpreter interpreter;

    public List<String> getEnvelopeAddress(AddressData senderAddress, AddressData recipientAddress) {
        List<String> fields = interpreter.getEnvelopeAddress(recipientAddress);
        if (recipientAddress.getPostalCountry() != null
                && !recipientAddress.getPostalCountry().equals(senderAddress.getPostalCountry())) {
            fields.add(CountryCode.getByCode(recipientAddress.getPostalCountry()).getName());
        }
        return fields;
    }
}
