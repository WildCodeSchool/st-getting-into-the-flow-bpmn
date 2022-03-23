package dev.wcs.tutoring.ssm.persistentstate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
public class StateMachinePersistenceConfig {

    private final StateMachine<String, String> stateMachine;

    private final JdbcTemplate jdbcTemplate;

    public StateMachinePersistenceConfig(StateMachine<String, String> stateMachine, JdbcTemplate jdbcTemplate) {
        this.stateMachine = stateMachine;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public Persist persist() {
        return new Persist(persistStateMachineHandler(), jdbcTemplate);
    }

    @Bean
    public PersistStateMachineHandler persistStateMachineHandler() {
        return new PersistStateMachineHandler(stateMachine);
    }


}
