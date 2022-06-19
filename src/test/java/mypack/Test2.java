package mypack;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

public class Test2
{
	public static void main(String[] args)
	{
		//connect Twilio cloud for SMS service
		String asid="ACc417fc5ea68009fd2301efd7846278a3"; //constant
		String auth="744394a204a62b97c5a500c17a1f5c33"; //constant
		Twilio.init(asid,auth);
        ResourceSet<Message> messages=Message.reader().read();
        for(Message record:messages)
        {
            System.out.println(record.getFrom()+"<-->"+record.getBody());
        }
        ResourceSet<Message> ms=Message.reader().read();
        String temp=ms.iterator().next().getBody(); //goto 1st or latest message body
        String[] pieces=temp.split(" "); //split that body using space into pieces
        String otp=pieces[0]; //1st piece is otp number received from Amazon
        System.out.println(otp);
	}
}



