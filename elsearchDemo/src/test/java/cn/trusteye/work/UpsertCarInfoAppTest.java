package cn.trusteye.work;

import org.elasticsearch.client.transport.TransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class UpsertCarInfoAppTest {

    TransportClient client;
    @Before
    public void setUp() throws Exception {
        client = UpsertCarInfoApp.getTransportClient();
    }

    @After
    public void tearDown() throws Exception {
        client.close();
    }

    @Test
    public void testCreatePostDocument() throws IOException {
       UpsertCarInfoApp.createPostDocument(client);
    }

    @Test
    public void testQueryPostDocument() {
        UpsertCarInfoApp.getPostDocument(client);
    }

}