package dev.wcs.tutoring.ssm.persistentstate;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends StateMachineConfigurerAdapter<String, String> {

    @Override
    public void configure(StateMachineStateConfigurer<String, String> states) throws Exception {
        states
                .withStates()
                .initial("ORDERED")
                .state("PROCESSING")
                .state("DELIVERED")
                .state("CLOSED");
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions) throws Exception {
        transitions
                .withExternal()
                .source("ORDERED").target("PROCESSING")
                .event("PROCESS")
                .and()
                .withExternal()
                .source("PROCESSING").target("DELIVERED")
                .event("SEND")
                .and()
                .withExternal()
                .source("DELIVERED").target("CLOSED")
                .event("CLOSE");
    }

}
