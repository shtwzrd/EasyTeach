package com.easyTeach.server.network;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.Server;

public class EasyTeachServerComponent extends Component {

    /**
     * Launches the mail server component.
     * 
     * @param args
     *            The arguments.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new EasyTeachServerComponent().start();
    }

    /**
     * Constructor.
     * 
     * @throws Exception
     */
    public EasyTeachServerComponent() throws Exception {
        // Set basic properties
        setName("EasyTeach Server component");
        setDescription("Example for 'Restlet in Action' book");
        setOwner("MBO Technologies");
        setAuthor("Morten, Brandon, Oliver and Tonni");

        getClients().add(Protocol.HTTP);

        // Adds a HTTP server connector
        Server server = getServers().add(Protocol.HTTP, 8111);
        server.getContext().getParameters().set("tracing", "true");

        getDefaultHost().attachDefault(new EasyTeachApplication());

    }
}
