package pl.pablo.eiger;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Creator;

public class Processor extends UntypedActor {
    
    public static Props props(final ActorRef ar_Notifier) {
        return Props.create(new Creator<Processor>() {
            private static final long serialVersionUID = 1L;
 
            @Override
            public Processor create() throws Exception {
                return new Processor(ar_Notifier);
            }
        });
    }
    
    private final LoggingAdapter la_log = Logging.getLogger(getContext().system(), this);
    private final ActorRef ar_notifier;
    
    Processor(ActorRef ar_notifier){
        this.ar_notifier = ar_notifier;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        ar_notifier.tell(message, ActorRef.noSender());
    }
    
}
