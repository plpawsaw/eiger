package pl.pablo.eiger;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Eiger {
    
    public static void main(String[] args){
       ActorSystem ActorSys = ActorSystem.create("Eiger");
       ActorRef ar_Coordinator = ActorSys.actorOf(Props.create(Coordinator.class, "The Alps - Eiger"), "Coordinator");
       ar_Coordinator.tell(Commands.START, ActorRef.noSender());
    }
}
