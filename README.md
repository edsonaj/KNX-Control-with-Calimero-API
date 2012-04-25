KNX Control
===========

About
-----
This JAVA program let you control any KNX device (ON or OFF) in the BUS from the computer using a KNXnet/IP Tunneling Device and the Calimero API.

Set-up Instructions
-------------------

To make this work, you have to make some changes:

1. Change the IP address of the KNXnet/IP device in line 40 of the Main class:
	new InetSocketAddress("192.168.1.3",
2. Change the BUS Address of the device you want to control in line 28 of the Main class:
	String Address = "1/5/217";
3. If you want to turn ON (Enable) the device, change the line 66 to:
	object.setASDUfromString(Enable);
4. If you want to turn OFF (Disable) the device, change the line 66 to:
	object.setASDUfromString(Disable);

**Observation**

Use ETS Software to change the IP Address of the KNXnet/IP device to a static one, or grab it's MAC Address and create a Reservation in your DHCP Server.

