package pl.pablo.eiger;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Creator;

public class Receiver extends UntypedActor {
    
    public static Props props() {
        return Props.create(new Creator<Receiver>() {
            private static final long serialVersionUID = 1L;
 
            @Override
            public Receiver create() throws Exception {
                return new Receiver();
            }
        });
    }
    
    private final LoggingAdapter la_log = Logging.getLogger(getContext().system(), this);
    private final Message msg_test = new Message("Wiadomość testowa!!!");

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof Commands){
            if((Commands)message == Commands.START){
                this.sender().tell(msg_test, ActorRef.noSender());
            }
        } else{
            unhandled("Message: " + message.toString() + " is unhandled yet.");
        }
    }
}
