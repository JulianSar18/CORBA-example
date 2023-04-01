import CalculatorApp.Calculator;
import CalculatorApp.CalculatorHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            System.out.println(orb);

            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            Calculator href = CalculatorHelper.narrow(ncRef.resolve_str("ECHO-SERVER"));
            boolean exit = false;
            do{
                System.out.println("1. Calcular Cuotas");
                System.out.println("2. Salir");
                int opcion = scan.nextInt();
                switch (opcion){
                    case 1:
                        System.out.println("Ingrese valor del credito ");
                        double purchase = scan.nextDouble();
                        System.out.println("Ingrese a cuantas cuotas ");
                        double monthlyFee = scan.nextDouble();
                        String CalcMonthlyFee = href.calcMonthlyFee(purchase, monthlyFee);
                        System.out.println("El valor de la cuota mensual es de " + CalcMonthlyFee);
                        break;
                    case 2:
                        exit = true;
                        break;
                }
            }while (!exit);

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
