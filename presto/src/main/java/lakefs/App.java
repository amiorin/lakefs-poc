package lakefs;

import com.amazonaws.auth.SignerFactory;
import io.prestosql.plugin.hive.s3.PrestoS3FileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.fs.s3a.TemporaryAWSCredentialsProvider;

import java.net.URI;

import static io.prestosql.plugin.hive.s3.PrestoS3FileSystem.*;

public class App {
    public static void main(String[] args) throws Exception {
        String uri = "s3://zalando-saiki-datalake-eu-central-1-dev/";
        SignerFactory.registerSigner("LakefsSignerType", LakefsSigner.class);
        Configuration config = new Configuration();
        config.set(S3_CREDENTIALS_PROVIDER, TemporaryAWSCredentialsProvider.class.getName());
        config.setBoolean(S3_USE_INSTANCE_CREDENTIALS, false);
        config.set("fs.s3a.access.key", "FIXME");
        config.set("fs.s3a.secret.key", "FIXME");
        config.set("fs.s3a.session.token", "FIXME");
        config.set(S3_SIGNER_TYPE, "LakefsSignerType");
        config.set(S3_ENDPOINT, "s3.eu-central-1.amazonaws.com");

        try (PrestoS3FileSystem fs = new PrestoS3FileSystem()) {
            fs.initialize(new URI(uri), config);
            RemoteIterator<LocatedFileStatus> fileStatusListIterator = fs.listFiles(new Path("/"), true);
            while (fileStatusListIterator.hasNext()) {
                LocatedFileStatus fileStatus = fileStatusListIterator.next();
                final Path path = fileStatus.getPath();
                System.out.println(path);
            }
        }
    }
}
