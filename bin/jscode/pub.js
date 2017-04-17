var swingNames = JavaImporter();    
swingNames.importPackage(Packages.java.lang);   
swingNames.importPackage(Packages.javacode);   

with (swingNames) {   
 System.out.println("--- Here is MQTTpub --- \n");
// user does
 client = new MQTTpub();  
 client.start();
 
}    
 