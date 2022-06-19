package mypack;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

public class Test1
{
	public static void main(String[] args)
	{
		//Virtual contact is "+1 2722826818"
		//connect Twilio cloud for SMS service
		String asid="ACc417fc5ea68009fd2301efd7846278a3"; //constant
		String auth="744394a204a62b97c5a500c17a1f5c33"; //constant
		Twilio.init(asid,auth);
		//Get all messages
        ResourceSet<Message> messages=Message.reader().read();
        for(Message record:messages)
        {
            System.out.println(record.getFrom()+"<-->"+record.getBody());
        }
        //Get 3rd message(ex: "120543 is your otp")
        ResourceSet<Message> ms=Message.reader().read();//collect all messages(sequentially)
        ms.iterator().next();
        ms.iterator().next();
        String temp=ms.iterator().next().getBody();
        String otp=temp.replaceAll("[^0-9]",""); //remove other than digits
        System.out.println(otp);
	}
}



