package edu.umd.mindlab.androidservicetest;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import static com.sun.mail.util.ASCIIUtility.getBytes;
import static org.junit.Assert.*;

/**
 * Created by niratsaini on 12/2/17.
 */

public class ByteArrayDataSourceTest {
    @Test
    public void getContentTypeForNull() {
        byte[] byteArray = null;
        ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(byteArray);
        String contentType = byteArrayDataSource.getContentType();
        assertEquals("application/octet-stream",contentType);
    }

    @Test
    public void getContentTypeForNotNull() {
        byte[] byteArray = null;
        ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(byteArray , "type");
        String contentType = byteArrayDataSource.getContentType();
        assertEquals("type",contentType);
    }

    @Test
    public void getNameTest() {
        byte[] byteArray = null;
        ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(byteArray , "type");
        String name = byteArrayDataSource.getName();
        assertEquals("ByteArrayDataSource",name);
    }

    @Test
    public void getInputStreamTest() throws IOException {
        byte[] byteArray = getBytes("Nirat");
        ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(byteArray , "type");
        InputStream inputStream = byteArrayDataSource.getInputStream();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        assertEquals(readStreamFromInputStreamReader(byteArrayInputStream),readStreamFromInputStreamReader(inputStream));
    }

    @Test(expected = IOException.class)
    public void getOutputStreamTest() throws IOException {
        byte[] byteArray = null;
        ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(byteArray);
        byteArrayDataSource.getOutputStream();
    }

    public String readStreamFromInputStreamReader(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, "UTF-8");
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }
}
