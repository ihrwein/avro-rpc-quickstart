/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import example.client.AbstractClient;
import example.server.IServer;
import org.apache.avro.ipc.specific.SpecificRequestor;
import org.apache.avro.util.Utf8;

import example.proto.Mail;
import example.proto.Message;

/**
 * Start a server, attach a client, and send a message.
 */
public class Main {

    public static int MESSAGE_NUMBER_TO_SEND = 1000;

    public static void main(String[] args) throws IOException {

        String[] implementations = {"netty", "http", "json"};
        List<String> results = new ArrayList<String>();

        for (String implementation:implementations) {
            System.out.println("Starting test of " + implementation);
            IServer server;
            AbstractClient client;
            int port = TransportAdapterFactory.DEFAULT_PORT;

            server = TransportAdapterFactory.createServer(implementation, port);

            client =  TransportAdapterFactory.createClient(implementation, port);

            for (int size = 32; size <= 16384; size *= 2)  {
                Object message = MessageFactory.createMessage(implementation, size);

                long startTime = System.nanoTime();
                for (int i = 0; i < MESSAGE_NUMBER_TO_SEND; i++) {
                    client.send(message);
                }
                long elapsedTime = System.nanoTime() - startTime;
                String result = String.format("%s,%s,%s,%s,%s", size, implementation, MESSAGE_NUMBER_TO_SEND, elapsedTime, 1);
                results.add(result);
            }

            client.close();
            server.close();
            port++;
            System.out.println("Test of " + implementation +  " ended");
        }

        for (String result: results)
            System.out.println(result);

    }
}
