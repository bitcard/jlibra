package dev.jlibra;

import static org.bouncycastle.util.encoders.Hex.encode;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertThat;

import java.security.Security;
import java.util.List;

import org.bouncycastle.util.encoders.Hex;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.protobuf.ByteString;

import dev.jlibra.admissioncontrol.query.AccountData;
import dev.jlibra.admissioncontrol.transaction.ImmutableTransaction;
import types.AccountStateBlobOuterClass.AccountStateBlob;
import types.AccountStateBlobOuterClass.AccountStateWithProof;
import types.GetWithProof.GetAccountStateResponse;

public class LibraHelperTest {

    private static final String PRIVATE_KEY_HEX = "3051020101300506032b6570042204202b1115484c64c297179d4ec8aa660f09eeae900a1ba6f16423f82869a101c8e98121002e00f50d1ba024895c72a92cee1310dfafefcc826629c266a4c80b914772f82d";
    private static final String ACCOUNT_STATE_HEX = "010000002100000001217da6c6b3e19f1825cfb2676daecce3bf3de03cf26647c78df00b371b25cc9745000000200000006674633c78e2e00c69fd6e027aa6d1db2abc2a6c80d78a3e129eaf33dd49ce1ca8056b3b0000000000010000000000000003000000000000000300000000000000";

    @BeforeClass
    public static void setUpClass() {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    @Test
    public void testSignTransaction() {
        // PrivateKey privateKey = KeyUtils.privateKeyFromHexString(PRIVATE_KEY_HEX);
        String signature = new String(
                encode(LibraHelper.signTransaction(ImmutableTransaction.builder().build(), null)));

        assertThat(signature, is(
                "68b31901fd58bf7dfe9a66b9c57f40e4d26f4e20dff3a30895cadc9180f85f086c9911caefb70e5670801b96fe6ca1d1a14f456209df981520308389151d840d"));
    }

    @Test
    public void testReadAccountStates() {
        List<AccountData> accountStates = LibraHelper
                .readAccountStates(GetAccountStateResponse.newBuilder().setAccountStateWithProof(AccountStateWithProof
                        .newBuilder().setBlob(
                                AccountStateBlob.newBuilder()
                                        .setBlob(ByteString.copyFrom(Hex.decode(ACCOUNT_STATE_HEX.getBytes())))
                                        .build())
                        .build()).build());

        assertThat(accountStates, is(iterableWithSize(1)));
        assertThat(new String(encode(accountStates.get(0).getAccountAddress())),
                is("6674633c78e2e00c69fd6e027aa6d1db2abc2a6c80d78a3e129eaf33dd49ce1c"));
        assertThat(accountStates.get(0).getBalanceInMicroLibras(), is(996869544L));
        assertThat(accountStates.get(0).getReceivedEvents(), is(1L));
        assertThat(accountStates.get(0).getSentEvents(), is(3L));
        assertThat(accountStates.get(0).getSequenceNumber(), is(3L));
        assertThat(accountStates.get(0).getDelegatedWithdrawalCapability(), is(false));
    }
}
