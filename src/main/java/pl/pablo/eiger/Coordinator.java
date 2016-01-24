package pl.pablo.eiger;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.LoggingAdapter;
import akka.event.Logging;
import akka.japi.Creator;

public class Coordinator extends UntypedActor {
    
    public static Props props(final String str_SystemName) {
        return Props.create(new Creator<Coordinator>() {
            private static final long serialVersionUID = 1L;
 
            @Override
            public Coordinator create() throws Exception {
                return new Coordinator(str_SystemName);
            }
        });
    }
    
    LoggingAdapter la_log = Logging.getLogger(getContext().system(), this);

    Coordinator(String str_SystemName){
        la_log.info("System: " + str_SystemName);
    }
    
    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof Commands){
            if((Commands)message == Commands.START){
                la_log.info("Uruchamiam system.");
            }
        } else{
            throw new UnsupportedOperationException("Not supported yet.");
        }
    } 
}
