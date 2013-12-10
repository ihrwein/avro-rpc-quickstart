package example.client;

import example.proto.Mail;
import org.apache.avro.ipc.HttpTransceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClient extends AvroClient {

    public HttpClient(int port) {
        this(DEFAULT_ADDRESS, port);
    }

    public HttpClient(String address, int port) {
        super(address, port);

        try {
            HttpTransceiver delegate = new HttpTransceiver(new URL(String.format("http://%s:%s",address, port)));
            setDelegate(delegate);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
