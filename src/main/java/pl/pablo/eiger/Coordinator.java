package pl.pablo.eiger;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.LoggingAdapter;
import akka.event.Logging;
import akka.japi.Creator;
import akka.routing.RoundRobinPool;

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
    
    private final LoggingAdapter la_log = Logging.getLogger(getContext().system(), this);
    private ActorRef ar_Processor;

    Coordinator(String str_SystemName){
        la_log.info("System: " + str_SystemName);
    }
    
    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof Commands){
            if((Commands)message == Commands.START){
                startSystem();
            }
        } else if(message instanceof Message){
            ar_Processor.tell(message, ActorRef.noSender());
        } else{
            unhandled("Message: " + message.toString() + " is unhandled yet.");
        }
    }
    
    private void startSystem(){
        la_log.info("Uruchamiam system.");
        ActorRef ar_Receiver = this.context().actorOf(Props.create(Receiver.class), "Receiver");
        ActorRef ar_Notifier = this.context().actorOf(new RoundRobinPool(2).props(Props.create(Notifier.class)), "Notifier");
        ar_Processor = this.context().actorOf(new RoundRobinPool(3).props(Props.create(Processor.class, ar_Notifier)), "Processor");
        ar_Receiver.tell(Commands.START, this.self());
    }
}
