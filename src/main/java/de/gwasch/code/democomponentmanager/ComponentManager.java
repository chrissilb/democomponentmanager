package de.gwasch.code.democomponentmanager;

import de.gwasch.code.escframework.events.listeners.EventAdapter;
import de.gwasch.code.escframework.states.events.TransitionEvent;
import de.gwasch.code.escframework.states.listeners.ActivityListener;
import de.gwasch.code.escframework.states.states.DependencyType;
import de.gwasch.code.escframework.states.states.ISState;
import de.gwasch.code.escframework.states.states.SimpleState;
import de.gwasch.code.escframework.states.states.SwitchedState;
import de.gwasch.code.escframework.states.transistionmodes.SequentialTransitionMode;


public class ComponentManager {

	//todo, TransitionListener und TransitionHandler generieren mit Methoden für die verschiedenen Transitionen
	//  private void Create()       { /* Komponente instanziieren... */ }
	//  private void Initialize()   { /* Komponente initialisieren... */ }
	//  private void Activate()     { /* Komponente aktivieren... */ }
	//
	//  private void Deactivate()   { /* Komponente deaktivieren... */}
	//  private void Uninitialize() { /* Komponente deinitialisieren... */ }
	class LifecycleEventHandler extends EventAdapter<TransitionEvent<LifecycleState>> {
		
		public boolean onProcess(TransitionEvent<LifecycleState> event) {
			System.out.println(componentName + " transitions from " + event.getOldValue() + " to " + event.getNewValue());
			
//			if (event.getNewValue() == LifecycleState.Initialized) {
//				errorState.setValue(ErrorState.Failed);
//			}
			
			return true;
		}
	}
	
	class LifecycleActivityHandler implements ActivityListener<LifecycleState> {

		public boolean activity(LifecycleState newvalue, LifecycleState oldvalue) {
			
//	        if ((newvalue.ordinal() > oldvalue.ordinal()) && (errorState.getValue() == ErrorState.Failed)) {
//	        	return false;
//	        }
//	        else {
	        	return true;
//	        }
		}
		
	}

	class ErrorEventHandler extends EventAdapter<TransitionEvent<ErrorState>> {
		
		public void onFinish(TransitionEvent<ErrorState> event, boolean success) {

		}
	}
	
	class AvailabilityEventHandler extends EventAdapter<TransitionEvent<Double>> {
		
		public void onFinish(TransitionEvent<Double> event, boolean success) {

		}
	}
	
    private ISState<LifecycleState> lifecycleState;
    private SimpleState<ErrorState> errorState;
    private SwitchedState<Double> availabilityIndicator;

    private String componentName;
    private Object component;        // die Komponenten-Instanz...

    public ComponentManager(String componentName) {
    	
    	this.componentName = componentName;

        lifecycleState = new ISState<LifecycleState>(LifecycleState.class, componentName + ".lfs");
        lifecycleState.setTransitionMode(new SequentialTransitionMode<LifecycleState>());
        lifecycleState.setValue(LifecycleState.Deployed);
        lifecycleState.registerTransitionListener(new LifecycleEventHandler());
        lifecycleState.setActivityHandler(new LifecycleActivityHandler());
        

//        errorState = new State<ErrorState>(ErrorState.class, componentName + ".es");
//        errorState.setValue(ErrorState.NoError);
//        errorState.registerTransitionListener(new ErrorEventHandler());
//        
//        availabilityIndicator = new SwitchedState<Double>(Double.class, componentName + ".avi");
//        availabilityIndicator.getPrimaryState().setValue(0.0);
//        availabilityIndicator.getSecondaryState().setValue(1.0);
//        availabilityIndicator.setPrimaryStateSet(true);
//        availabilityIndicator.registerTransitionListener(new AvailabilityEventHandler());
    }

    public ISState<LifecycleState> getLifecycleState() {
        return lifecycleState;
    }

    public SimpleState<ErrorState> getErrorState() {
        return errorState;
    }    

    public SwitchedState<Double> getAvailabilityIndicator() {
        return availabilityIndicator;
    }
    
    public Object getComponent() {
    	return component;
    }


    public void addDependency(ComponentManager cm)
    {
        lifecycleState.addDependency(cm.lifecycleState, DependencyType.Passive, DependencyType.Active);
    }


//    public void removeDependency(ComponentManager cm)
//    {
//        lifecycleState.removeDependency(cm.lifecycleState);
//    }


    public void start()
    {
        lifecycleState.setValue(LifecycleState.Active);
    }


    public void stop()
    {
        lifecycleState.setValue(LifecycleState.Deployed);
    }





//    private bool OnPostActivity(LifecycleState newstate, LifecycleState oldstate)
//    {
//        if (   (newstate > oldstate)
//            && (errorState.Value == ErrorState.Failed)   ) return false;
//
//        return true;
//    }
//
//
//    private void OnErrorTransition()
//    {
//        availabilityIndicator.PrimaryStateSet = (errorState.Value == ErrorState.Failed
//                                   || lifecycleState.Value != LifecycleState.Active);
//    
//        // Auf geänderten Fehlerzustand reagieren...
//    }
//
//
//    private void OnLifecycleTransition()
//    {
//        availabilityIndicator.PrimaryStateSet = (errorState.Value == ErrorState.Failed
//                                    || lifecycleState.Value != LifecycleState.Active);
//
//        // Auf geänderten Zustand im Lebenszyklus reagieren...
//    }
//
//
//    private void OnAITransition()
//    {
//        // Auf geänderten Availability Indicator reagieren...
//    }
}
