package dev.jlibra.admissioncontrol.transaction;

import static dev.jlibra.serialization.CanonicalSerialization.join;

import org.bouncycastle.util.encoders.Hex;

import dev.jlibra.serialization.CanonicalSerialization;

public class AccountAddressArgument implements TransactionArgument {

    private byte[] address;

    private static final byte[] PREFIX = Hex.decode("01000000");

    public AccountAddressArgument(byte[] address) {
        this.address = address;
    }

    @Override
    public byte[] serialize() {
        return join(PREFIX, CanonicalSerialization.serializeByteArray(address));
    }

    @Override
    public Type type() {
        return Type.ADDRESS;
    }
}
