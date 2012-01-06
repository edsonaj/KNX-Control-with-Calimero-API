
/** KNX CONTROL WITH CALIMERO API 1.4
 *  Author: Edson Alves Junior (@edsonaj)
 *  The Calimero API is not mine, I'm just using it.
 */


import java.net.InetSocketAddress;
import tuwien.auto.eibxlator.*;
import tuwien.auto.eicl.*;
import tuwien.auto.eicl.util.*;
import tuwien.auto.eicl.struct.cemi.*;
import tuwien.auto.eicl.struct.eibnetip.util.*;
import tuwien.auto.eibxlator.PointPDUXlator.*;
import tuwien.auto.eibxlator.PointPDUXlator_Boolean.*;


public class Main {
    public static CEMI_Connection tunnel;
     public static void main(String[] args) {
         
            /** Declaration of some variables
             *  I don' know why, but it's Enalbe and not Enable 
             */
         
            String Enable = "Enalbe";
            String Disable = "Disable";
            String Address = "1/5/217";
            
		
            try {
 
                
            /** Create a new connection using CEMI_Connection
             *  to KNXnet/IP interface (in this case the ip
             *  192.168.1.3), with the default port.
             */
            
            tunnel = new CEMI_Connection(   
                new InetSocketAddress("192.168.1.3",        
                    EIBNETIP_Constants.EIBNETIP_PORT_NUMBER), 
                new TunnellingConnectionType());
  
            /** Just saying that it connected. In case of an error,
             * this is not executed, so you know it was unable to connect
             */
            
            System.out.println("Connected.");
            
            /** Create the knx device (a port from a relay for example)
             *  and declare it as a boolean device, and it's a Enable/Disable
             *  device. In the API docs there are all kinds avaliable
             */
      
           
            PointPDUXlator object = PDUXlatorList.getPointPDUXlator(
                PDUXlatorList.TYPE_BOOLEAN[0],
                PointPDUXlator_Boolean.DPT_ENABLE[0]);
            
            /** Declare that we are going to write some info in the "Address"
             *  and this info is writed in the string Enable. To disable just 
             *  change the variable
             */
            
            object.setServiceType(PointPDUXlator.A_GROUPVALUE_WRITE);
            object.setASDUfromString(Enable);
           
            /** Create the message to be sent to device
             *  The device KNX address was declared in the begining
             */
            
            CEMI_L_DATA message = new CEMI_L_DATA(
            CEMI_L_DATA.MC_L_DATAREQ,
            new EIB_Address(),
            new EIB_Address(Address),
            object.getAPDUByteArray());
            
            /** Send the message using the connection "tunnel"
             */

            tunnel.sendFrame(message, CEMI_Connection.WAIT_FOR_CONFIRM);
            
            /** Print that the message was sent
             */
            
            System.out.println("Message Sent to " + Address + ": " + Enable);
            
            /** Disconnect from the device and print it to the screen
             *  If your device can handle more than 1 Tunneling connection you
             *  can let it connected, but in my case I use different devices
             *  to control it, so I always have to disconnect after doing
             *  anything
             */
            
            tunnel.disconnect(null);  
            System.out.println("Disconnected.");
        }
        catch (EICLException ex) { }  // connection error
}
}
