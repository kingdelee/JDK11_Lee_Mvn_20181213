package C6_Task_Execution.support;

import support.TimeUtil;

import java.io.*;
import java.net.Socket;

public final class RequestHandler {

    private RequestHandler() {
    }

    public static void handleRequest(Socket connection) throws IOException {
        try {
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            OutputStream os = connection.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            String info = null;
            while (!((info = br.readLine()) == null)) {
                System.out.println(TimeUtil.defaultNow() + " [" + Thread.currentThread().getId()
                        + "] server receives a client msg: '" + info + "'");
            }
            String reply = "welcome, the server id is " + Thread.currentThread().getId();
            System.out.println(TimeUtil.defaultNow() + " [" + Thread.currentThread().getId()
                    + "] server sends a reply: '" + reply + "'");
            pw.write(reply);
            pw.flush();

            pw.close();
            os.close();
            br.close();
            is.close();
        } finally {
            connection.close();
        }
    }
}
