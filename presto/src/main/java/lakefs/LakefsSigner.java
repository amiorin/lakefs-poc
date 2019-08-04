package lakefs;

import com.amazonaws.SignableRequest;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.Signer;
import com.amazonaws.services.s3.internal.AWSS3V4Signer;

public class LakefsSigner implements Signer {


    private final AWSS3V4Signer signer;

    public LakefsSigner() {
        this.signer = new AWSS3V4Signer();
        signer.setServiceName("s3");
    }

    @Override
    public void sign(SignableRequest<?> signableRequest, AWSCredentials awsCredentials) {
        this.signer.sign(signableRequest, awsCredentials);
    }
}