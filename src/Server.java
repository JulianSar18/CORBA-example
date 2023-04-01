import CalculatorApp.Calculator;
import CalculatorApp.CalculatorHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class Server {
    public static void main(String[] args) {
        try {

            ORB orb = ORB.init(args, null);

            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();


            EchoServer server = new EchoServer();

            org.omg.CORBA.Object ref = rootPOA.servant_to_reference(server);

            Calculator href = CalculatorHelper.narrow(ref);

            org.omg.CORBA.Object objRef =  orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent path[] = ncRef.to_name( "ECHO-SERVER" );
            ncRef.rebind(path, href);

            System.out.println("Server ready and waiting ...");

            orb.run();

        }catch (Exception e){
            System.err.println("Error: "  + e.getMessage());
            e.printStackTrace(System.out);
        }

        System.out.println("Exiting ...");
    }
}
