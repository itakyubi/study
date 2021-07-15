package com.wa.demo.threadlocal;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ThreadWrapper
 * 2021-07-15 10:51
 *
 * @author wuao
 */
@Slf4j
public class ThreadWrapper extends HttpServletRequestWrapper {

    private byte[] body;

    public ThreadWrapper(HttpServletRequest request) {
        super(request);
        try {
            body = readBytes(request.getInputStream());
        } catch (IOException e) {
            log.error("BmlRequestWrapper get input stream error.", e);
        }
    }

    private static byte[] readBytes(InputStream in) throws IOException {
        BufferedInputStream bufIn = new BufferedInputStream(in);
        int buffSize = 1024;
        ByteArrayOutputStream out = new ByteArrayOutputStream(buffSize);

        byte[] temp = new byte[buffSize];
        int size;
        while ((size = bufIn.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        bufIn.close();

        return out.toByteArray();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream baIs = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return baIs.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener arg0) {

            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public byte[] getBody() {
        return body;
    }
}
