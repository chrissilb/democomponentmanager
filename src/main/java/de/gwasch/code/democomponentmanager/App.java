package de.gwasch.code.democomponentmanager;


import de.gwasch.code.escframework.states.utils.MaskPool;

public class App {
    public static void main( String[] args ) {
    	
    	MaskPool.getInstance().addMaskSet(LifecycleState.class, LifecycleState.Active);
    	
        ComponentManager cmA = new ComponentManager("a");
        ComponentManager cmB = new ComponentManager("b");
        cmA.addDependency(cmB);
//        cmB.addDependency(cmA);
        cmA.start();
        cmB.start();
//        cmA.stop();
//        cmB.stop();
    }
}
