package pl.pablo.eiger;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Creator;

public class Notifier extends UntypedActor {
    
    public static Props props() {
        return Props.create(new Creator<Notifier>() {
            private static final long serialVersionUID = 1L;
 
            @Override
            public Notifier create() throws Exception {
                return new Notifier();
            }
        });
    }
    
    private final LoggingAdapter la_log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof Message){
            la_log.info(((Message) message).getName());
        }
    }
    
}
